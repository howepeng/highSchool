package hs.service.impl;

import hs.common.dao.QueryDAO;
import hs.common.dao.UpdateDAO;
import hs.common.vo.SqlParameterVO;
import hs.common.vo.SqlResultVO;
import hs.dao.ClassInfoDaoI;
import hs.dao.DictionaryDaoI;
import hs.dao.MonthInfoDaoI;
import hs.dao.MonthScoreDaoI;
import hs.dao.ScoreCountDaoI;
import hs.model.TbClassInfo;
import hs.model.TbDictionary;
import hs.model.TbMonthInfo;
import hs.model.TbMonthScore;
import hs.model.TbScoreCount;
import hs.model.TbYearInfo;
import hs.pageModel.DataGrid;
import hs.pageModel.ScoreCount;
import hs.pageModel.SessionInfo;
import hs.service.ScoreCountServiceI;
import hs.service.YearInfoServiceI;
import hs.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scoreCountService")
public class ScoreCountServiceImpl implements ScoreCountServiceI {

    private ScoreCountDaoI scoreCountDao;

    @Autowired
    public void setScoreCountDao(ScoreCountDaoI scoreCountDao) {
        this.scoreCountDao = scoreCountDao;
    }

    private MonthScoreDaoI monthScoreDao;

    @Autowired
    public void setMonthScoreDao(MonthScoreDaoI monthScoreDao) {
        this.monthScoreDao = monthScoreDao;
    }

    private MonthInfoDaoI monthInfoDao;

    @Autowired
    public void setMonthInfoDao(MonthInfoDaoI monthInfoDao) {
        this.monthInfoDao = monthInfoDao;
    }

    private DictionaryDaoI dictionaryDao;

    @Autowired
    public void setDictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    private YearInfoServiceI yearInfoService;

    private ClassInfoDaoI classInfoDao;

    @Autowired
    public void setClassInfoDao(ClassInfoDaoI classInfoDao) {
        this.classInfoDao = classInfoDao;
    }

    @Autowired
    public void setYearInfoService(YearInfoServiceI yearInfoService) {
        this.yearInfoService = yearInfoService;
    }

    @Autowired
    protected UpdateDAO updateDAO = null;

    @Autowired
    protected QueryDAO queryDAO = null;

    @Override
    public DataGrid datagrid(ScoreCount scoreCount, SessionInfo sessionInfo) {
        DataGrid j = new DataGrid();
        List<ScoreCount> finList = new ArrayList<ScoreCount>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbScoreCount t where t.tbStudent.tbYearInfo.isDefault = '105001' And t.tbYearInfo.isDefault = '105001'";
        hql += this.addCondition(scoreCount, params, sessionInfo);
        finList = getFinList(scoreCount, params, hql);
        j.setRows(finList);
        j.setTotal(scoreCountDao.count("SELECT count(*) " + hql, params));
        return j;
    }

     @Override
     public void remove(String ids) {
//         if (ids != null) {
//             for (String id : ids.split(",")) {
//                 TbMonthScore r = scoreCountDao.getById(TbMonthScore.class, id.trim());
//                 if (r != null) {
//                     scoreCountDao.delete(r);
//                 }
//             }
//         }
     }
     @Override
     public void calculate(ScoreCount scoreCount) {

         TbMonthInfo tbMonthInfo1 = monthInfoDao.getById(TbMonthInfo.class, scoreCount.getMonthTime1());
         TbMonthInfo tbMonthInfo2 = monthInfoDao.getById(TbMonthInfo.class, scoreCount.getMonthTime2());
         String professionalId = scoreCount.getProfessionalId();
         String subjectName = StringUtil.getSubjectName(scoreCount);
         TbYearInfo tbYearInfo = yearInfoService.getDefaultYear();
         // 先删除再添加
         deleteScoreCount(tbMonthInfo1, tbMonthInfo2, professionalId);
         Map<String, TbScoreCount> scoreCountMap = new HashMap<String, TbScoreCount>();
         Map<String, TbClassInfo> classId = new HashMap<String, TbClassInfo>();
         // 月考总分年级排名
         List<TbMonthScore> monthScoreList1 = getMonthScore(tbMonthInfo1, professionalId, subjectName, null);
         // 比较名次
         Map<String, String> gradeRank1 = new HashMap<String, String>();

         if (monthScoreList1 != null) {
             int gradeRank = 1;
             for (TbMonthScore item : monthScoreList1) {
                 gradeRank1.put(item.getTbStudent().getId(), gradeRank + "");
                 gradeRank++;
             }
         }
         // 计算名次
         List<TbMonthScore> monthScoreList2 = getMonthScore(tbMonthInfo2, professionalId, subjectName, null);
         if (monthScoreList2 != null) {
             int gradeRank = 1;
             // 计算年级名次
             for (TbMonthScore item : monthScoreList2) {
                 TbScoreCount score = new TbScoreCount();
                 score.setTbStudent(item.getTbStudent());
                 score.setTbMonthInfo1(tbMonthInfo1);
                 score.setTbMonthInfo2(tbMonthInfo2);
                 score.setGradeRank(gradeRank);
                 String last = gradeRank1.get(item.getTbStudent().getId());
                 score.setGradeChange((Integer.valueOf(last) - gradeRank));
                 score.setSubjectId(scoreCount.getSubjectId());
                 score.setTbYearInfo(tbYearInfo);
                 gradeRank++;
                 scoreCountMap.put(item.getTbStudent().getId(), score);
                 // 记录班级信息
                 if (!classId.containsKey(item.getTbStudent().getTbClassInfo().getId())) {
                     classId.put(item.getTbStudent().getTbClassInfo().getId(),
                             item.getTbStudent().getTbClassInfo());
                 }
             }
         }
         // 月考总分班级名次
         // 循环班级信息
         for (Entry<String, TbClassInfo> classInfo : classId.entrySet()) {

             // 比较名次
             List<TbMonthScore> monthScoreListByClass1 = getMonthScore(tbMonthInfo1, professionalId, subjectName, classInfo.getKey());
             Map<String, String> classRank1 = new HashMap<String, String>();
             if (monthScoreListByClass1 != null) {
                 int classRank = 1;
                 for (TbMonthScore item : monthScoreListByClass1) {
                     classRank1.put(item.getTbStudent().getId(), classRank + "");
                     classRank++;
                 }
             }
             List<TbMonthScore> monthScoreListByClass2 = getMonthScore(tbMonthInfo2, professionalId, subjectName, classInfo.getKey());
             if (monthScoreListByClass2 != null) {
                 int classRank = 1;
                 for (TbMonthScore item : monthScoreListByClass2) {
                     TbScoreCount score = scoreCountMap.get(item.getTbStudent().getId());
                     score.setClassRank(classRank);
                     String last = gradeRank1.get(item.getTbStudent().getId());
                     score.setClassChange((Integer.valueOf(last) - classRank));
                     classRank++;
                 }
             }
         }
         // 保存总分
         for (Entry<String, TbScoreCount> score : scoreCountMap.entrySet()) {
             scoreCountDao.save(score.getValue());
         }
     }

    private void deleteScoreCount(TbMonthInfo tbMonthInfo1, TbMonthInfo tbMonthInfo2, String professionalId) {
        SqlParameterVO param = new SqlParameterVO();
        param.putVarchar("monthTime1", tbMonthInfo1.getId());
        param.putVarchar("monthTime2", tbMonthInfo2.getId());
        param.putVarchar("professionalId", professionalId);
        updateDAO.update("com.deleteScoreCount", param);
    }

    private List<TbMonthScore> getMonthScore(TbMonthInfo tbMonthInfo1, String professionalId, String orderByName, String classInfoId) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("monthInfoId", tbMonthInfo1.getId());
        m.put("professionalId", professionalId);
        String hsql = "FROM TbMonthScore t1 where t1.tbMonthInfo.id = :monthInfoId and t1.tbStudent.tbClassType.professionalId = :professionalId AND t1.tbStudent.tbYearInfo.isDefault = '105001'";

        if (StringUtil.isNotEmpty(classInfoId)) {
            hsql = hsql + " and t1.tbStudent.tbClassInfo.id = :classId AND t1.tbStudent.tbClassInfo.tbYearInfo.isDefault = '105001'";
            m.put("classId", classInfoId);
        }

        if (StringUtil.isNotEmpty(orderByName)) {
            hsql = hsql + " order by t1." + orderByName + " desc";
        }
        return monthScoreDao.find(hsql, m);
    }

     private String addCondition(ScoreCount scoreCount, Map<String, Object> params,  SessionInfo sessionInfo) {
         String hql = "";
         if(sessionInfo != null && sessionInfo.isOnlyDirector()) {
             String classId = getClassIdByUserId(sessionInfo);
             hql += " AND t.tbStudent.tbClassInfo.id in (" + classId + ")";
         }
         if(scoreCount.getMonthTime1() != null && !scoreCount.getMonthTime1().trim().equals("")) {
             hql += " AND t.tbMonthInfo1.id LIKE :monthTime1";
             params.put("monthTime1", "%%" + scoreCount.getMonthTime1() + "%%");
         }

         if(scoreCount.getMonthTime2() != null && !scoreCount.getMonthTime2().trim().equals("")) {
             hql += " AND t.tbMonthInfo2.id LIKE :monthTime2";
             params.put("monthTime2", "%%" + scoreCount.getMonthTime2() + "%%");
         }

         if(scoreCount.getProfessionalId() != null && !scoreCount.getProfessionalId().trim().equals("")) {
             hql += " AND t.tbStudent.tbClassType.professionalId LIKE :professionalId";
             params.put("professionalId", "%%" + scoreCount.getProfessionalId() + "%%");
         }

         if(scoreCount.getClassInfoId() != null && !scoreCount.getClassInfoId().trim().equals("")) {
             hql += " AND t.tbStudent.tbClassInfo.id LIKE :className";
             params.put("className", "%%" + scoreCount.getClassInfoId() + "%%");
         }

         if(scoreCount.getSubjectId() != null && !scoreCount.getSubjectId().trim().equals("")) {
             hql += " AND t.subjectId = :subjectId";
             params.put("subjectId", scoreCount.getSubjectId());
         }
         if (scoreCount.getSort() != null) {
             if ("monthTime1".equals(scoreCount.getSort())) {
                 hql += " ORDER BY t.tbMonthInfo1.name";
             } else if ("monthTime2".equals(scoreCount.getSort())) {
                 hql += " ORDER BY t.tbMonthInfo2.name";
             } else if ("studentName".equals(scoreCount.getSort())) {
                 hql += " ORDER BY t.tbStudent.name";
             } else if ("className".equals(scoreCount.getSort())) {
                 hql += " ORDER BY t.tbStudent.tbClassInfo.name";
             } else if ("subjectName".equals(scoreCount.getSort())) {
                 hql += " ORDER BY t.subjectId";
             } else if ("professionalName".equals(scoreCount.getSort())) {
                 hql += " ORDER BY t.tbMonthInfo2.tbClassType.professionalId";
             } else {
                 hql += " ORDER BY t." + scoreCount.getSort();
             }
             hql +=  " " + scoreCount.getOrder();
         }
         return hql;
     }


     @Override
     public SqlResultVO getScoreCountForFractionLine(SqlParameterVO param) {
         SqlResultVO ret = queryDAO.queryForObject("com.getScoreCountForFractionLine", param, SqlResultVO.class);
         return ret;
     }

     @Override
     public SqlResultVO getFractionForFractionLine(SqlParameterVO param) {
         SqlResultVO ret = queryDAO.queryForObject("com.getFractionForFractionLine", param, SqlResultVO.class);
         return ret;
     }

     @Override
     public List<SqlResultVO> getStudentListForFractionLine(SqlParameterVO param) {
         List<SqlResultVO> ret = queryDAO.queryForList("com.getStudentListForFractionLine",
                 param, SqlResultVO.class);
         return ret;
     }

     private String getClassIdByUserId(SessionInfo sessionInfo) {
         String ret = "";
         Map<String, Object> params = new HashMap<String, Object>();
         String hql = "from TbClassInfo t  where 1=1 ";
         hql += " AND t.tbUser.id = :userId";
         params.put("userId", sessionInfo.getId());
         hql += " order by t.createdatetime desc";
         List<TbClassInfo> l = classInfoDao.find(hql, params);
         if (l != null && l.size() > 0) {
             for (TbClassInfo t : l) {
                 ret = "'" + t.getId() + "',";
             }
         }
         if (ret != null && 1 < ret.length()) {
             ret = ret.substring(0, ret.length() - 1);
         }
         return ret;
     }

    @Override
    public List<ScoreCount> getScoreCountList(ScoreCount scoreCount,
            SessionInfo sessionInfo) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbScoreCount t where t.tbStudent.tbYearInfo.isDefault = '105001' And t.tbYearInfo.isDefault = '105001'";
        hql += this.addCondition(scoreCount, params, sessionInfo);
        List<ScoreCount> finList = getFinList(scoreCount, params, hql);
        return finList;
    }

    private List<ScoreCount> getFinList(ScoreCount scoreCount,
            Map<String, Object> params, String hql) {
        List<ScoreCount> finList = new ArrayList<ScoreCount>();
        List<TbScoreCount> tbMonList = scoreCountDao.find(hql, params, scoreCount.getPage(), scoreCount.getRows());
        if (tbMonList != null && tbMonList.size() > 0) {
            for (TbScoreCount t : tbMonList) {
                ScoreCount f = new ScoreCount();
                BeanUtils.copyProperties(t, f);

                if(t.getTbMonthInfo1() != null) {
                    f.setMonthTime1(t.getTbMonthInfo1().getName());
                }
                if(t.getTbMonthInfo2() != null) {
                    f.setMonthTime2(t.getTbMonthInfo2().getName());
                }
                if(t.getTbStudent() != null) {
                    f.setStudentId(t.getTbStudent().getId());
                    f.setStudentName(t.getTbStudent().getName());
                    if(t.getTbStudent().getTbClassType() != null) {
                        f.setProfessionalId(t.getTbStudent().getTbClassType().getProfessionalId());
                        TbDictionary r = dictionaryDao.getById(TbDictionary.class,
                                t.getTbStudent().getTbClassType().getProfessionalId());
                        f.setProfessionalName(r.getName());
                    }
                    if(t.getTbStudent().getTbClassInfo() != null) {
                        f.setClassName(t.getTbStudent().getTbClassInfo().getName());
                    }
                }

                Map<String, Object> param1 = new HashMap<String, Object>();
                String hql1 = "FROM TbMonthScore t1 where t1.tbStudent.tbYearInfo.isDefault = '105001'";
                hql1 += " AND t1.tbMonthInfo.id LIKE :monthTime2";
                param1.put("monthTime2", "%%" + t.getTbMonthInfo2().getId() + "%%");
                hql1 += " AND t1.tbStudent.id LIKE :studentId";
                param1.put("studentId", "%%" + t.getTbStudent().getId() + "%%");
                List<TbMonthScore> tbMon = monthScoreDao.find(hql1, param1);
                if(tbMon != null && tbMon.size() > 0) {
                    BeanUtils.copyProperties(tbMon.get(0), f);
                    TbDictionary r = dictionaryDao.getById(TbDictionary.class, f.getSubjectId());
                    f.setSubjectName(r.getName());
                    if ("106001".equals(f.getSubjectId())) {
                        f.setFraction(tbMon.get(0).getFractionLanguage());
                    } else if ("106002".equals(f.getSubjectId())){
                        f.setFraction(tbMon.get(0).getFractionMath());
                    } else if ("106003".equals(f.getSubjectId())){
                        f.setFraction(tbMon.get(0).getFractionEnglish());
                    } else if ("106004".equals(f.getSubjectId())){
                        f.setFraction(tbMon.get(0).getFractionComp1());
                    } else if ("106005".equals(f.getSubjectId())){
                        f.setFraction(tbMon.get(0).getFractionComp2());
                    } else if ("106006".equals(f.getSubjectId())){
                        f.setFraction(tbMon.get(0).getFractionComp3());
                    } else if ("106007".equals(f.getSubjectId())){
                        f.setFraction(tbMon.get(0).getFractionComp_count());
                    } else if ("106008".equals(f.getSubjectId())){
                        f.setFraction(tbMon.get(0).getFractionCount());
                    }
                }
                finList.add(f);
            }
        }
        return finList;
    }
}
