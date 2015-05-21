package hs.service.impl;

import hs.common.dao.QueryDAO;
import hs.common.vo.SqlParameterVO;
import hs.common.vo.SqlResultVO;
import hs.dao.ClassInfoDaoI;
import hs.dao.ClassTypeDaoI;
import hs.dao.DictionaryDaoI;
import hs.dao.StudentDaoI;
import hs.dao.UserDaoI;
import hs.dao.YearInfoDaoI;
import hs.model.TbClassInfo;
import hs.model.TbClassType;
import hs.model.TbDictionary;
import hs.model.TbStudent;
import hs.model.TbUser;
import hs.model.TbYearInfo;
import hs.pageModel.ClassInfo;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.SessionInfo;
import hs.service.ClassInfoServiceI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classInfoService")
public class ClassInfoServiceImpl implements ClassInfoServiceI {

    private ClassInfoDaoI classInfoDao;

    @Autowired
    public void setClassInfoDao(ClassInfoDaoI classInfoDao) {
        this.classInfoDao = classInfoDao;
    }

    private ClassTypeDaoI classTypeDao;

    @Autowired
    public void setClassTypeDao(ClassTypeDaoI classTypeDao) {
        this.classTypeDao = classTypeDao;
    }

    private YearInfoDaoI yearInfoDao;

    @Autowired
    public void setYearInfoDao(YearInfoDaoI yearInfoDao) {
        this.yearInfoDao = yearInfoDao;
    }

    private UserDaoI userDao;

    @Autowired
    public void setUserDao(UserDaoI userDao) {
        this.userDao = userDao;
    }

    private StudentDaoI studentDao;

    @Autowired
    public void setStudentDao(StudentDaoI studentDao) {
        this.studentDao = studentDao;
    }

    private DictionaryDaoI dictionaryDao;

    @Autowired
    public void setictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Autowired
    protected QueryDAO queryDAO = null;

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        SessionInfo sessionInfo = (SessionInfo)ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from TbClassInfo t where t.tbYearInfo.isDefault = '105001' ";
        if (sessionInfo != null && sessionInfo.isOnlyDirector()) {
               hql += " AND t.tbUser.id = :userId";
               params.put("userId", sessionInfo.getId());
        }
        hql += " order by t.tbClassType.professionalId";
        List<TbClassInfo> l = classInfoDao.find(hql, params);
        if (l != null && l.size() > 0) {
            for (TbClassInfo t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getName());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(ClassInfo classInfo) {
        DataGrid j = new DataGrid();
        String hql = "FROM TbClassInfo";
        if (classInfo.getSort() != null) {
            hql += " ORDER BY " + classInfo.getSort() + " " + classInfo.getOrder();
        }
        j.setRows(classInfoDao.find(hql, classInfo.getPage(), classInfo.getRows()));
        j.setTotal(classInfoDao.count("SELECT count(*) FROM TbClassInfo"));
        return j;
    }

     public DataGrid datagridClassInfo(ClassInfo classInfo){
         DataGrid j = new DataGrid();
         List<ClassInfo> finList = new ArrayList<ClassInfo>();
         Map<String, Object> params = new HashMap<String, Object>();
         String hql = "FROM TbClassInfo t";
         hql += this.addCondition(classInfo, params);
         List<TbClassInfo> tbClassInfoList = classInfoDao.find(hql, params, classInfo.getPage(), classInfo.getRows());
         if(tbClassInfoList != null && tbClassInfoList.size() > 0) {
             for(TbClassInfo t : tbClassInfoList) {
                 ClassInfo f = new ClassInfo();
                 BeanUtils.copyProperties(t, f);
                 if(t.getTbClassType()!=null) {
                     f.setClassType(t.getTbClassType().getClassType());
                 }
                 if(t.getTbYearInfo() != null) {
                     f.setYearId(t.getTbYearInfo().getName());
                 }
                 if(t.getTbUser() != null) {
                     f.setUserId(t.getTbUser().getName());
                 }
                 if(t.getClassMode() !=null) {
                    TbDictionary tbDictionary = dictionaryDao.getById(TbDictionary.class, t.getClassMode());
                    f.setClassMode(tbDictionary.getName());
                 }
                 finList.add(f);
             }
         }
         j.setRows(finList);
         j.setTotal(classInfoDao.count("SELECT count(*) " + hql,params));
         return j;
     }

     /**
      * 生成查询hql语句
      *
      * @param student
      * @param params
      * @return
      */
     private String addCondition(ClassInfo classInfo, Map<String, Object> params) {
         String hql = "";

         if (classInfo.getYearId() != null && !classInfo.getYearId().trim().equals("")) {
             hql += " WHERE t.tbYearInfo.id LIKE :yearId";
             params.put("yearId", "%%" + classInfo.getYearId() + "%%");
         }

         if (classInfo.getClassType() != null && !classInfo.getClassType().trim().equals("")) {
             if (classInfo.getYearId() != null && !classInfo.getYearId().trim().equals("")) {
                 hql += " AND";
             }else {
                 hql += " WHERE";
             }
             hql += " t.tbClassType.id LIKE :classType";
             params.put("classType", "%%" + classInfo.getClassType() + "%%");
         }
         if(classInfo.getClassMode() != null && !classInfo.getClassMode().trim().equals("")) {
             if ((classInfo.getYearId() != null && !classInfo.getYearId().trim().equals("")) ||
                 (classInfo.getClassType() != null && !classInfo.getClassType().trim().equals(""))) {
                 hql += " AND";
             }else {
                 hql += " WHERE";
             }
             hql += " t.classMode LIKE :classMode";
             params.put("classMode", "%%" + classInfo.getClassMode() + "%%");
         }
         if (classInfo.getSort() != null) {
             if ("classType".equals(classInfo.getSort())) {
                 hql += " ORDER BY t.tbClassType.classType";
             } else if ("userId".equals(classInfo.getSort())) {
                 hql += " ORDER BY t.tbUser.name";
             } else {
                 hql += " ORDER BY t." + classInfo.getSort();
             }
             hql += " " + classInfo.getOrder();
         }
         return hql;
     }


    @Override
    public void add(ClassInfo classInfo) {
        TbClassInfo tbClassInfo = new TbClassInfo();
        BeanUtils.copyProperties(classInfo, tbClassInfo);
        tbClassInfo.setId(UUID.randomUUID().toString());
        tbClassInfo.setTbClassType(classTypeDao.getById(TbClassType.class, classInfo.getClassType().trim()));
        tbClassInfo.setTbYearInfo(yearInfoDao.getById(TbYearInfo.class, classInfo.getYearId().trim()));
        tbClassInfo.setTbUser(userDao.getById(TbUser.class, classInfo.getUserId().trim()));
        classInfoDao.save(tbClassInfo);
    }

    @Override
    public void edit(ClassInfo classInfo) {
        TbClassInfo tbClassInfo = classInfoDao.getById(TbClassInfo.class, classInfo.getId());
        //BeanUtils.copyProperties(classInfo, tbClassInfo, new String[] { "id" });
        tbClassInfo.setTbUser(userDao.getById(TbUser.class, classInfo.getUserId().trim()));
        classInfoDao.save(tbClassInfo);
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                String hql = "FROM TbStudent t WHERE t.tbClassInfo.id LIKE :classId";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("classId", "%%" + id.trim() + "%%");
                List<TbStudent> tbStuList = studentDao.find(hql,params);
                if(tbStuList != null && tbStuList.size() > 0){
                    for(TbStudent t: tbStuList) {
                        t.setTbClassInfo(null);
                        studentDao.save(t);
                    }
                }
                TbClassInfo r = classInfoDao.getById(TbClassInfo.class, id.trim());
                if (r != null) {
                    classInfoDao.delete(r);
                }
            }
        }
    }

    @Override
    public ClassInfo getClassInfo(String id) {
        ClassInfo classInfo = new ClassInfo();
        TbClassInfo tbClassInfo = classInfoDao.getById(TbClassInfo.class, id.trim());
        BeanUtils.copyProperties(tbClassInfo, classInfo);
        return classInfo;
    }

    @Override
    public int getStudentCount(String classId) {
        SqlParameterVO param = new SqlParameterVO();
        param.putVarchar("classId", classId);
        SqlResultVO  ret = queryDAO.queryForObject("com.getStudentCountByClassId", param);
        int subtotal = ret.getInteger("sCount");
        return subtotal;
    }
}
