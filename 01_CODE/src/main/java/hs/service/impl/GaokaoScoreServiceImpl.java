package hs.service.impl;

import hs.common.Property;
import hs.common.dao.QueryDAO;
import hs.common.vo.SqlParameterVO;
import hs.common.vo.SqlResultVO;
import hs.dao.ClassInfoDaoI;
import hs.dao.DictionaryDaoI;
import hs.dao.GaokaoScoreDaoI;
import hs.dao.StudentDaoI;
import hs.model.TbClassInfo;
import hs.model.TbDictionary;
import hs.model.TbGaokaoScore;
import hs.model.TbStudent;
import hs.model.TbYearInfo;
import hs.pageModel.DataGrid;
import hs.pageModel.GaokaoScore;
import hs.pageModel.GaokaoScoreJXL;
import hs.pageModel.GaokaoScoreStudent;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;
import hs.service.GaokaoScoreServiceI;
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

@Service("gaokaoScoreService")
public class GaokaoScoreServiceImpl implements GaokaoScoreServiceI {

    private GaokaoScoreDaoI gaokaoScoreDao;

    @Autowired
    public void setGaokaoScoreDao(GaokaoScoreDaoI gaokaoScoreDao) {
        this.gaokaoScoreDao = gaokaoScoreDao;
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
    public DataGrid datagrid(GaokaoScore gaokaoScore, SessionInfo sessionInfo) {
        DataGrid j = new DataGrid();
        List<GaokaoScore> finList = new ArrayList<GaokaoScore>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbGaokaoScore t where t.tbStudent.tbYearInfo.isDefault = '105001' AND t.tbYearInfo.isDefault = '105001'";
        hql += this.addCondition(gaokaoScore, params, sessionInfo);
        List<TbGaokaoScore> tbGaokaoList = gaokaoScoreDao.find(hql, params, gaokaoScore.getPage(), gaokaoScore.getRows());
        if (tbGaokaoList != null && tbGaokaoList.size() > 0) {
            for (TbGaokaoScore t : tbGaokaoList) {
                GaokaoScore f = new GaokaoScore();
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
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(gaokaoScoreDao.count("SELECT count(*) " + hql, params));
        return j;
    }

    @Override
    public List<GaokaoScore> getGaokaoScoreList(GaokaoScore gaokaoScore, SessionInfo sessionInfo) {
        List<GaokaoScore> finList = new ArrayList<GaokaoScore>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbGaokaoScore t where t.tbStudent.tbYearInfo.isDefault = '105001' AND t.tbYearInfo.isDefault = '105001'";
        hql += this.addCondition(gaokaoScore, params, sessionInfo);
        List<TbGaokaoScore> tbGaokaoList = gaokaoScoreDao.find(hql, params, gaokaoScore.getPage(), gaokaoScore.getRows());
        if (tbGaokaoList != null && tbGaokaoList.size() > 0) {
            for (TbGaokaoScore t : tbGaokaoList) {
                GaokaoScore f = new GaokaoScore();
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
                finList.add(f);
            }
        }
        return finList;
    }

     @Override
     public void remove(String ids) {
         if (ids != null) {
             for (String id : ids.split(",")) {
                 TbGaokaoScore r = gaokaoScoreDao.getById(TbGaokaoScore.class, id.trim());
                 if (r != null) {
                     gaokaoScoreDao.delete(r);
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
     private String addCondition(GaokaoScore gaokaoScore, Map<String, Object> params, SessionInfo sessionInfo) {
         String hql = "";

         if(sessionInfo != null && sessionInfo.isOnlyDirector()) {
             String classId = getClassIdByUserId(sessionInfo);
             hql += " AND t.tbStudent.tbClassInfo.id in (" + classId + ")";
         }

         if(gaokaoScore.getProfessionalId() != null && !gaokaoScore.getProfessionalId().trim().equals("")) {
             hql += " AND t.tbStudent.tbClassType.professionalId LIKE :professionalId";
             params.put("professionalId", "%%" + gaokaoScore.getProfessionalId() + "%%");
         }

         if(gaokaoScore.getClassName() != null && !gaokaoScore.getClassName().trim().equals("")) {
             hql += " AND t.tbStudent.tbClassInfo.id LIKE :className";
             params.put("className", "%%" + gaokaoScore.getClassName() + "%%");
         }
        if (StringUtil.isNotEmpty(gaokaoScore.getSubjectId())) {
            String columnName = StringUtil.getSubjectColumnName(gaokaoScore.getSubjectId());
            if(gaokaoScore.getFractionCountStart() != null && gaokaoScore.getFractionCountEnd() != null) {
                hql += " AND t."+columnName+" >= " + gaokaoScore.getFractionCountStart()
                        + " AND t."+columnName+" <= " + gaokaoScore.getFractionCountEnd();
            }
            if (StringUtil.isNotEmpty(gaokaoScore.getObjectId())
                    && StringUtil.isNotEmpty(gaokaoScore.getAverageId())) {
                SqlResultVO average = null;
                // 班级平均分
                if ("109001".equals(gaokaoScore.getObjectId())) {
                    average = this.averageClassName(gaokaoScore);
                // 年级平均分
                } else if ("109002".equals(gaokaoScore.getObjectId())) {
                    average = this.averageClassType(gaokaoScore);
                }
                if (average != null) {
                    String averageColumnName = StringUtil.getSubjectAverageColumnName(gaokaoScore.getSubjectId());
                    String averageValue = average.getString(averageColumnName);
                    String averageName = StringUtil.getAverageName(gaokaoScore.getAverageId());
                    hql += " AND t." + columnName + " "+ averageName + " " + averageValue;
                }
            }

        }
        if (gaokaoScore.getSort() != null) {
            if ("classType".equals(gaokaoScore.getSort())) {
                hql += " ORDER BY t.tbStudent.tbClassType.name";
            } else if ("className".equals(gaokaoScore.getSort())) {
                hql += " ORDER BY t.tbStudent.tbClassInfo.name";
            } else if ("studentName".equals(gaokaoScore.getSort())) {
                hql += " ORDER BY t.tbStudent.name";
            } else if ("professionalName".equals(gaokaoScore.getSort())) {
                hql += " ORDER BY t.tbStudent.tbClassType.professionalId";
            } else {
                hql += " ORDER BY t." + gaokaoScore.getSort();
            }
            hql += " " + gaokaoScore.getOrder();
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
    public String[] uploadClassScore(GaokaoScore gaokaoScore) {
        String[] ret = null;
        String fileName = gaokaoScore.getGaokaoScoreFileFileName();
        if (StringUtil.isNotEmpty(fileName)
                && StringUtil.isNotEmpty(gaokaoScore.getClassName())) {
            ret= new String[1];
            String fileRealName = UUID.randomUUID().toString()
                    + getFileType(fileName);
            String filePath = Property.getProperty("uploadPath")+"\\gaokaoScore\\" + fileRealName;
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                // 建立文件输出流
                fos = new FileOutputStream(filePath);
                // 建立文件上传流
                fis = new FileInputStream(gaokaoScore.getGaokaoScoreFile());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                List<GaokaoScoreJXL> gaokaoScores = readExcel(filePath);
                int count = 0;
                if (gaokaoScores != null) {
                    count = gaokaoScores.size();
                }
                // 先删除再导入
                if (count > 0) {
                    Map<String, Object> m = new HashMap<String, Object>();
                    m.put("classId", gaokaoScore.getClassName());
                    List<TbGaokaoScore> gaokaoScoreList = gaokaoScoreDao.find("FROM TbGaokaoScore t1 where 1=1 "
                    +"and t1.tbStudent.tbClassInfo.id = :classId "
                    +"and t1.tbYearInfo.isDefault = '105001'", m);
                    if (gaokaoScoreList != null) {
                        for (TbGaokaoScore item : gaokaoScoreList) {
                            gaokaoScoreDao.delete(item);
                        }
                    }
                }
                TbYearInfo tbYearInfo = yearInfoService.getDefaultYear();
                // 再导入
                for (GaokaoScoreJXL item : gaokaoScores) {
                    if(StringUtil.isEmpty(item.getStudentId())) {
                        continue;
                    }
                    TbGaokaoScore nItem  = new TbGaokaoScore();
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
                    nItem.setAdmissionSchoolName(item.getAdmissionSchoolName());
                    nItem.setGaokaoNum(item.getGaokaoNum());
                    gaokaoScoreDao.save(nItem);
                }
                ret[0] = "gaokaoScore/"+fileRealName;
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
    public List<GaokaoScoreJXL> readExcel(String filePath) {
        InputStream inputXML = new BufferedInputStream(GaokaoScoreJXL.class.getResourceAsStream("gaokaoScoreJXL.xml"));
        XLSReader mainReader;
        try {
            mainReader = ReaderBuilder.buildFromXML(inputXML );
            InputStream inputXLS = new BufferedInputStream(new FileInputStream(filePath));
            GaokaoScoreJXL gaokaoScore = new GaokaoScoreJXL();
            List gaokaoScores = new ArrayList();
            Map beans = new HashMap();
            beans.put("gaokaoScore", gaokaoScore);
            beans.put("gaokaoScores", gaokaoScores);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            if (readStatus.isStatusOK()) {
                return gaokaoScores;
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
    public SqlResultVO averageClassType(GaokaoScore gaokaoScore) {
        SqlParameterVO param = new SqlParameterVO();
        // 计算年级平均分
        if (StringUtil.isNotEmpty(gaokaoScore.getProfessionalId())) {
            param.putVarchar("professionalId", gaokaoScore.getProfessionalId());
        }
        SqlResultVO ret = averageAll(param);
        return ret;
    }

    @Override
    public SqlResultVO averageClassName(GaokaoScore gaokaoScore) {
        SqlParameterVO param = new SqlParameterVO();
        // 计算班级平均分
        if (StringUtil.isNotEmpty(gaokaoScore.getClassName())) {
            param.putVarchar("classId", gaokaoScore.getClassName());
        }
        SqlResultVO ret = averageAll(param);
        return ret;
    }

    public SqlResultVO averageAll(SqlParameterVO param) {
        SqlResultVO ret = queryDAO.queryForObject("com.averageAllGaokaoScore", param, SqlResultVO.class);
        return ret;
    }

    @Override
    public List<GaokaoScoreStudent> getStudentInfo(Student student,
            SessionInfo sessionInfo) {
        SqlParameterVO param = new SqlParameterVO();
        param.putVarchar("classId", student.getClassId());
        List<SqlResultVO> students = queryDAO.queryForList("com.getGaokaoScoreStudents", param, SqlResultVO.class);
        if (students != null && students.size() > 0) {
            List<GaokaoScoreStudent> ret = new ArrayList<GaokaoScoreStudent>();
            int index = 1;
            for (SqlResultVO item : students) {
                GaokaoScoreStudent nItem = new GaokaoScoreStudent();
                nItem.setIndex(index + "");
                nItem.setId(item.getString("id"));
                nItem.setName(item.getString("name"));
                nItem.setClassType(item.getString("class_type"));
                nItem.setClassName(item.getString("class_name"));
                if ("0".equals(item.getString("sex"))) {
                    nItem.setSex("男");
                } else {
                    nItem.setSex("女");
                }
                nItem.setGaokaoNum(item.getString("gaokao_num"));
                nItem.setFractionLanguage(item.getString("fraction_language"));
                nItem.setFractionMath(item.getString("fraction_math"));
                nItem.setFractionEnglish(item.getString("fraction_english"));
                nItem.setFractionComp1(item.getString("fraction_comp1"));
                nItem.setFractionComp2(item.getString("fraction_comp2"));
                nItem.setFractionComp3(item.getString("fraction_comp3"));
                nItem.setFractionComp_count(item.getString("fraction_comp_count"));
                nItem.setFractionCount(item.getString("fraction_count"));
                nItem.setAdmissionSchoolName(item.getString("admission_school_name"));
                index++;
                ret.add(nItem);
            }
            return ret;
        }
        return null;
    }

}
