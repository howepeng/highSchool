package hs.service.impl;

import hs.dao.ClassTypeDaoI;
import hs.dao.FileDaoI;
import hs.dao.FinanceDaoI;
import hs.dao.IdFileDaoI;
import hs.dao.PreferentialDaoI;
import hs.dao.ReportFileDaoI;
import hs.dao.StudentDaoI;
import hs.dao.UserDaoI;
import hs.model.TbClassType;
import hs.model.TbFinance;
import hs.model.TbPreferential;
import hs.model.TbStudent;
import hs.model.TbUser;
import hs.pageModel.DataGrid;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;
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
        //String hql = "FROM TbStudent t WHERE t.tbClassType is not null";
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
            if (student.getName() != null && !student.getName().trim().equals("") ||
                    student.getCreatedatetimeStart() != null && student.getCreatedatetimeEnd() != null) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            hql += " t.tbClassType.id LIKE :classType";
            params.put("classType", "%%" + student.getClassType() + "%%");
        }
        if (student.fractionCountStart != null && !student.fractionCountStart.trim().equals("")) {
            if (student.getName() != null && !student.getName().trim().equals("") ||
                    student.getCreatedatetimeStart() != null && student.getCreatedatetimeEnd() != null ||
                        student.getClassType() != null && !student.getClassType().trim().equals("")) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            hql += " t.fractionCount >= "+student.fractionCountStart;
        }
        if (student.fractionCountEnd != null && !student.fractionCountEnd.trim().equals("")) {
            if (student.getName() != null && !student.getName().trim().equals("") ||
                    student.getCreatedatetimeStart() != null && student.getCreatedatetimeEnd() != null ||
                        student.getClassType() != null && !student.getClassType().trim().equals("") ||
                                student.fractionCountStart != null && !student.fractionCountStart.trim().equals("")) {
                hql += " AND";
            }else {
                hql += " WHERE";
            }
            hql += " t.fractionCount <= "+student.fractionCountEnd;
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

        boolean isUpdate = true;
        TbFinance tbFinance = getFinance(tbStudent.getId());
        if (tbFinance == null) {
            tbFinance = new TbFinance();
            isUpdate = false;
            tbFinance.setId(UUID.randomUUID().toString());
        }
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
        // 转账
        if(student.getTransferFee() == null) {
            tbFinance.setTransferFee(new BigDecimal(0));
        } else {
            if (tbFinance.getTransferFee() != null) {
                tbFinance.setTransferFee(tbFinance.getTransferFee().add(student.getTransferFee()));
            } else {
                tbFinance.setTransferFee(student.getTransferFee());
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

        tbFinance.setTbClassType(tbClassType);
        tbFinance.setTbUser(userDao.getById(TbUser.class, sessionInfo.getId()));
        tbFinance.setCreatedatetime(new Date());
        tbFinance.setStudentId(tbStudent.getId());
        if (isUpdate) {
            financeDao.update(tbFinance);
        } else {
            financeDao.save(tbFinance);
        }
    }

    private TbFinance getFinance(String id) {
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String createdatetimeStart =df.format(now) + " 00:00:00";
        String createdatetimeEnd =df.format(now) + " 24:00:00";
        String hql = "FROM TbFinance t WHERE t.studentId is '"+id+"' and t.createdatetime >= '"+createdatetimeStart+"' and t.createdatetime <= '"+createdatetimeEnd+"'";
        TbFinance ret = financeDao.get(hql);
        if (ret == null || ret.getId() == null || "".equals(ret.getId())) {
            ret = null;
        }
        return ret;
    }

    @Override
    public void arrearsPay(Student student, SessionInfo sessionInfo) {
        if (student.cashPayAgainFee == null) {
            student.cashPayAgainFee = new BigDecimal(0);
        }

        if (student.transferPayAgainFee == null) {
            student.transferPayAgainFee = new BigDecimal(0);
        }

        BigDecimal arrearFee = student.cashPayAgainFee.add(student.transferPayAgainFee);

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

        boolean isUpdate = true;
        TbFinance tbFinance = getFinance(tbStudent.getId());
        if (tbFinance == null) {
            tbFinance = new TbFinance();
            isUpdate = false;
            tbFinance.setId(UUID.randomUUID().toString());
        }

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
        if(tbFinance.getTransferFee() !=null) {
            // 转账
            tbFinance.setTransferFee(tbFinance.getTransferFee().add(student.transferPayAgainFee));
        } else {
            // 转账
            tbFinance.setTransferFee(student.transferPayAgainFee);
        }
        if (tbFinance.getCashPayAgainFee() != null) {
            // 现金补交
            tbFinance.setCashPayAgainFee(tbFinance.getCashPayAgainFee().add(student.cashPayAgainFee));
        } else {
            // 现金补交
            tbFinance.setCashPayAgainFee(student.cashPayAgainFee);
        }
        if (tbFinance.getTransferPayAgainFee() != null) {
            // 转账补交
            tbFinance.setTransferPayAgainFee(tbFinance.getTransferPayAgainFee().add(student.transferPayAgainFee));
        } else {
            // 转账补交
            tbFinance.setTransferPayAgainFee(student.transferPayAgainFee);
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
        if (isUpdate) {
            financeDao.update(tbFinance);
        } else {
            financeDao.save(tbFinance);
        }
    }


    @Override
    public void refunPay(Student student, SessionInfo sessionInfo) {
        TbStudent tbStudent = studentDao.getById(TbStudent.class, student.getId().trim());
        if (student.cashRefundFee == null) {
            student.cashRefundFee = new BigDecimal(0);
        }

        if (student.transferRefundFee == null) {
            student.transferRefundFee = new BigDecimal(0);
        }

        BigDecimal refundFee = student.cashRefundFee.add(student.transferRefundFee);

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

        boolean isUpdate = true;
        TbFinance tbFinance = getFinance(tbStudent.getId());
        if (tbFinance == null) {
            tbFinance = new TbFinance();
            isUpdate = false;
            tbFinance.setId(UUID.randomUUID().toString());
            // 退款
            tbFinance.setRefundFee(refundFee);
            // 合计交付
            tbFinance.setCountPayFee(refundFee.multiply(new BigDecimal(-1)));
            // 现金
            tbFinance.setCashFee(student.cashRefundFee.multiply(new BigDecimal(-1)));
            // 转账
            tbFinance.setTransferFee(student.transferRefundFee.multiply(new BigDecimal(-1)));
        } else{
             // 退款
            if (tbFinance.getRefundFee() != null) {
                tbFinance.setRefundFee(tbFinance.getRefundFee().add(refundFee));
            } else {
                tbFinance.setRefundFee(refundFee);
            }
            // 合计交付
            if (tbFinance.getCountPayFee() != null) {
                tbFinance.setCountPayFee(tbFinance.getCountPayFee().subtract(refundFee));
            } else {
                tbFinance.setCountPayFee(refundFee.multiply(new BigDecimal(-1)));
            }
            if (tbFinance.getCashFee() != null) {
                // 现金
                tbFinance.setCashFee(tbFinance.getCashFee().subtract(student.cashRefundFee));
            } else {
                // 现金
                tbFinance.setCashFee(student.cashRefundFee.multiply(new BigDecimal(-1)));
            }
            if (tbFinance.getTransferFee() != null) {
                // 转账
                tbFinance.setTransferFee(tbFinance.getTransferFee().subtract(student.transferRefundFee));
            } else {
                // 转账
                tbFinance.setTransferFee(student.transferRefundFee.multiply(new BigDecimal(-1)));
            }
        }
        if (tbFinance.getCashRefundFee() != null) {
            // 现金退款
            tbFinance.setCashRefundFee(tbFinance.getCashRefundFee().add(student.cashRefundFee));
        } else {
            // 现金退款
            tbFinance.setCashRefundFee(student.cashRefundFee);
        }
        if (tbFinance.getTransferRefundFee() != null) {
            // 转账退款
            tbFinance.setTransferRefundFee(tbFinance.getTransferRefundFee().add(student.transferRefundFee));
        } else {
            // 转账退款
            tbFinance.setTransferRefundFee(student.transferRefundFee);
        }
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
        if (isUpdate) {
            financeDao.update(tbFinance);
        } else {
            financeDao.save(tbFinance);
        }
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
    public String deleteStudent(String attachid) {
        TbStudent tbStudent = studentDao.getById(TbStudent.class,
                attachid.trim());
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
        }
        return "success";
    }
}
