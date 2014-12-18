package hs.service.impl;

import hs.dao.ClassInfoDaoI;
import hs.dao.ClassTypeDaoI;
import hs.dao.FileDaoI;
import hs.dao.FinanceDaoI;
import hs.dao.IdFileDaoI;
import hs.dao.PhotoFileDaoI;
import hs.dao.ReportFileDaoI;
import hs.dao.StudentDaoI;
import hs.dao.StudentInfoHistoryDaoI;
import hs.dao.UserDaoI;
import hs.dao.YearInfoDaoI;
import hs.model.TbClassInfo;
import hs.model.TbClassType;
import hs.model.TbFile;
import hs.model.TbFinance;
import hs.model.TbIdFile;
import hs.model.TbPhotoFile;
import hs.model.TbReportFile;
import hs.model.TbStudent;
import hs.model.TbStudentInfoHistory;
import hs.model.TbUser;
import hs.model.TbYearInfo;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;
import hs.service.StuSignupServiceI;
import hs.util.HSConstants;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stuSignupService")
public class StuSignupServiceImpl implements StuSignupServiceI {

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

    private ClassTypeDaoI classTypeDao;

    @Autowired
    public void setClassTypeDao(ClassTypeDaoI classTypeDao) {
        this.classTypeDao = classTypeDao;
    }

    private ClassInfoDaoI classInfoDao;

    @Autowired
    public void setClassInfoDao(ClassInfoDaoI classInfoDao) {
        this.classInfoDao = classInfoDao;
    }

    private YearInfoDaoI yearInfoDao;

    @Autowired
    public void setYearInfoDao(YearInfoDaoI yearInfoDao) {
        this.yearInfoDao = yearInfoDao;
    }

    private FileDaoI fileDao;

    @Autowired
    public void setFileDao(FileDaoI fileDao) {
        this.fileDao = fileDao;
    }

    private IdFileDaoI idFileDao;

    @Autowired
    public void setIdFileDao(IdFileDaoI idFileDao) {
        this.idFileDao = idFileDao;
    }

    private PhotoFileDaoI photoFileDao;

    @Autowired
    public void setPhotoFileDao(PhotoFileDaoI photoFileDao) {
        this.photoFileDao = photoFileDao;
    }

    private ReportFileDaoI reportFileDao;

    @Autowired
    public void setReportFileDao(ReportFileDaoI reportFileDao) {
        this.reportFileDao = reportFileDao;
    }

    @Override
    public String signup(Student student, SessionInfo sessionInfo) throws Exception {
        TbStudent tbStu = null;
        String updateType = "";
        TbUser tbUser = userDao.getById(TbUser.class, sessionInfo.getId());
        TbClassType tbClassType = null;
        TbClassInfo tbClassInfo = null;
        TbYearInfo tbYearInfo = null;
        TbStudentInfoHistory tbStuHis = new TbStudentInfoHistory();
        if (student.getId() == null || "".equals(student.getId())) {
            tbStu = new TbStudent();
            updateType = "添加";
            BeanUtils.copyProperties(student, tbStu);
            tbStu.setId(UUID.randomUUID().toString());
            tbStu.setCreatedatetime(new Date());
            tbStu.setTbUser(tbUser);
            tbClassType = classTypeDao.getById(TbClassType.class, student.getClassType());
            tbStu.setTbClassType(tbClassType);
            tbClassInfo = classInfoDao.getById(TbClassInfo.class, student.getClassId());
            tbStu.setTbClassInfo(tbClassInfo);
            tbYearInfo = yearInfoDao.getById(TbYearInfo.class, sessionInfo.getYearId());
            tbStu.setTbYearInfo(tbYearInfo);
            tbStu.setScore(100);
            if ("1".equals(tbStu.getSignUpMoneyFlg())) {
                tbStu.setSignFee(tbClassType.getSignFee());
                tbStu.setCountFee(tbClassType.getSignFee());
            }
            tbStu.setIsPaymentFlg("0");
            studentDao.save(tbStu);
            if ("1".equals(tbStu.getSignUpMoneyFlg())) {
                TbFinance tbFinance = new TbFinance();
                tbFinance.setId(UUID.randomUUID().toString());
                tbFinance.setCreatedatetime(new Date());
                tbFinance.setSignFee(tbClassType.getSignFee());
                tbFinance.setCountPayFee(tbClassType.getSignFee());
                tbFinance.setTbUser(tbUser);
                tbFinance.setStudentId(tbStu.getId());
                tbFinance.setName(tbStu.getName());
                tbFinance.setIdNum(tbStu.getIdNum());
                tbFinance.setTbClassType(tbStu.getTbClassType());
                if("1".equals(tbStu.getBankSignUpMoneyFlg())) {
                    tbFinance.setBankFee(tbClassType.getSignFee());
                } else if("1".equals(tbStu.getLakalaSignUpMoneyFlg())) {
                    tbFinance.setLakalaFee(tbClassType.getSignFee());
                }else if("1".equals(tbStu.getAliSignUpMoneyFlg())) {
                    tbFinance.setAliFee(tbClassType.getSignFee());
                }else {
                    tbFinance.setCashFee(tbClassType.getSignFee());
                }
                // 撤销状态
                tbFinance.setCancelflg("0");
                // 账单类型
                tbFinance.setCrashHistoryType("报名费");
                financeDao.save(tbFinance);
            }
            if (student.getPhotoId() != null
                    && !"".equals(student.getPhotoId())) {
                TbPhotoFile tbFile = photoFileDao.getById(TbPhotoFile.class,
                        student.getPhotoId());
                tbStu.setTbPhotoFile(tbFile);
            }
            upload(student, tbStu);
        } else {
            updateType = "修改";
            tbStu = studentDao.getById(TbStudent.class, student.getId());
            String oldSignUpMoneyFlg = tbStu.getSignUpMoneyFlg();
            BigDecimal oldCountFee = tbStu.getCountFee();
            String oldIsPaymentFlg = tbStu.getIsPaymentFlg();
            Date createdatetime = tbStu.getCreatedatetime();
            String oldBankSignUpMoneyFlg = tbStu.getBankSignUpMoneyFlg();
            String oldLakalaSignUpMoneyFlg= tbStu.getLakalaSignUpMoneyFlg();
            String oldAliSignUpMoneyFlg = tbStu.getAliSignUpMoneyFlg();
            BigDecimal oldSignFee =  tbStu.getSignFee();
            setStudent(student, tbStu);
            tbStu.setCreatedatetime(createdatetime);
            tbStu.setModifydatetime(new Date());
            tbStu.setTbUser(tbUser);
            tbClassType = classTypeDao.getById(TbClassType.class, student.getClassType());
            tbStu.setTbClassType(tbClassType);
            tbClassInfo = classInfoDao.getById(TbClassInfo.class, student.getClassId());
            tbStu.setTbClassInfo(tbClassInfo);
            tbYearInfo = yearInfoDao.getById(TbYearInfo.class, student.getYearId());
            tbStu.setTbYearInfo(tbYearInfo);
            if ("1".equals(tbStu.getSignUpMoneyFlg())
                    && "0".equals(oldSignUpMoneyFlg)) {
                tbStu.setSignFee(tbClassType.getSignFee());
                tbStu.setCountFee(oldCountFee.add(tbClassType.getSignFee()));
            }
            if ("0".equals(tbStu.getSignUpMoneyFlg())
                    && "1".equals(oldSignUpMoneyFlg)) {
                tbStu.setSignFee(new BigDecimal(0));
                tbStu.setCountFee(oldCountFee.subtract(tbClassType.getSignFee()));
            }
            tbStu.setIsPaymentFlg(oldIsPaymentFlg);

            if ("1".equals(tbStu.getSignUpMoneyFlg())
                    && "0".equals(oldSignUpMoneyFlg)) {
                TbFinance tbFinance = new TbFinance();
                tbFinance.setId(UUID.randomUUID().toString());
                tbFinance.setCreatedatetime(new Date());
                tbFinance.setSignFee(tbClassType.getSignFee());
                tbFinance.setCountPayFee(tbClassType.getSignFee());
                tbFinance.setTbUser(tbUser);
                tbFinance.setStudentId(tbStu.getId());
                tbFinance.setName(tbStu.getName());
                tbFinance.setIdNum(tbStu.getIdNum());
                tbFinance.setTbClassType(tbStu.getTbClassType());
                if("1".equals(tbStu.getBankSignUpMoneyFlg())) {
                    tbFinance.setBankFee(tbClassType.getSignFee());
                } else if("1".equals(tbStu.getLakalaSignUpMoneyFlg())) {
                    tbFinance.setLakalaFee(tbClassType.getSignFee());
                }else if("1".equals(tbStu.getAliSignUpMoneyFlg())) {
                    tbFinance.setAliFee(tbClassType.getSignFee());
                }else {
                    tbFinance.setCashFee(tbClassType.getSignFee());
                }
                // 撤销状态
                tbFinance.setCancelflg("0");
                // 账单类型
                tbFinance.setCrashHistoryType("报名费");
                financeDao.save(tbFinance);
            }
            if ("0".equals(tbStu.getSignUpMoneyFlg())
                    && "1".equals(oldSignUpMoneyFlg)) {
                TbFinance tbFinance = getFinance(tbStu.getId());
                if (tbFinance != null) {
                    tbStu.setSignUpMoneyFlg("0");
                    tbStu.setBankSignUpMoneyFlg("0");
                    tbStu.setLakalaSignUpMoneyFlg("0");
                    tbStu.setAliSignUpMoneyFlg("0");
                    tbFinance.setCancelflg("1");
                    financeDao.update(tbFinance);
                } else {
                    throw new Exception("报名费已出账不能修改报名信息的【是否缴纳报名费】！");
                }
            }
            if ("1".equals(tbStu.getSignUpMoneyFlg())
                    && "1".equals(oldSignUpMoneyFlg)) {
                if(!oldBankSignUpMoneyFlg.equals(tbStu.getBankSignUpMoneyFlg())
                    || !oldLakalaSignUpMoneyFlg.equals(tbStu.getLakalaSignUpMoneyFlg())
                    || !oldAliSignUpMoneyFlg.equals(tbStu.getAliSignUpMoneyFlg())) {
                        TbFinance tbFinance = getFinance(tbStu.getId());
                        if (tbFinance != null) {
                            if("1".equals(tbStu.getBankSignUpMoneyFlg())) {
                                tbFinance.setBankFee(tbClassType.getSignFee());
                            } else {
                                tbFinance.setBankFee(new BigDecimal(0));
                            }
                            if("1".equals(tbStu.getLakalaSignUpMoneyFlg())) {
                                tbFinance.setLakalaFee(tbClassType.getSignFee());
                            } else {
                                tbFinance.setLakalaFee(new BigDecimal(0));
                            }
                            if("1".equals(tbStu.getAliSignUpMoneyFlg())) {
                                tbFinance.setAliFee(tbClassType.getSignFee());
                            } else {
                                tbFinance.setAliFee(new BigDecimal(0));
                            }
                            if (!"1".equals(tbStu.getBankSignUpMoneyFlg())
                                && !"1".equals(tbStu.getLakalaSignUpMoneyFlg())
                                && !"1".equals(tbStu.getAliSignUpMoneyFlg())) {
                                tbFinance.setCashFee(tbClassType.getSignFee());
                            } else {
                                tbFinance.setCashFee(new BigDecimal(0));
                            }
                            tbFinance.setCreatedatetime(new Date());
                            tbFinance.setCountPayFee(tbClassType.getSignFee());
                            tbStu.setSignFee(tbClassType.getSignFee());
                            //合计 = 之前的合计-之前的报名费+新的报名费
                            tbStu.setCountFee(tbStu.getCountFee().subtract(oldSignFee).add(tbClassType.getSignFee()));
                            financeDao.update(tbFinance);
                        } else {
                            throw new Exception("报名费已出账不能修改报名信息的【报名交付方式】！");
                        }
                }

            }
            studentDao.update(tbStu);
            if (tbStu.getTbPhotoFile()!= null && student.getPhotoId() != null
                    && !"".equals(student.getPhotoId())
                    && !student.getPhotoId().equals(tbStu.getTbPhotoFile().getId())) {
                TbPhotoFile tbFile = photoFileDao.getById(TbPhotoFile.class,
                        student.getPhotoId());
                tbStu.setTbPhotoFile(tbFile);
            }
            upload(student, tbStu);
        }
        tbStuHis.setId(UUID.randomUUID().toString());
        tbStuHis.setStudentId(tbStu.getId());
        tbStuHis.setIdNum(tbStu.getIdNum());
        tbStuHis.setName(tbStu.getName());
        tbStuHis.setNum(tbStu.getNum());
        tbStuHis.setTbUser(tbUser);
        tbStuHis.setCreatedatetime(tbStu.getCreatedatetime());
        tbStuHis.setUpdatedatetime(new Date());
        tbStuHis.setClassTypeName(tbClassType.getClassType());
        tbStuHis.setClassTypeId(student.getClassType());
        tbStuHis.setUpdateType(updateType);
        tbStuHis.setUpdateContent(tbStu.toString());
        studentInfoHistoryDao.save(tbStuHis);
        return tbStu.getId();
    }

    /**
     * 复制学生信息
     * @param student
     * @param tbStu
     */
    private void setStudent(Student student, TbStudent tbStu) {
        if (tbStu.getId().equals(student.getId())) {
            tbStu.setNum(student.getNum());
            tbStu.setName(student.getName());
            tbStu.setWlqf(student.getWlqf());
            tbStu.setStudentType(student.getStudentType());
            tbStu.setSex(student.getSex());
            tbStu.setSignedFlg(student.getSignedFlg());
            tbStu.setIdNum(student.getIdNum());
            tbStu.setTel(student.getTel());
            tbStu.setHomeTel(student.getHomeTel());
            tbStu.setGraduateSchool(student.getGraduateSchool());
            tbStu.setAddress(student.getAddress());
            tbStu.setFatherName(student.getFatherName());
            tbStu.setFatherTel(student.getFatherTel());
            tbStu.setFatherWork(student.getFatherWork());
            tbStu.setMotherName(student.getMotherName());
            tbStu.setMotherTel(student.getMotherTel());
            tbStu.setMotherWork(student.getMotherWork());
            tbStu.setFractionLanguage(student.getFractionLanguage());
            tbStu.setFractionMath(student.getFractionMath());
            tbStu.setFractionEnglish(student.getFractionEnglish());
            tbStu.setFractionComp1(student.getFractionComp1());
            tbStu.setFractionComp2(student.getFractionComp2());
            tbStu.setFractionComp3(student.getFractionComp3());
            tbStu.setFractionCompCount(student.getFractionCompCount());
            tbStu.setFractionCount(student.getFractionCount());
            tbStu.setSignUpMoneyFlg(student.getSignUpMoneyFlg());
            tbStu.setBankSignUpMoneyFlg(student.getBankSignUpMoneyFlg());
            tbStu.setLakalaSignUpMoneyFlg(student.getLakalaSignUpMoneyFlg());
            tbStu.setAliSignUpMoneyFlg(student.getAliSignUpMoneyFlg());
            tbStu.setStayFlg(student.getStayFlg());
            tbStu.setDormitoryNum(student.getDormitoryNum());
            tbStu.setSelfstudyNightflg(student.getSelfstudyNightflg());
            tbStu.setSelfstudyNoonflg(student.getSelfstudyNoonflg());
            tbStu.setStuNum(student.getStuNum());
            tbStu.setIntention(student.getIntention());
            tbStu.setArtType(student.getArtType());
            tbStu.setSecureFlg(student.getSecureFlg());
            tbStu.setOldFileName(student.getOldFileName());
            tbStu.setOldReportFileName(student.getOldReportFileName());
            tbStu.setOldIdFileName(student.getOldIdFileName());
            tbStu.setRemark(student.getRemark());
            tbStu.setScore(student.getScore());
        }
    }

    private TbFinance getFinance(String id) {
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String createdatetimeStart =df.format(now) + " 00:00:00";
        String createdatetimeEnd =df.format(now) + " 24:00:00";
        String hql = "FROM TbFinance t WHERE t.studentId is '"+id + "' and t.createdatetime >= '"+createdatetimeStart
                +"' and t.createdatetime <= '"+createdatetimeEnd + "' and t.crashHistoryType='报名费'";
        TbFinance ret = financeDao.get(hql);
        if (ret == null || ret.getId() == null || "".equals(ret.getId())) {
            ret = null;
        }
        return ret;
    }

    @Override
    public void upload(Student student, TbStudent tbStu) {
        uploadVideo(student, tbStu);
        uploadReportFile(student, tbStu);
        uploadIdFile(student, tbStu);
    }

    private void uploadVideo(Student student, TbStudent tbStu) {
        String fileName = student.getVideoFileName();
        if (fileName != null && !"".equals(fileName)) {
            String fileRealName = UUID.randomUUID().toString()
                    + getFileType(fileName);
            String filePath = HSConstants.ROOT_PATH+HSConstants.FILE_PATH+"\\video\\" + fileRealName;
            TbFile tbFile = new TbFile();
            tbFile.setId(UUID.randomUUID().toString());
            tbFile.setFileName(fileName);
            tbFile.setFilePath(filePath);
            tbFile.setFileRealname(fileRealName);
            fileDao.save(tbFile);
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                // 建立文件输出流
                fos = new FileOutputStream(filePath);
                // 建立文件上传流
                fis = new FileInputStream(student.getVideo());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            } catch (Exception e) {
                System.out.println("文件上传失败");
                e.printStackTrace();
            } finally {
                close(fos, fis);
            }
            tbStu.setTbFile(tbFile);
        }
    }

    private void uploadReportFile(Student student, TbStudent tbStu) {
        String fileName = student.getReportFileFileName();
        if (fileName != null && !"".equals(fileName)) {
            String fileRealName = UUID.randomUUID().toString()
                    + getFileType(fileName);
            String filePath = HSConstants.ROOT_PATH+HSConstants.FILE_PATH+"\\report\\" + fileRealName;
            TbReportFile tbFile = new TbReportFile();
            tbFile.setId(UUID.randomUUID().toString());
            tbFile.setFileName(fileName);
            tbFile.setFilePath(filePath);
            tbFile.setFileRealname(fileRealName);
            reportFileDao.save(tbFile);
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                // 建立文件输出流
                fos = new FileOutputStream(filePath);
                // 建立文件上传流
                fis = new FileInputStream(student.getReportFile());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            } catch (Exception e) {
                System.out.println("文件上传失败");
                e.printStackTrace();
            } finally {
                close(fos, fis);
            }
            tbStu.setTbReportFile(tbFile);
        }
    }

    private void uploadIdFile(Student student, TbStudent tbStu) {
        String fileName = student.getIdNUmFileFileName();
        if (fileName != null && !"".equals(fileName)) {
            String fileRealName = UUID.randomUUID().toString()
                    + getFileType(fileName);
            String filePath = HSConstants.ROOT_PATH+HSConstants.FILE_PATH+"\\id\\" + fileRealName;
            TbIdFile tbFile = new TbIdFile();
            tbFile.setId(UUID.randomUUID().toString());
            tbFile.setFileName(fileName);
            tbFile.setFilePath(filePath);
            tbFile.setFileRealname(fileRealName);
            idFileDao.save(tbFile);
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                // 建立文件输出流
                fos = new FileOutputStream(filePath);
                // 建立文件上传流
                fis = new FileInputStream(student.getIdNUmFile());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            } catch (Exception e) {
                System.out.println("文件上传失败");
                e.printStackTrace();
            } finally {
                close(fos, fis);
            }
            tbStu.setTbIdFile(tbFile);
        }
    }

    @Override
    public String[] uploadPhoto(Student student, String path) {
        String[] ret = null;
        String fileName = student.getPhotoFileFileName();
        if (fileName != null && !"".equals(fileName)) {
            ret= new String[2];
            String fileRealName = UUID.randomUUID().toString()
                    + getFileType(fileName);
            String filePath = path + "\\photo\\" + fileRealName;
            TbPhotoFile tbPhotoFile = new TbPhotoFile();
            tbPhotoFile.setId(UUID.randomUUID().toString());
            tbPhotoFile.setFileName(fileName);
            tbPhotoFile.setFilePath("photo/"+fileRealName);
            tbPhotoFile.setFileRealname(fileRealName);
            photoFileDao.save(tbPhotoFile);
            ret[0] = tbPhotoFile.getId();
            ret[1] = "photo/"+fileRealName;
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                // 建立文件输出流
                fos = new FileOutputStream(filePath);
                // 建立文件上传流
                fis = new FileInputStream(student.getPhotoFile());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            } catch (Exception e) {
                System.out.println("文件上传失败");
                e.printStackTrace();
            } finally {
                close(fos, fis);
            }
        }
        return ret;
    }

    @Override
    public Long getStudentCount() {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbStudent";
        return studentDao.count("SELECT count(*) " + hql, params);
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
}
