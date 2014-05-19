package hs.service.impl;

import hs.dao.ClassTypeDaoI;
import hs.dao.FileDaoI;
import hs.dao.FinanceDaoI;
import hs.dao.IdFileDaoI;
import hs.dao.PhotoFileDaoI;
import hs.dao.ReportFileDaoI;
import hs.dao.StudentDaoI;
import hs.dao.UserDaoI;
import hs.model.TbClassType;
import hs.model.TbFile;
import hs.model.TbFinance;
import hs.model.TbIdFile;
import hs.model.TbPhotoFile;
import hs.model.TbReportFile;
import hs.model.TbStudent;
import hs.model.TbUser;
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
    public String signup(Student student, SessionInfo sessionInfo) {
        TbStudent tbStu = null;
        if (student.getId() == null || "".equals(student.getId())) {
            tbStu = new TbStudent();
            TbUser tbUser = userDao.getById(TbUser.class, sessionInfo.getId());
            BeanUtils.copyProperties(student, tbStu);
            tbStu.setId(UUID.randomUUID().toString());
            tbStu.setCreatedatetime(new Date());
            tbStu.setTbUser(tbUser);
            TbClassType tbClassType = classTypeDao.getById(TbClassType.class, student.getClassType());
            tbStu.setTbClassType(tbClassType);
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
                if("1".equals(tbStu.getTransferSignUpMoneyFlg())) {
                    tbFinance.setTransferFee(tbClassType.getSignFee());
                } else {
                    tbFinance.setCashFee(tbClassType.getSignFee());
                }
                financeDao.save(tbFinance);
            }

            if (student.getPhotoId() != null
                    && !"".equals(student.getPhotoId())) {
                TbPhotoFile tbFile = photoFileDao.getById(TbPhotoFile.class,
                        student.getPhotoId());
                tbStu.setTbPhotoFile(tbFile);
            }
        } else {
            tbStu = studentDao.getById(TbStudent.class, student.getId());
            TbUser tbUser = userDao.getById(TbUser.class, sessionInfo.getId());
            String oldSignUpMoneyFlg = tbStu.getSignUpMoneyFlg();
            BigDecimal oldCountFee = tbStu.getCountFee();
            String oldIsPaymentFlg = tbStu.getIsPaymentFlg();
            Date createdatetime = tbStu.getCreatedatetime();
            BeanUtils.copyProperties(student, tbStu);
            tbStu.setCreatedatetime(createdatetime);
            tbStu.setModifydatetime(new Date());
            tbStu.setTbUser(tbUser);
            TbClassType tbClassType = classTypeDao.getById(TbClassType.class, student.getClassType());
            tbStu.setTbClassType(tbClassType);
            if ("1".equals(tbStu.getSignUpMoneyFlg())
                    && "0".equals(oldSignUpMoneyFlg)) {
                tbStu.setSignFee(tbClassType.getSignFee());
                tbStu.setCountFee(oldCountFee.add(tbClassType.getSignFee()));
            }
            if ("0".equals(tbStu.getSignUpMoneyFlg())
                    && "1".equals(oldSignUpMoneyFlg)) {
                tbStu.setSignFee(null);
                tbStu.setCountFee(oldCountFee.subtract(tbClassType.getSignFee()));
            }
            tbStu.setIsPaymentFlg(oldIsPaymentFlg);
            studentDao.update(tbStu);
            if ("1".equals(tbStu.getSignUpMoneyFlg())
                    && "0".equals(oldSignUpMoneyFlg)) {
                boolean isUpdate = true;
                TbFinance tbFinance = getFinance(tbStu.getId());
                if (tbFinance == null) {
                    tbFinance = new TbFinance();
                    isUpdate = false;
                    tbFinance.setId(UUID.randomUUID().toString());
                    tbFinance.setCreatedatetime(new Date());
                    tbFinance.setTbClassType(tbClassType);
                    tbFinance.setName(tbStu.getName());
                    tbFinance.setIdNum(tbStu.getIdNum());
                }
                tbFinance.setSignFee(tbClassType.getSignFee());
                if (tbFinance.getCountPayFee() != null) {
                    tbFinance.setCountPayFee(tbFinance.getCountPayFee().add(tbClassType.getSignFee()));
                } else {
                    tbFinance.setCountPayFee(tbFinance.getCountPayFee());
                }
                if("1".equals(tbStu.getTransferSignUpMoneyFlg())) {
                    if (tbFinance.getTransferFee() != null) {
                        tbFinance.setTransferFee(tbFinance.getTransferFee().add(tbClassType.getSignFee()));
                    } else {
                        tbFinance.setTransferFee(tbClassType.getSignFee());
                    }
                } else {
                    if (tbFinance.getCashFee() != null) {
                        tbFinance.setCashFee(tbFinance.getCashFee().add(tbClassType.getSignFee()));
                    } else {
                        tbFinance.setCashFee(tbClassType.getSignFee());
                    }
                }
                tbFinance.setStudentId(tbStu.getId());
                tbFinance.setTbUser(tbUser);
                if (isUpdate) {
                    financeDao.update(tbFinance);
                } else {
                    financeDao.save(tbFinance);
                }
            }
            if ("0".equals(tbStu.getSignUpMoneyFlg())
                    && "1".equals(oldSignUpMoneyFlg)) {
                TbFinance tbFinance = getFinance(tbStu.getId());
                if (tbFinance != null) {
                    tbFinance.setSignFee(null);
                    if(tbFinance.getCountPayFee()!=null) {
                        tbFinance.setCountPayFee(tbFinance.getCountPayFee().subtract(tbClassType.getSignFee()));
                    } else {
                        tbFinance.setCountPayFee(tbClassType.getSignFee().multiply(new BigDecimal(-1)));
                    }
                    if("1".equals(tbStu.getTransferSignUpMoneyFlg())) {
                        if (tbFinance.getTransferFee() != null) {
                            tbFinance.setTransferFee(tbFinance.getTransferFee().subtract(tbClassType.getSignFee()));
                        } else {
                            tbFinance.setTransferFee(tbClassType.getSignFee().multiply(new BigDecimal(-1)));
                        }
                    } else {
                        if (tbFinance.getCashFee() != null) {
                            tbFinance.setCashFee(tbFinance.getCashFee().add(tbClassType.getSignFee()));
                        } else {
                            tbFinance.setCashFee(tbClassType.getSignFee());
                        }
                    }
                    tbFinance.setTbUser(tbUser);
                    if (tbFinance.getCountPayFee().compareTo(new BigDecimal(0)) > 0) {
                        financeDao.update(tbFinance);
                    } else {
                        financeDao.delete(tbFinance);
                    }
                }

            }
            if (tbStu.getTbPhotoFile()!= null && student.getPhotoId() != null
                    && !"".equals(student.getPhotoId())
                    && !student.getPhotoId().equals(tbStu.getTbPhotoFile().getId())) {
                TbPhotoFile tbFile = photoFileDao.getById(TbPhotoFile.class,
                        student.getPhotoId());
                tbStu.setTbPhotoFile(tbFile);
            }
            upload(student);
        }
        return tbStu.getId();
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
    public void upload(Student student) {
        uploadVideo(student);
        uploadReportFile(student);
        uploadIdFile(student);
    }

    private void uploadVideo(Student student) {
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
            TbStudent tbStu = studentDao.getById(TbStudent.class,
                    student.getId());
            tbStu.setTbFile(tbFile);
        }
    }

    private void uploadReportFile(Student student) {
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
            TbStudent tbStu = studentDao.getById(TbStudent.class,
                    student.getId());
            tbStu.setTbReportFile(tbFile);
        }
    }

    private void uploadIdFile(Student student) {
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
            TbStudent tbStu = studentDao.getById(TbStudent.class,
                    student.getId());
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
