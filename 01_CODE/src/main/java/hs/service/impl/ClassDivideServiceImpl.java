package hs.service.impl;

import hs.dao.ClassInfoDaoI;
import hs.dao.ClassTypeDaoI;
import hs.dao.DictionaryDaoI;
import hs.dao.StudentDaoI;
import hs.dao.YearInfoDaoI;
import hs.model.TbClassInfo;
import hs.model.TbClassType;
import hs.model.TbDictionary;
import hs.model.TbStudent;
import hs.model.TbYearInfo;
import hs.pageModel.ClassDivide;
import hs.service.ClassDivideServiceI;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classDivideService")
public class ClassDivideServiceImpl implements ClassDivideServiceI {

    private StudentDaoI studentDao;

    @Autowired
    public void setStudentDao(StudentDaoI studentDao) {
        this.studentDao = studentDao;
    }

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

    private DictionaryDaoI dictionaryDao;

    @Autowired
    public void setDictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public String divide(ClassDivide classDivide) {
        //检索符合条件的学生
        String hql = "FROM TbStudent t WHERE t.tbClassType.id LIKE :classType AND t.tbYearInfo.id LIKE :yearId ORDER BY t.fractionCount desc";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("classType", "%%" + classDivide.getClassType() + "%%");
        params.put("yearId", "%%" + classDivide.getYearId() + "%%");
        List<TbStudent> tbStuList = studentDao.find(hql,params);

        int classNum = Integer.parseInt(classDivide.getClassNum());
        if(tbStuList.size() >= classNum) {

            //生成班级信息
            String[] classId = new String[classNum];
            TbYearInfo tbYearInfo = yearInfoDao.getById(TbYearInfo.class, classDivide.getYearId().trim());
            TbClassType tbClassType = classTypeDao.getById(TbClassType.class, classDivide.getClassType().trim());
            TbDictionary tbDictionary = dictionaryDao.getById(TbDictionary.class, classDivide.getClassModeId().trim());
            for(int i=1; i<=classNum; i++){
                String id = UUID.randomUUID().toString();
                classId[i-1] = id;

                TbClassInfo tbClassInfo = new TbClassInfo();
                tbClassInfo.setId(id);
                tbClassInfo.setName(classDivide.getClassPrefixion() + i);
                tbClassInfo.setTbYearInfo(tbYearInfo);
                tbClassInfo.setTbClassType(tbClassType);
                tbClassInfo.setClassMode(tbDictionary.getName());
                tbClassInfo.setCreatedatetime(new Date());
                classInfoDao.save(tbClassInfo);
            }

            //分班
            if("104001".equals(classDivide.getClassModeId())){
                //总分降序排列平行分
                for(int i=0; i<tbStuList.size(); i++) {
                    int classPostfix = i%classNum;
                    TbClassInfo tbClassInfo = classInfoDao.getById(TbClassInfo.class, classId[classPostfix]);
                    tbStuList.get(i).setTbClassInfo(tbClassInfo);
                    studentDao.save(tbStuList.get(i));
                }
            } else {
                //总分降序排列前后分
                int avgStuNum = tbStuList.size()/classNum;
                int j=0;

                for(int i=0; i<classNum-1;i++) {
                    TbClassInfo tbClassInfo = classInfoDao.getById(TbClassInfo.class, classId[i]);

                    for(j=avgStuNum*i; j<avgStuNum*(i+1); j++) {
                        tbStuList.get(j).setTbClassInfo(tbClassInfo);
                        studentDao.save(tbStuList.get(j));
                    }
                }
                //更新最后一组学生
                TbClassInfo tbClassInfo = classInfoDao.getById(TbClassInfo.class, classId[classNum-1]);
                for(int n=j;n<tbStuList.size();n++) {
                    tbStuList.get(n).setTbClassInfo(tbClassInfo);
                    studentDao.save(tbStuList.get(n));
                }
            }
            return "分班成功！";
        } else {
            return "分班数大于学生总数！";
        }
    }

}
