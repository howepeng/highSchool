package hs.service.impl;

import hs.common.Property;
import hs.common.dao.QueryDAO;
import hs.common.vo.SqlParameterVO;
import hs.common.vo.SqlResultVO;
import hs.dao.ClassInfoDaoI;
import hs.dao.DictionaryDaoI;
import hs.dao.MonthInfoDaoI;
import hs.dao.MonthScoreDaoI;
import hs.dao.StudentDaoI;
import hs.model.TbClassInfo;
import hs.model.TbDictionary;
import hs.model.TbMonthInfo;
import hs.model.TbMonthScore;
import hs.model.TbStudent;
import hs.model.TbYearInfo;
import hs.pageModel.DataGrid;
import hs.pageModel.MonthScore;
import hs.pageModel.MonthScoreJXL;
import hs.pageModel.SessionInfo;
import hs.service.MonthScoreServiceI;
import hs.service.YearInfoServiceI;
import hs.util.StringUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service("monthScoreService")
public class MonthScoreServiceImpl implements MonthScoreServiceI {

    private MonthScoreDaoI monthScoreDao;

    @Autowired
    public void setMonthScoreDao(MonthScoreDaoI monthScoreDao) {
        this.monthScoreDao = monthScoreDao;
    }

    private ClassInfoDaoI classInfoDao;

    @Autowired
    public void setClassInfoDao(ClassInfoDaoI classInfoDao) {
        this.classInfoDao = classInfoDao;
    }

    private StudentDaoI studentDao;

    @Autowired
    public void setStudentDao(StudentDaoI studentDao) {
        this.studentDao = studentDao;
    }

    private MonthInfoDaoI monthInfoDao;

    @Autowired
    public void setMonthInfoDao(MonthInfoDaoI monthInfoDao) {
        this.monthInfoDao = monthInfoDao;
    }

    private YearInfoServiceI yearInfoService;

    @Autowired
    public void setYearInfoService(YearInfoServiceI yearInfoService) {
        this.yearInfoService = yearInfoService;
    }

    private DictionaryDaoI dictionaryDao;

    @Autowired
    public void setDictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Autowired
    protected QueryDAO queryDAO = null;

    @Override
    public DataGrid datagrid(MonthScore monthScore, SessionInfo sessionInfo) {
        DataGrid j = new DataGrid();
        List<MonthScore> finList = new ArrayList<MonthScore>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbMonthScore t where t.tbStudent.tbYearInfo.isDefault = '105001' AND t.tbYearInfo.isDefault = '105001'";
        hql += this.addCondition(monthScore, params, sessionInfo);
        List<TbMonthScore> tbMonList = monthScoreDao.find(hql, params, monthScore.getPage(), monthScore.getRows());
        if (tbMonList != null && tbMonList.size() > 0) {
            for (TbMonthScore t : tbMonList) {
                MonthScore f = new MonthScore();
                BeanUtils.copyProperties(t, f);
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
                if(t.getTbMonthInfo() != null) {
                    f.setMonthTimeId(t.getTbMonthInfo().getId());
                    f.setMonthTimeName(t.getTbMonthInfo().getName());
                }
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(monthScoreDao.count("SELECT count(*) " + hql, params));
        return j;
    }

    @Override
    public List<MonthScore> getMonthScoreList(MonthScore monthScore, SessionInfo sessionInfo) {
        List<MonthScore> finList = new ArrayList<MonthScore>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbMonthScore t where t.tbStudent.tbYearInfo.isDefault = '105001' AND t.tbYearInfo.isDefault = '105001'";
        hql += this.addCondition(monthScore, params, sessionInfo);
        List<TbMonthScore> tbMonList = monthScoreDao.find(hql, params, monthScore.getPage(), monthScore.getRows());
        if (tbMonList != null && tbMonList.size() > 0) {
            for (TbMonthScore t : tbMonList) {
                MonthScore f = new MonthScore();
                BeanUtils.copyProperties(t, f);

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
                if(t.getTbMonthInfo() != null) {
                    f.setMonthTimeId(t.getTbMonthInfo().getId());
                    f.setMonthTimeName(t.getTbMonthInfo().getName());
                }
                finList.add(f);
            }
        }
        return finList;
    }

     @Override
     public void remove(String ids) {
         if (ids != null) {
             for (String id : ids.split(",")) {
                 TbMonthScore r = monthScoreDao.getById(TbMonthScore.class, id.trim());
                 if (r != null) {
                     monthScoreDao.delete(r);
                 }
             }
         }
     }

     /**
      * 生成查询hql语句
      *
      * @param student
      * @param params
      * @return
      */
     private String addCondition(MonthScore monthScore, Map<String, Object> params, SessionInfo sessionInfo) {
         String hql = "";

         if(sessionInfo != null && sessionInfo.isOnlyDirector()) {
             String classId = getClassIdByUserId(sessionInfo);
             hql += " AND t.tbStudent.tbClassInfo.id in (" + classId + ")";
         }
         if(monthScore.getMonthTimeId() != null && !monthScore.getMonthTimeId().trim().equals("")) {
             hql += " AND t.tbMonthInfo.id LIKE :monthTimeId";
             params.put("monthTimeId", "%%" + monthScore.getMonthTimeId() + "%%");
         }

         if(monthScore.getProfessionalId() != null && !monthScore.getProfessionalId().trim().equals("")) {
             hql += " AND t.tbStudent.tbClassType.professionalId LIKE :professionalId";
             params.put("professionalId", "%%" + monthScore.getProfessionalId() + "%%");
         }

         if(monthScore.getClassName() != null && !monthScore.getClassName().trim().equals("")) {
             hql += " AND t.tbStudent.tbClassInfo.id LIKE :className";
             params.put("className", "%%" + monthScore.getClassName() + "%%");
         }
        if (StringUtil.isNotEmpty(monthScore.getSubjectId())) {
            String columnName = StringUtil.getSubjectColumnName(monthScore.getSubjectId());
            if(monthScore.getFractionCountStart() != null && monthScore.getFractionCountEnd() != null) {
                hql += " AND t."+columnName+" >= " + monthScore.getFractionCountStart()
                        + " AND t."+columnName+" <= " + monthScore.getFractionCountEnd();
            }
            if (StringUtil.isNotEmpty(monthScore.getObjectId())
                    && StringUtil.isNotEmpty(monthScore.getMonthTimeId())
                    && StringUtil.isNotEmpty(monthScore.getAverageId())) {
                SqlResultVO average = null;
                // 班级平均分
                if ("109001".equals(monthScore.getObjectId())) {
                    average = this.averageClassName(monthScore);
                // 年级平均分
                } else if ("109002".equals(monthScore.getObjectId())) {
                    average = this.averageClassType(monthScore);
                }
                if (average != null) {
                    String averageColumnName = StringUtil.getSubjectAverageColumnName(monthScore.getSubjectId());
                    String averageValue = average.getString(averageColumnName);
                    String averageName = StringUtil.getAverageName(monthScore.getAverageId());
                    hql += " AND t." + columnName + " "+ averageName + " " + averageValue;
                }
            }

        }
        if (monthScore.getSort() != null) {
            if ("monthTimeName".equals(monthScore.getSort())) {
                hql += " ORDER BY t.tbMonthInfo.name";
            } else if ("classType".equals(monthScore.getSort())) {
                hql += " ORDER BY t.tbStudent.tbClassType.name";
            } else if ("className".equals(monthScore.getSort())) {
                hql += " ORDER BY t.tbStudent.tbClassInfo.name";
            } else if ("studentName".equals(monthScore.getSort())) {
                hql += " ORDER BY t.tbStudent.name";
            } else if ("professionalName".equals(monthScore.getSort())) {
                hql += " ORDER BY t.tbStudent.tbClassType.professionalId";
            } else {
                hql += " ORDER BY t." + monthScore.getSort();
            }
            hql += " " + monthScore.getOrder();
        }
        return hql;
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
    public String[] uploadClassScore(MonthScore monthScore) {
        String[] ret = null;
        String fileName = monthScore.getMonthScoreFileFileName();
        if (StringUtil.isNotEmpty(fileName)
                && StringUtil.isNotEmpty(monthScore.getMonthTimeId())
                && StringUtil.isNotEmpty(monthScore.getClassName())) {
            ret= new String[2];
            String fileRealName = UUID.randomUUID().toString()
                    + getFileType(fileName);
            String filePath = Property.getProperty("uploadPath")+"\\classScore\\" + fileRealName;
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                // 建立文件输出流
                fos = new FileOutputStream(filePath);
                // 建立文件上传流
                fis = new FileInputStream(monthScore.getMonthScoreFile());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                List<MonthScoreJXL> monthScores = readExcel(filePath);
                int count = 0;
                if (monthScores != null) {
                    count = monthScores.size();
                }
                // 先删除再导入
                if (count > 0) {
                    Map<String, Object> m = new HashMap<String, Object>();
                    m.put("monthInfoId", monthScore.getMonthTimeId());
                    m.put("classId", monthScore.getClassName());
                    List<TbMonthScore> monthScoreList = monthScoreDao.find("FROM TbMonthScore t1 where t1.tbMonthInfo.id = :monthInfoId "
                    +"and t1.tbStudent.tbClassInfo.id = :classId "
                    +"and t1.tbYearInfo.isDefault = '105001'", m);
                    if (monthScoreList != null) {
                        for (TbMonthScore item : monthScoreList) {
                            monthScoreDao.delete(item);
                        }
                    }
                }
                TbYearInfo tbYearInfo = yearInfoService.getDefaultYear();
                // 再导入
                for (MonthScoreJXL item : monthScores) {
                    if(StringUtil.isEmpty(item.getStudentId())) {
                        continue;
                    }
                    TbMonthScore nItem  = new TbMonthScore();
                    nItem.setId(UUID.randomUUID().toString());
                    nItem.setFractionComp1(new BigDecimal(item.getFractionComp1()));
                    nItem.setFractionComp2(new BigDecimal(item.getFractionComp2()));
                    nItem.setFractionComp3(new BigDecimal(item.getFractionComp3()));
                    nItem.setFractionComp_count(new BigDecimal(item.getFractionComp_count()));
                    nItem.setFractionCount(new BigDecimal(item.getFractionCount()));
                    nItem.setFractionEnglish(new BigDecimal(item.getFractionEnglish()));
                    nItem.setFractionLanguage(new BigDecimal(item.getFractionLanguage()));
                    nItem.setFractionMath(new BigDecimal(item.getFractionMath()));
                    nItem.setCreatedatetime(new Date());
                    nItem.setTbYearInfo(tbYearInfo);
                    TbStudent student = studentDao.getById(TbStudent.class, item.getStudentId());
                    nItem.setTbStudent(student);
                    TbMonthInfo monthInfo = monthInfoDao.getById(TbMonthInfo.class, monthScore.getMonthTimeId());
                    nItem.setTbMonthInfo(monthInfo);
                    monthScoreDao.save(nItem);
                }
                ret[0] = monthScore.getMonthTimeId();
                ret[1] = "classScore/"+fileRealName;
            } catch (Exception e) {
                System.out.println("文件上传失败");
                e.printStackTrace();
                ret = null;
            } finally {
                close(fos, fis);
            }
        }
        return ret;
    }

    /**
     * 读取Excel
     *
     * @param filePath
     */
    @SuppressWarnings({ "unchecked", "rawtypes"})
    public List<MonthScoreJXL> readExcel(String filePath) {
        InputStream inputXML = new BufferedInputStream(MonthScoreJXL.class.getResourceAsStream("monthScoreJXL.xml"));
        XLSReader mainReader;
        try {
            mainReader = ReaderBuilder.buildFromXML(inputXML );
            InputStream inputXLS = new BufferedInputStream(new FileInputStream(filePath));
            MonthScoreJXL monthScore =  new MonthScoreJXL();
            List monthScores = new ArrayList();
            Map beans = new HashMap();
            beans.put("monthScore", monthScore);
            beans.put("monthScores", monthScores);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            if (readStatus.isStatusOK()) {
                return monthScores;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println("FileInputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                System.out.println("FileOutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }

    private String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."),fileName.length());
    }

    @Override
    public SqlResultVO averageClassType(MonthScore monthScore) {
        SqlParameterVO param = new SqlParameterVO();
        param.putVarchar("monthTimeId", monthScore.getMonthTimeId());
        // 计算年级平均分
        if (StringUtil.isNotEmpty(monthScore.getProfessionalId())) {
            param.putVarchar("professionalId", monthScore.getProfessionalId());
        }
        SqlResultVO ret = averageAll(param);
        return ret;
    }

    @Override
    public SqlResultVO averageClassName(MonthScore monthScore) {
        SqlParameterVO param = new SqlParameterVO();
        param.putVarchar("monthTimeId", monthScore.getMonthTimeId());
        // 计算班级平均分
        if (StringUtil.isNotEmpty(monthScore.getClassName())) {
            param.putVarchar("classId", monthScore.getClassName());
        }
        SqlResultVO ret = averageAll(param);
        return ret;
    }

    public SqlResultVO averageAll(SqlParameterVO param) {
        SqlResultVO ret = queryDAO.queryForObject("com.averageAllMonthScore", param, SqlResultVO.class);
        return ret;
    }

}
