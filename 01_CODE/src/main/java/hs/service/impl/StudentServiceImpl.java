package hs.service.impl;

import hs.dao.ClassTypeDaoI;
import hs.dao.FileDaoI;
import hs.dao.FinanceDaoI;
import hs.dao.IdFileDaoI;
import hs.dao.PreferentialDaoI;
import hs.dao.ReportFileDaoI;
import hs.dao.StudentDaoI;
import hs.dao.StudentInfoHistoryDaoI;
import hs.dao.UserDaoI;
import hs.model.TbClassType;
import hs.model.TbFinance;
import hs.model.TbPreferential;
import hs.model.TbStudent;
import hs.model.TbStudentInfoHistory;
import hs.model.TbUser;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;
import hs.pageModel.StudentInfoHistory;
import hs.service.StudentServiceI;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements StudentServiceI {

    private StudentDaoI studentDao;

    @Autowired
    public void setStudentDao(StudentDaoI studentDao) {
        this.studentDao = studentDao;
    }

    private StudentInfoHistoryDaoI studentInfoHistoryDao;

    @Autowired
    public void setStudentInfoHistoryDao(StudentInfoHistoryDaoI studentInfoHistoryDao) {
        this.studentInfoHistoryDao = studentInfoHistoryDao;
    }

    private ClassTypeDaoI classTypeDao;

    @Autowired
    public void setClassTypeDao(ClassTypeDaoI classTypeDao) {
        this.classTypeDao = classTypeDao;
    }

    private FinanceDaoI financeDao;

    @Autowired
    public void setFinanceDao(FinanceDaoI financeDao) {
        this.financeDao = financeDao;
    }

    private UserDaoI userDao;

    @Autowired
    public void setUserDao(UserDaoI userDao) {
        this.userDao = userDao;
    }

    private PreferentialDaoI preferentialDao;

    @Autowired
    public void setPreferentialDao(PreferentialDaoI preferentialDao) {
        this.preferentialDao = preferentialDao;
    }

    private FileDaoI fileDao;

    @Autowired
    public void setFileDao(FileDaoI fileDao) {
        this.fileDao = fileDao;
    }

    private IdFileDaoI idFileDaoI;

    @Autowired
    public void setIdFileDaoI(IdFileDaoI idFileDaoI) {
        this.idFileDaoI = idFileDaoI;
    }

    private ReportFileDaoI reportFileDaoI;

    @Autowired
    public void setReportFileDaoI(ReportFileDaoI reportFileDaoI) {
        this.reportFileDaoI = reportFileDaoI;
    }

    @Override
    public List<Combobox> combox(Student student) {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbStudent> l = studentDao.find("from TbStudent");
        if (l != null && l.size() > 0) {
            for (TbStudent t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getName());
                rl.add(r);
            }
        }
        return rl;
    }
    /**
     * 缴费
     */
    @Override
    public DataGrid datagridBeforePay(Student student) {
        DataGrid j = new DataGrid();
        List<Student> finList = new ArrayList<Student>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbStudent t WHERE t.isPaymentFlg = '0'";
        hql += this.addConditionAND(student, params);
        List<TbStudent> tbStuList = studentDao.find(hql, params, student.getPage(), student.getRows());
        if (tbStuList != null && tbStuList.size() > 0) {
            for (TbStudent t : tbStuList) {
                Student f = new Student();
                BeanUtils.copyProperties(t, f);
                if(t.getTbClassType() != null) {
                    f.setClassType(t.getTbClassType().getId());
                    f.setClassTypeName(t.getTbClassType().getClassType());
                }

                f.setPayee(t.getTbUser().getName());
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(studentDao.count("SELECT count(*) " + hql, params));
        return j;
    }

    /**
     * 补交费
     */
    @Override
    public DataGrid datagridAfterPay(Student student) {
        DataGrid j = new DataGrid();
        List<Student> finList = new ArrayList<Student>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbStudent t WHERE t.arrearflg='1'";
        hql += this.addConditionAND(student, params);
        List<TbStudent> tbStuList = studentDao.find(hql, params, student.getPage(), student.getRows());
        if (tbStuList != null && tbStuList.size() > 0) {
            for (TbStudent t : tbStuList) {
                Student f = new Student();
                BeanUtils.copyProperties(t, f);
                f.setPayee(t.getTbUser().getName());
                f.setArrearFee(t.getArrearFee());
                if(t.getTbClassType() != null) {
                    f.setClassType(t.getTbClassType().getId());
                    f.setClassTypeName(t.getTbClassType().getClassType());
                }
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(studentDao.count("SELECT count(*) " + hql, params));
        return j;
    }

    /**
     * 退款
     */
    @Override
    public DataGrid datagridReturnPay(Student student) {
        DataGrid j = new DataGrid();
        List<Student> finList = new ArrayList<Student>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbStudent t WHERE t.countFee > t.signFee";
        hql += this.addConditionAND(student, params);
        List<TbStudent> tbStuList = studentDao.find(hql, params, student.getPage(), student.getRows());
        if (tbStuList != null && tbStuList.size() > 0) {
            for (TbStudent t : tbStuList) {
                Student f = new Student();
                BeanUtils.copyProperties(t, f);
                f.setPayee(t.getTbUser().getName());
                if(t.getTbClassType() != null) {
                    f.setClassType(t.getTbClassType().getId());
                    f.setClassTypeName(t.getTbClassType().getClassType());
                }
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(studentDao.count("SELECT count(*) " + hql, params));
        return j;
    }

    @Override
    public DataGrid datagridStudent(Student student) {
        DataGrid j = new DataGrid();
        List<Student> finList = new ArrayList<Student>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbStudent t";
        hql += this.addCondition(student, params);
        List<TbStudent> tbStuList = studentDao.find(hql, params, student.getPage(), student.getRows());
        if (tbStuList != null && tbStuList.size() > 0) {
            for (TbStudent t : tbStuList) {
                Student f = new Student();
                BeanUtils.copyProperties(t, f);
                if ("91".equals(t.getWlqf())) {
                    f.setWlqfContent("文科");
                } else if ("95".equals(t.getWlqf())) {
                    f.setWlqfContent("理科");
                } else if ("93".equals(t.getWlqf())) {
                    f.setWlqfContent("艺术文科");
                } else if ("97".equals(t.getWlqf())) {
                    f.setWlqfContent("艺术理科");
                } else if ("94".equals(t.getWlqf())) {
                    f.setWlqfContent("体育文科");
                } else if ("98".equals(t.getWlqf())) {
                    f.setWlqfContent("体育理科");
                }
                if ("0".equals(t.getStudentType())) {
                    f.setStuTypeContent("复读");
                } else if ("1".equals(t.getStudentType())) {
                    f.setStuTypeContent("应届");
                } else if ("2".equals(t.getStudentType())) {
                    f.setStuTypeContent("往届");
                }
                if ("0".equals(t.getSex())) {
                    f.setSexContent("男");
                } else if ("1".equals(t.getSex())) {
                    f.setSexContent("女");
                }
                f.setPayee(t.getTbUser().getName());
//                if (t.getTbPreferential() != null) {
//                    f.setArrearFee(t.getTbClassType().getCountFee().subtract(t.getCountFee()).subtract(t.getTbPreferential().getPreferentialFee()));
//                } else {
//                    f.setArrearFee(t.getTbClassType().getCountFee().subtract(t.getCountFee()));
//                }
                if (t.getTbClassType()!=null) {
                    f.setClassType(t.getTbClassType().getId());
                    f.setClassTypeName(t.getTbClassType().getClassType());
                }
                if (t.getTbPhotoFile()!=null) {
                    f.setPhotoId(t.getTbPhotoFile().getId());
                    f.setPhotoImgSrc(t.getTbPhotoFile().getFilePath());
                }
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(studentDao.count("SELECT count(*) " + hql, params));
        return j;
    }

    @Override
    public List<TbStudent> getStudentInfo(Student student) {
        List<Student> finList = new ArrayList<Student>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbStudent t";
        hql += this.addCondition(student, params);
        List<TbStudent> tbStuList = studentDao.find(hql, params, student.getPage(), student.getRows());
        if (tbStuList != null && tbStuList.size() > 0) {
            for (TbStudent t : tbStuList) {
                Student f = new Student();
                BeanUtils.copyProperties(t, f);
                if ("91".equals(t.getWlqf())) {
                    f.setWlqfContent("文科");
                } else if ("95".equals(t.getWlqf())) {
                    f.setWlqfContent("理科");
                } else if ("93".equals(t.getWlqf())) {
                    f.setWlqfContent("艺术文科");
                } else if ("97".equals(t.getWlqf())) {
                    f.setWlqfContent("艺术理科");
                } else if ("94".equals(t.getWlqf())) {
                    f.setWlqfContent("体育文科");
                } else if ("98".equals(t.getWlqf())) {
                    f.setWlqfContent("体育理科");
                }
                if ("0".equals(t.getStudentType())) {
                    f.setStuTypeContent("复读");
                } else if ("1".equals(t.getStudentType())) {
                    f.setStuTypeContent("应届");
                } else if ("2".equals(t.getStudentType())) {
                    f.setStuTypeContent("往届");
                }
                if ("0".equals(t.getSex())) {
                    f.setSexContent("男");
                } else if ("1".equals(t.getSex())) {
                    f.setSexContent("女");
                }
                f.setPayee(t.getTbUser().getName());
                if (t.getTbClassType()!=null) {
                    f.setClassType(t.getTbClassType().getId());
                    f.setClassTypeName(t.getTbClassType().getClassType());
                }
                if (t.getTbPhotoFile()!=null) {
                    f.setPhotoId(t.getTbPhotoFile().getId());
                    f.setPhotoImgSrc(t.getTbPhotoFile().getFilePath());
                }
                finList.add(f);
            }
        }
        return tbStuList;
    }

    @Override
    public DataGrid datagridStudentInfoHistory(
            StudentInfoHistory studentInfoHistory) {
        DataGrid j = new DataGrid();
        List<StudentInfoHistory> finList = new ArrayList<StudentInfoHistory>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbStudentInfoHistory t";
        hql += this.addStudentInfoHistoryCondition(studentInfoHistory, params);
        List<TbStudentInfoHistory> tbStuHistoryList = studentInfoHistoryDao.find(hql, params, studentInfoHistory.getPage(), studentInfoHistory.getRows());
        if (tbStuHistoryList != null && tbStuHistoryList.size() > 0) {
            for (TbStudentInfoHistory t : tbStuHistoryList) {
                StudentInfoHistory f = new StudentInfoHistory();
                BeanUtils.copyProperties(t, f);
                f.setUserId(t.getTbUser().getId());
                f.setUserName(t.getTbUser().getName());
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(studentInfoHistoryDao.count("SELECT count(*) " + hql, params));
        return j;
    }

    /**
     * 生成查询hql语句
     *
     * @param student
     * @param params
     * @return
     */
    private String addCondition(Student student, Map<String, Object> params) {
        String hql = "";

        if (student.getName() != null && !student.getName().trim().equals("")) {
            hql += " WHERE t.name LIKE :name";
            params.put("name", "%%" + student.getName() + "%%");
        }
        if (student.getCreatedatetimeStart() != null && student.getCreatedatetimeEnd() != null) {

            if (student.getName() != null && !student.getName().trim().equals("")) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String createdatetimeEnd =df.format(student.getCreatedatetimeEnd()) + " 24:00:00";
            hql += " t.createdatetime>= :createdatetimeStart AND t.createdatetime<= '" + createdatetimeEnd +"'";
            params.put("createdatetimeStart", student.getCreatedatetimeStart());
        }
        if (student.getClassType() != null && !student.getClassType().trim().equals("")) {
            if ((student.getName() != null && !student.getName().trim().equals("")) ||
                    (student.getCreatedatetimeStart() != null && student.getCreatedatetimeEnd() != null)) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            hql += " t.tbClassType.id LIKE :classType";
            params.put("classType", "%%" + student.getClassType() + "%%");
        }
        if (student.fractionCountStart != null && !student.fractionCountStart.trim().equals("")) {
            if ((student.getName() != null && !student.getName().trim().equals("")) ||
                    (student.getCreatedatetimeStart() != null && student.getCreatedatetimeEnd() != null) ||
                    (student.getClassType() != null && !student.getClassType().trim().equals(""))) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            hql += " t.fractionCount >= "+student.fractionCountStart;
        }
        if (student.fractionCountEnd != null && !student.fractionCountEnd.trim().equals("")) {
            if (student.getName() != null && !student.getName().trim().equals("") ||
                    (student.getCreatedatetimeStart() != null && student.getCreatedatetimeEnd() != null) ||
                    (student.getClassType() != null && !student.getClassType().trim().equals("")) ||
                    (student.fractionCountStart != null && !student.fractionCountStart.trim().equals(""))) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            hql += " t.fractionCount <= "+student.fractionCountEnd;
        }
        if (student.getStayType() != null && !student.getStayType().trim().equals("")) {
            if (student.getName() != null && !student.getName().trim().equals("") ||
                    (student.getCreatedatetimeStart() != null && student.getCreatedatetimeEnd() != null) ||
                    (student.getClassType() != null && !student.getClassType().trim().equals("")) ||
                    (student.fractionCountStart != null && !student.fractionCountStart.trim().equals("")) ||
                    (student.fractionCountEnd != null && !student.fractionCountEnd.trim().equals(""))) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            hql += " t.stayFlg LIKE :stayFlg";
            params.put("stayFlg", "%%" + student.getStayType() + "%%");
        }
        if (student.getSecureType() != null && !student.getSecureType().trim().equals("")) {
            if (student.getName() != null && !student.getName().trim().equals("") ||
                    (student.getCreatedatetimeStart() != null && student.getCreatedatetimeEnd() != null) ||
                    (student.getClassType() != null && !student.getClassType().trim().equals("")) ||
                    (student.fractionCountStart != null && !student.fractionCountStart.trim().equals("")) ||
                    (student.fractionCountEnd != null && !student.fractionCountEnd.trim().equals("")) ||
                    (student.getStayType() != null && !student.getStayType().trim().equals(""))) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            hql += " t.secureFlg LIKE :secureFlg";
            params.put("secureFlg", "%%" + student.getSecureType() + "%%");
        }
        if (student.getSort() != null) {
            hql += " ORDER BY " + student.getSort() + " " + student.getOrder();
        }
        return hql;
    }

    private String addConditionAND(Student student, Map<String, Object> params) {
        String hql = "";

        if (student.getName() != null && !student.getName().trim().equals("")) {
            hql += " AND t.name LIKE :name";
            params.put("name", "%%" + student.getName() + "%%");
        }
        if (student.getCreatedatetimeStart() != null && student.getCreatedatetimeEnd() != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String createdatetimeEnd =df.format(student.getCreatedatetimeEnd()) + " 24:00:00";
            hql += " AND t.createdatetime>= :createdatetimeStart AND t.createdatetime<= '" + createdatetimeEnd +"'";
            params.put("createdatetimeStart", student.getCreatedatetimeStart());
        }
        if (student.getClassType() != null && !student.getClassType().trim().equals("")) {
            hql += " AND t.tbClassType.id LIKE :classType";
            params.put("classType", "%%" + student.getClassType() + "%%");
        }
        if (student.fractionCountStart != null && !student.fractionCountStart.trim().equals("")) {
            hql += " AND t.fractionCount > "+student.fractionCountStart;
        }
        if (student.fractionCountEnd != null && !student.fractionCountEnd.trim().equals("")) {
            hql += " AND t.fractionCount < "+student.fractionCountEnd;
        }
        if (student.getSort() != null) {
            hql += " ORDER BY " + student.getSort() + " " + student.getOrder();
        }
        return hql;
    }

    /**
     * 生成学生历史查询hql语句
     *
     * @param studentInfoHistory
     * @param params
     * @return
     */
    private String addStudentInfoHistoryCondition(StudentInfoHistory studentInfoHistory, Map<String, Object> params) {
        String hql = "";

        if (studentInfoHistory.getName() != null && !studentInfoHistory.getName().trim().equals("")) {
            hql += " WHERE t.name LIKE :name";
            params.put("name", "%%" + studentInfoHistory.getName() + "%%");
        }
        if (studentInfoHistory.getCreatedatetimeStart() != null && studentInfoHistory.getCreatedatetimeEnd() != null) {
            if (studentInfoHistory.getName() != null && !studentInfoHistory.getName().trim().equals("")) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String createdatetimeEnd =df.format(studentInfoHistory.getCreatedatetimeEnd()) + " 24:00:00";
            hql += " t.createdatetime>= :createdatetimeStart AND t.createdatetime<= '" + createdatetimeEnd +"'";
            params.put("createdatetimeStart", studentInfoHistory.getCreatedatetimeStart());
        }
        if (studentInfoHistory.getClassTypeId() != null && !studentInfoHistory.getClassTypeId().trim().equals("")) {
            if ((studentInfoHistory.getName() != null && !studentInfoHistory.getName().trim().equals("")) ||
                    (studentInfoHistory.getCreatedatetimeStart() != null && studentInfoHistory.getCreatedatetimeEnd() != null)) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            hql += " t.classTypeId LIKE :classType";
            params.put("classType", "%%" + studentInfoHistory.getClassTypeId() + "%%");
        }
        if (studentInfoHistory.getUpdatedatetimeStart() != null && studentInfoHistory.getUpdatedatetimeEnd() != null) {

            if ((studentInfoHistory.getName() != null && !studentInfoHistory.getName().trim().equals("")) ||
                    (studentInfoHistory.getCreatedatetimeStart() != null && studentInfoHistory.getCreatedatetimeEnd() != null) ||
                    (studentInfoHistory.getClassTypeName() != null && !studentInfoHistory.getClassTypeName().trim().equals(""))) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String updatedatetimeEnd =df.format(studentInfoHistory.getUpdatedatetimeEnd()) + " 24:00:00";
            hql += " t.updatedatetime>= :updatedatetimeStart AND t.updatedatetime<= '" + updatedatetimeEnd +"'";
            params.put("updatedatetimeStart", studentInfoHistory.getUpdatedatetimeStart());
            params.put("updatedatetimeEnd", updatedatetimeEnd);
        }
        if (studentInfoHistory.getSort() != null) {
            hql += " ORDER BY " + studentInfoHistory.getSort() + " " + studentInfoHistory.getOrder();
        }
        return hql;
    }

    @Override
    public void feePay(Student student, SessionInfo sessionInfo) {
        TbStudent tbStudent = studentDao.getById(TbStudent.class, student.getId().trim());
        TbClassType tbClassType = classTypeDao.getById(TbClassType.class, student.getClassType().trim());
        String signUpMoneyFlg = tbStudent.getSignUpMoneyFlg();
        BigDecimal arrearFee = student.getArrearFee();
        tbStudent.setTbClassType(tbClassType);
        // 学费
        tbStudent.setStudyFee(student.getStu_stuPayment_studyFee());
        // 住宿费
        tbStudent.setStayFee(student.getStu_stuPayment_stayFee());
        // 晚自习费
        tbStudent.setSelfFee(student.getStu_stuPayment_selfFee());
        if ("0".equals(signUpMoneyFlg)) {
            // 报名费
            tbStudent.setSignFee(student.getStu_stuPayment_signFee());
            // 报名费flg
            tbStudent.setSignUpMoneyFlg("1");
        }
        // 成绩单押金
        tbStudent.setScoreFee(student.getStu_stuPayment_scoreFee());
        // 保险费
        tbStudent.setSafetyFee(student.getStu_stuPayment_safetyFee());
        // 水费
        tbStudent.setWaterFee(student.getStu_stuPayment_waterFee());
        // 减免
        tbStudent.setPreferentialFee(student.getStu_stuPayment_preferentialHd());
        // 欠费
        tbStudent.setArrearFee(arrearFee);
        // 总计交付
        if (tbStudent.getCountFee() != null) {
            tbStudent.setCountFee(tbStudent.getCountFee().add(student.getCountFee()));
        } else {
            tbStudent.setCountFee(student.getCountFee());
        }

        if (student.getPreferential() != null && !student.getPreferential().trim().equals("")) {
            TbPreferential tbPreferential = preferentialDao.getById(TbPreferential.class, student.getPreferential().trim());
            tbStudent.setTbPreferential(tbPreferential);
        }

        // 如果欠款>0
        if (arrearFee!= null && arrearFee.compareTo(new BigDecimal(0))>0) {
            tbStudent.setArrearflg("1");
        } else {
            tbStudent.setArrearflg("0");
        }
        // 已缴费
        tbStudent.setIsPaymentFlg("1");

        TbFinance  tbFinance = new TbFinance();
        tbFinance.setId(UUID.randomUUID().toString());
        tbFinance.setName(tbStudent.getName());
        tbFinance.setIdNum(tbStudent.getIdNum());
        // 学费
        if(student.getStudyFee() == null) {
            tbFinance.setStudyFee(new BigDecimal(0));
        } else {
            tbFinance.setStudyFee(student.getStudyFee());
        }
        // 住宿费
        if(student.getStayFee() == null) {
            tbFinance.setStayFee(new BigDecimal(0));
        } else {
            tbFinance.setStayFee(student.getStayFee());
        }
        // 晚自习费
        if(student.getSelfFee() == null) {
            tbFinance.setSelfFee(new BigDecimal(0));
        } else {
            tbFinance.setSelfFee(student.getSelfFee());
        }
        // 报名费
        if ("0".equals(signUpMoneyFlg)) {
            if(student.getSignFee() == null) {
                tbFinance.setSignFee(new BigDecimal(0));
            } else {
                tbFinance.setSignFee(student.getSignFee());
            }
        }
        // 成绩单押金
        if(student.getScoreFee() == null) {
            tbFinance.setScoreFee(new BigDecimal(0));
        } else {
            tbFinance.setScoreFee(student.getScoreFee());
        }
        // 保险费
        if(student.getSafetyFee() == null) {
            tbFinance.setSafetyFee(new BigDecimal(0));
        } else {
            tbFinance.setSafetyFee(student.getSafetyFee());
        }
        // 水费
        if(student.getWaterFee() == null) {
            tbFinance.setWaterFee(new BigDecimal(0));
        } else {
            tbFinance.setWaterFee(student.getWaterFee());
        }
        // 现金
        if(student.getCashFee() == null) {
            tbFinance.setCashFee(new BigDecimal(0));
        } else {
            if (tbFinance.getCashFee() != null) {
                tbFinance.setCashFee(tbFinance.getCashFee().add(student.getCashFee()));
            } else {
                tbFinance.setCashFee(student.getCashFee());
            }
        }
        // 银行转账
        if(student.getBankFee() == null) {
            tbFinance.setBankFee(new BigDecimal(0));
        } else {
            if (tbFinance.getBankFee() != null) {
                tbFinance.setBankFee(tbFinance.getBankFee().add(student.getBankFee()));
            } else {
                tbFinance.setBankFee(student.getBankFee());
            }
        }
        // 拉卡拉pos机转账
        if(student.getLakalaFee() == null) {
            tbFinance.setLakalaFee(new BigDecimal(0));
        } else {
            if (tbFinance.getLakalaFee() != null) {
                tbFinance.setLakalaFee(tbFinance.getLakalaFee().add(student.getLakalaFee()));
            } else {
                tbFinance.setLakalaFee(student.getLakalaFee());
            }
        }
        // 支付宝转账
        if(student.getAliFee() == null) {
            tbFinance.setAliFee(new BigDecimal(0));
        } else {
            if (tbFinance.getAliFee() != null) {
                tbFinance.setAliFee(tbFinance.getAliFee().add(student.getAliFee()));
            } else {
                tbFinance.setAliFee(student.getAliFee());
            }
        }
        // 减免
        if(student.getStu_stuPayment_preferentialHd() == null) {
            tbFinance.setPreferentialFee(new BigDecimal(0));
        } else {
            tbFinance.setPreferentialFee(student.getStu_stuPayment_preferentialHd());
        }
        // 欠费
        if(arrearFee == null) {
            tbFinance.setArrearFee(new BigDecimal(0));
        } else {
            tbFinance.setArrearFee(arrearFee);
        }
        // 退款
        tbFinance.setRefundFee(new BigDecimal(0));
        // 补交费
        tbFinance.setPayAgainFee(new BigDecimal(0));
        // 总计交付
        if (tbFinance.getCountPayFee() != null) {
            tbFinance.setCountPayFee(tbFinance.getCountPayFee().add(student.getCountFee()));
        } else {
            if (tbFinance.getCountPayFee() != null) {
                tbFinance.setCountPayFee(tbFinance.getCountPayFee().add(student.getCountFee()));
            } else {
                tbFinance.setCountPayFee(student.getCountFee());
            }
        }
        // 撤销状态
        tbFinance.setCancelflg("0");
        // 账单类型
        tbFinance.setCrashHistoryType("缴费");

        tbFinance.setTbClassType(tbClassType);
        tbFinance.setTbUser(userDao.getById(TbUser.class, sessionInfo.getId()));
        tbFinance.setCreatedatetime(new Date());
        tbFinance.setStudentId(tbStudent.getId());
        financeDao.save(tbFinance);
    }

    @Override
    public void arrearsPay(Student student, SessionInfo sessionInfo) {
        if (student.cashPayAgainFee == null) {
            student.cashPayAgainFee = new BigDecimal(0);
        }

        if (student.bankPayAgainFee == null) {
            student.bankPayAgainFee = new BigDecimal(0);
        }

        if (student.lakalaPayAgainFee == null) {
            student.lakalaPayAgainFee = new BigDecimal(0);
        }

        if (student.aliPayAgainFee == null) {
            student.aliPayAgainFee = new BigDecimal(0);
        }

        BigDecimal arrearFee = student.cashPayAgainFee.add(student.bankPayAgainFee).add(student.lakalaPayAgainFee).add(student.aliPayAgainFee);

        if (arrearFee.compareTo(new BigDecimal(0)) <= 0) {
            return;
        }

        TbStudent tbStudent = studentDao.getById(TbStudent.class, student.getId().trim());
        // 欠款 = 原欠款-补交款
        if (tbStudent.getArrearFee() != null) {
            tbStudent.setArrearFee(tbStudent.getArrearFee().subtract(arrearFee));
        }

        // 合计交付=原合计交付+补交款
        if (tbStudent.getCountFee() != null) {
            tbStudent.setCountFee(tbStudent.getCountFee().add(arrearFee));
        }

        // 如果欠款>0
        if (tbStudent.getArrearFee().compareTo(new BigDecimal(0))>0) {
            tbStudent.setArrearflg("1");
        } else {
            tbStudent.setArrearflg("0");
        }
        TbFinance  tbFinance = new TbFinance();
        tbFinance.setId(UUID.randomUUID().toString());
        tbFinance.setName(tbStudent.getName());
        tbFinance.setIdNum(tbStudent.getIdNum());
        // 补交费 = 原补交费+现在补交费
        if (tbFinance.getPayAgainFee() != null) {
            tbFinance.setPayAgainFee(tbFinance.getPayAgainFee().add(arrearFee));
        } else {
            tbFinance.setPayAgainFee(arrearFee);
        }

        // 欠款
        if(tbFinance.getArrearFee() != null && tbFinance.getArrearFee().compareTo(new BigDecimal(0)) > 0) {
            tbFinance.setArrearFee(tbFinance.getArrearFee().subtract(arrearFee));
        } else{
            if (tbStudent.getArrearFee() != null) {
                tbFinance.setArrearFee(tbStudent.getArrearFee());
            }
        }

        if(tbFinance.getCashFee() !=null) {
            // 现金
            tbFinance.setCashFee(tbFinance.getCashFee().add(student.cashPayAgainFee));
        } else {
            // 现金
            tbFinance.setCashFee(student.cashPayAgainFee);
        }
        if(tbFinance.getBankFee() !=null) {
            // 银行转账
            tbFinance.setBankFee(tbFinance.getBankFee().add(student.bankPayAgainFee));
        } else {
            // 银行转账
            tbFinance.setBankFee(student.bankPayAgainFee);
        }
        if(tbFinance.getLakalaFee() !=null) {
            // 拉卡拉pos机转账
            tbFinance.setLakalaFee(tbFinance.getLakalaFee().add(student.lakalaPayAgainFee));
        } else {
            // 拉卡拉pos机转账
            tbFinance.setLakalaFee(student.lakalaPayAgainFee);
        }
        if(tbFinance.getAliFee() !=null) {
            // 支付宝转账
            tbFinance.setAliFee(tbFinance.getAliFee().add(student.aliPayAgainFee));
        } else {
            // 支付宝转账
            tbFinance.setAliFee(student.aliPayAgainFee);
        }
        if (tbFinance.getCashPayAgainFee() != null) {
            // 现金补交
            tbFinance.setCashPayAgainFee(tbFinance.getCashPayAgainFee().add(student.cashPayAgainFee));
        } else {
            // 现金补交
            tbFinance.setCashPayAgainFee(student.cashPayAgainFee);
        }
        if (tbFinance.getBankPayAgainFee() != null) {
            // 银行转账补交
            tbFinance.setBankPayAgainFee(tbFinance.getBankPayAgainFee().add(student.bankPayAgainFee));
        } else {
            // 银行转账补交
            tbFinance.setBankPayAgainFee(student.bankPayAgainFee);
        }
        if (tbFinance.getLakalaPayAgainFee() != null) {
            // 拉卡拉pos机转账补交
            tbFinance.setLakalaPayAgainFee(tbFinance.getLakalaPayAgainFee().add(student.lakalaPayAgainFee));
        } else {
            // 拉卡拉pos机转账补交
            tbFinance.setLakalaPayAgainFee(student.lakalaPayAgainFee);
        }
        if (tbFinance.getAliPayAgainFee() != null) {
            // 支付宝转账补交
            tbFinance.setAliPayAgainFee(tbFinance.getAliPayAgainFee().add(student.aliPayAgainFee));
        } else {
            // 支付宝转账补交
            tbFinance.setAliPayAgainFee(student.aliPayAgainFee);
        }
        // 合计交付
        if(tbFinance.getCountPayFee() != null ) {
            tbFinance.setCountPayFee(tbFinance.getCountPayFee().add(arrearFee));
        } else{
            tbFinance.setCountPayFee(arrearFee);
        }
        if(student.getClassType()!=null) {
            tbFinance.setTbClassType(classTypeDao.getById(TbClassType.class, student.getClassType().trim()));
        } else {
            tbFinance.setTbClassType(tbStudent.getTbClassType());
        }
        tbFinance.setTbUser(userDao.getById(TbUser.class, sessionInfo.getId()));
        tbFinance.setCreatedatetime(new Date());
        tbFinance.setStudentId(tbStudent.getId());
        // 撤销状态
        tbFinance.setCancelflg("0");
        // 账单类型
        tbFinance.setCrashHistoryType("补交费");
        financeDao.save(tbFinance);
    }


    @Override
    public void refunPay(Student student, SessionInfo sessionInfo) {
        TbStudent tbStudent = studentDao.getById(TbStudent.class, student.getId().trim());
        if (student.cashRefundFee == null) {
            student.cashRefundFee = new BigDecimal(0);
        }

        if (student.bankRefundFee == null) {
            student.bankRefundFee = new BigDecimal(0);
        }

        if (student.lakalaRefundFee == null) {
            student.lakalaRefundFee = new BigDecimal(0);
        }
        if (student.aliRefundFee == null) {
            student.aliRefundFee = new BigDecimal(0);
        }

        BigDecimal refundFee = student.cashRefundFee.add(student.bankRefundFee).add(student.lakalaRefundFee).add(student.aliRefundFee);

        if (refundFee.compareTo(new BigDecimal(0)) <= 0) {
            return;
        }

        // 退款 = 原退款+退款
        if(tbStudent.getRefundFee() != null) {
            tbStudent.setRefundFee(tbStudent.getRefundFee().add(refundFee));
        } else {
            tbStudent.setRefundFee(refundFee);
        }
        // 合计交付=原合计交付-退款
        if (tbStudent.getCountFee() != null) {
            tbStudent.setCountFee(tbStudent.getCountFee().subtract(refundFee));
        } else {
            tbStudent.setCountFee(refundFee.multiply(new BigDecimal(-1)));
        }

        TbFinance tbFinance = new TbFinance();
        tbFinance.setId(UUID.randomUUID().toString());
        // 退款
        tbFinance.setRefundFee(refundFee);
        // 合计交付
        tbFinance.setCountPayFee(refundFee.multiply(new BigDecimal(-1)));
        // 现金
        tbFinance.setCashFee(student.cashRefundFee.multiply(new BigDecimal(-1)));
        // 银行转账转账
        tbFinance.setBankFee(student.bankRefundFee.multiply(new BigDecimal(-1)));
        // 拉卡拉pos机转账转账
        tbFinance.setLakalaFee(student.lakalaRefundFee.multiply(new BigDecimal(-1)));
        // 支付宝转账转账
        tbFinance.setAliFee(student.aliRefundFee.multiply(new BigDecimal(-1)));

        if (tbFinance.getCashRefundFee() != null) {
            // 现金退款
            tbFinance.setCashRefundFee(tbFinance.getCashRefundFee().add(student.cashRefundFee));
        } else {
            // 现金退款
            tbFinance.setCashRefundFee(student.cashRefundFee);
        }
        if (tbFinance.getBankRefundFee() != null) {
            // 银行退款
            tbFinance.setBankRefundFee(tbFinance.getBankRefundFee().add(student.bankRefundFee));
        } else {
            // 银行退款
            tbFinance.setBankRefundFee(student.bankRefundFee);
        }
        if (tbFinance.getLakalaRefundFee() != null) {
            // 拉卡拉pos机退款
            tbFinance.setLakalaRefundFee(tbFinance.getLakalaRefundFee().add(student.lakalaRefundFee));
        } else {
            // 拉卡拉pos机退款
            tbFinance.setLakalaRefundFee(student.lakalaRefundFee);
        }
        if (tbFinance.getAliRefundFee() != null) {
            // 支付宝退款
            tbFinance.setAliRefundFee(tbFinance.getAliRefundFee().add(student.aliRefundFee));
        } else {
            // 支付宝退款
            tbFinance.setAliRefundFee(student.aliRefundFee);
        }
        // 撤销状态
        tbFinance.setCancelflg("0");
        // 账单类型
        tbFinance.setCrashHistoryType("退费");

        tbFinance.setName(tbStudent.getName());
        tbFinance.setIdNum(tbStudent.getIdNum());
        if(student.getClassType()!=null) {
            tbFinance.setTbClassType(classTypeDao.getById(TbClassType.class, student.getClassType().trim()));
        } else {
            tbFinance.setTbClassType(tbStudent.getTbClassType());
        }
        tbFinance.setTbUser(userDao.getById(TbUser.class, sessionInfo.getId()));
        tbFinance.setCreatedatetime(new Date());
        tbFinance.setStudentId(tbStudent.getId());
        financeDao.save(tbFinance);
    }

    @Override
    public TbStudent fileDownload(String attachid) {
        return studentDao.getById(TbStudent.class, attachid.trim());
    }

    @Override
    public TbStudent idFileDownload(String attachid) {
        return studentDao.getById(TbStudent.class, attachid.trim());
    }

    @Override
    public TbStudent reportFileDownload(String attachid) {
        return studentDao.getById(TbStudent.class, attachid.trim());
    }

    @Override
    public TbStudent getInfoFileDownload(String attachid) {
        return studentDao.getById(TbStudent.class, attachid.trim());
    }

    @Override
    public String deleteStudent(String attachid,TbUser tbUser) {
        TbStudent tbStudent = studentDao.getById(TbStudent.class,
                attachid.trim());
        TbStudentInfoHistory tbStuHis = new TbStudentInfoHistory();
        tbStuHis.setId(UUID.randomUUID().toString());
        tbStuHis.setStudentId(tbStudent.getId());
        tbStuHis.setIdNum(tbStudent.getIdNum());
        tbStuHis.setName(tbStudent.getName());
        tbStuHis.setNum(tbStudent.getNum());
        tbStuHis.setTbUser(tbUser);
        tbStuHis.setCreatedatetime(tbStudent.getCreatedatetime());
        tbStuHis.setUpdatedatetime(new Date());
        tbStuHis.setClassTypeName(tbStudent.getTbClassType().getClassType());
        tbStuHis.setClassTypeId(tbStudent.getTbClassType().getId());
        tbStuHis.setUpdateType("删除");
        tbStuHis.setUpdateContent(tbStudent.toString());
        try {
            try {
                if (tbStudent.getTbFile() != null) {
                    File file = new File(tbStudent.getTbFile().getFilePath());
                    file.delete();
                }
            } catch (Exception e) {

            }

            try {
                fileDao.delete(tbStudent.getTbFile());
            } catch (Exception e) {

            }

            try {
                if (tbStudent.getTbIdFile() != null) {
                    File file = new File(tbStudent.getTbIdFile().getFilePath());
                    file.delete();
                }
            } catch (Exception e) {

            }

            try {
                idFileDaoI.delete(tbStudent.getTbIdFile());
            } catch (Exception e) {

            }

            try {
                if (tbStudent.getTbReportFile() != null) {
                    File file = new File(tbStudent.getTbReportFile().getFilePath());
                    file.delete();
                }
            } catch (Exception e) {

            }

            try {
                reportFileDaoI.delete(tbStudent.getTbReportFile());
            } catch (Exception e) {

            }

        } catch (Exception e) {
            return "fail";
        } finally {
            studentDao.delete(tbStudent);
            studentInfoHistoryDao.save(tbStuHis);
        }
        return "success";
    }
}
