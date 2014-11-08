package hs.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbStudent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_student", catalog = "highschool")
public class TbStudent implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // Fields
    private String id;
    private String num;
    private TbPreferential tbPreferential;
    private TbFile tbFile;
    private TbPhotoFile tbPhotoFile;
    private TbReportFile tbReportFile;
    private TbIdFile tbIdFile;
    private TbClassType tbClassType;
    private TbUser tbUser;
    private String name;
    private String sex;
    private String nation;
    private String signedFlg;
    private String tel;
    private String intention;
    private String wlqf;
    private String studentType;
    private String photo;
    private String fatherName;
    private String fatherTel;
    private String fatherWork;
    private String motherName;
    private String motherTel;
    private String motherWork;
    private String idNum;
    private String address;
    private String homeTel;
    private String graduateSchool;
    private String fractionLanguage;
    private String fractionMath;
    private String fractionEnglish;
    private String fractionComp1;
    private String fractionComp2;
    private String fractionComp3;
    private String fractionCompCount;
    private String fractionCount;
    private String className;
    private String stayFlg;
    private String stayNum;
    private String stayTel;
    private String stuNum;
    private String selfstudyNightflg;
    private String selfstudyNoonflg;
    private String remark;
    private Date createdatetime;
    private Date modifydatetime;
    private String year;
    private BigDecimal studyFee;
    private BigDecimal stayFee;
    private BigDecimal selfFee;
    private BigDecimal signFee;
    private BigDecimal scoreFee;
    private BigDecimal safetyFee;
    private BigDecimal waterFee;
    private BigDecimal countFee;
    private String arrearflg;
    private String dormitoryNum;
    private String signUpMoneyFlg;
    private String signUpMoneyContent;
    private String secureFlg;
    private String bankSignUpMoneyFlg;
    private String lakalaSignUpMoneyFlg;
    private String aliSignUpMoneyFlg;
    private String secureContent;
    private String artType;

    private String wlqfContent;
    private String stuTypeContent;
    private String sexContent;
    private String signedContent;
    private String stayContent;
    private String selfstudyNightContent;
    private String selfstudyNoonContent;
    private String oldFileName;
    private String oldIdFileName;
    private String oldPhotoFileName;
    private String oldReportFileName;
    private String classTypeName;
    private String isPaymentFlg;
    //欠款
    private BigDecimal arrearFee;
    //退款
    private BigDecimal refundFee;
    //优惠
    private BigDecimal preferentialFee;

    // Constructors

    /** default constructor */
    public TbStudent() {
    }

    /** minimal constructor */
    public TbStudent(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbStudent(String id, String num, TbPreferential tbPreferential, TbFile tbFile,TbIdFile tbIdFile,TbReportFile tbReportFile,TbPhotoFile tbPhotoFile, TbClassType tbClassType, TbUser tbUser, String name, String sex, String nation, String signedFlg, String tel, String intention, String wlqf, String photo, String fatherName, String fatherTel, String fatherWork, String motherName, String motherTel, String motherWork, String idNum, String address, String homeTel, String graduateSchool, String fractionLanguage, String fractionMath, String fractionEnglish, String fractionComp1, String fractionComp2, String fractionComp3, String fractionCompCount, String fractionCount, String className, String stayFlg, String stayNum, String stayTel, String stuNum, String selfstudyNightflg, String selfstudyNoonflg, String remark, Date createdatetime, Date modifydatetime, String year, BigDecimal studyFee, BigDecimal stayFee, BigDecimal selfFee, BigDecimal signFee, BigDecimal scoreFee, BigDecimal safetyFee, BigDecimal waterFee, BigDecimal countFee, String arrearflg, String isPaymentFlg,BigDecimal arrearFee,BigDecimal refundFee,BigDecimal preferentialFee) {
        this.id = id;
        this.num = num;
        this.tbPreferential = tbPreferential;
        this.tbFile = tbFile;
        this.tbIdFile = tbIdFile;
        this.tbReportFile = tbReportFile;
        this.tbPhotoFile = tbPhotoFile;
        this.tbClassType = tbClassType;
        this.tbUser = tbUser;
        this.name = name;
        this.sex = sex;
        this.nation=nation;
        this.signedFlg=signedFlg;
        this.tel=tel;
        this.intention=intention;
        this.wlqf = wlqf;
        this.photo = photo;
        this.fatherName = fatherName;
        this.fatherTel = fatherTel;
        this.fatherWork = fatherWork;
        this.motherName = motherName;
        this.motherTel = motherTel;
        this.motherWork = motherWork;
        this.idNum = idNum;
        this.address = address;
        this.homeTel = homeTel;
        this.graduateSchool = graduateSchool;
        this.fractionLanguage = fractionLanguage;
        this.fractionMath = fractionMath;
        this.fractionEnglish = fractionEnglish;
        this.fractionComp1 = fractionComp1;
        this.fractionComp2 = fractionComp2;
        this.fractionComp3 = fractionComp3;
        this.fractionCompCount = fractionCompCount;
        this.fractionCount = fractionCount;
        this.className = className;
        this.stayFlg = stayFlg;
        this.stayNum = stayNum;
        this.stayTel = stayTel;
        this.stuNum = stuNum;
        this.selfstudyNightflg = selfstudyNightflg;
        this.selfstudyNoonflg = selfstudyNoonflg;
        this.remark = remark;
        this.createdatetime = createdatetime;
        this.modifydatetime = modifydatetime;
        this.year = year;
        this.studyFee = studyFee;
        this.stayFee = stayFee;
        this.selfFee = selfFee;
        this.signFee = signFee;
        this.scoreFee = scoreFee;
        this.safetyFee = safetyFee;
        this.waterFee = waterFee;
        this.countFee = countFee;
        this.arrearflg = arrearflg;
        this.isPaymentFlg = isPaymentFlg;
        this.arrearFee = arrearFee;
        this.refundFee = refundFee;
        this.preferentialFee = preferentialFee;
    }

    // Property accessors
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preferential")
    public TbPreferential getTbPreferential() {
        return this.tbPreferential;
    }

    public void setTbPreferential(TbPreferential tbPreferential) {
        this.tbPreferential = tbPreferential;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    public TbFile getTbFile() {
        return this.tbFile;
    }

    public void setTbFile(TbFile tbFile) {
        this.tbFile = tbFile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_file_id")
    public TbPhotoFile getTbPhotoFile() {
        return this.tbPhotoFile;
    }

    public void setTbPhotoFile(TbPhotoFile tbPhotoFile) {
        this.tbPhotoFile = tbPhotoFile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_file_id")
    public TbReportFile getTbReportFile() {
        return this.tbReportFile;
    }

    public void setTbReportFile(TbReportFile tbReportFile) {
        this.tbReportFile = tbReportFile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_file_id")
    public TbIdFile getTbIdFile() {
        return this.tbIdFile;
    }

    public void setTbIdFile(TbIdFile tbIdFile) {
        this.tbIdFile = tbIdFile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_type")
    public TbClassType getTbClassType() {
        return this.tbClassType;
    }

    public void setTbClassType(TbClassType tbClassType) {
        this.tbClassType = tbClassType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payee")
    public TbUser getTbUser() {
        return this.tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }

    @Column(name = "name", length = 10)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "sex", length = 2)
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "wlqf", length = 2)
    public String getWlqf() {
        return this.wlqf;
    }

    public void setWlqf(String wlqf) {
        this.wlqf = wlqf;
    }

    @Column(name = "photo", length = 100)
    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "father_name", length = 10)
    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Column(name = "father_tel", length = 20)
    public String getFatherTel() {
        return this.fatherTel;
    }

    public void setFatherTel(String fatherTel) {
        this.fatherTel = fatherTel;
    }

    @Column(name = "father_work", length = 100)
    public String getFatherWork() {
        return this.fatherWork;
    }

    public void setFatherWork(String fatherWork) {
        this.fatherWork = fatherWork;
    }

    @Column(name = "mother_name", length = 10)
    public String getMotherName() {
        return this.motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    @Column(name = "mother_tel", length = 20)
    public String getMotherTel() {
        return this.motherTel;
    }

    public void setMotherTel(String motherTel) {
        this.motherTel = motherTel;
    }

    @Column(name = "mother_work", length = 100)
    public String getMotherWork() {
        return this.motherWork;
    }

    public void setMotherWork(String motherWork) {
        this.motherWork = motherWork;
    }

    @Column(name = "id_num", length = 30)
    public String getIdNum() {
        return this.idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Column(name = "address", length = 100)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "home_tel", length = 20)
    public String getHomeTel() {
        return this.homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    @Column(name = "graduate_school", length = 100)
    public String getGraduateSchool() {
        return this.graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    @Column(name = "fraction_language", length = 5)
    public String getFractionLanguage() {
        return this.fractionLanguage;
    }

    public void setFractionLanguage(String fractionLanguage) {
        this.fractionLanguage = fractionLanguage;
    }

    @Column(name = "fraction_math", length = 5)
    public String getFractionMath() {
        return this.fractionMath;
    }

    public void setFractionMath(String fractionMath) {
        this.fractionMath = fractionMath;
    }

    @Column(name = "fraction_english", length = 5)
    public String getFractionEnglish() {
        return this.fractionEnglish;
    }

    public void setFractionEnglish(String fractionEnglish) {
        this.fractionEnglish = fractionEnglish;
    }

    @Column(name = "fraction_comp1", length = 5)
    public String getFractionComp1() {
        return this.fractionComp1;
    }

    public void setFractionComp1(String fractionComp1) {
        this.fractionComp1 = fractionComp1;
    }

    @Column(name = "fraction_comp2", length = 5)
    public String getFractionComp2() {
        return this.fractionComp2;
    }

    public void setFractionComp2(String fractionComp2) {
        this.fractionComp2 = fractionComp2;
    }

    @Column(name = "fraction_comp3", length = 5)
    public String getFractionComp3() {
        return this.fractionComp3;
    }

    public void setFractionComp3(String fractionComp3) {
        this.fractionComp3 = fractionComp3;
    }

    @Column(name = "fraction_comp_count", length = 5)
    public String getFractionCompCount() {
        return this.fractionCompCount;
    }

    public void setFractionCompCount(String fractionCompCount) {
        this.fractionCompCount = fractionCompCount;
    }

    @Column(name = "fraction_count", length = 5)
    public String getFractionCount() {
        return this.fractionCount;
    }

    public void setFractionCount(String fractionCount) {
        this.fractionCount = fractionCount;
    }

    @Column(name = "class_name", length = 36)
    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column(name = "stay_flg", length = 2)
    public String getStayFlg() {
        return this.stayFlg;
    }

    public void setStayFlg(String stayFlg) {
        this.stayFlg = stayFlg;
    }

    @Column(name = "stay_num", length = 36)
    public String getStayNum() {
        return this.stayNum;
    }

    public void setStayNum(String stayNum) {
        this.stayNum = stayNum;
    }

    @Column(name = "stay_tel", length = 30)
    public String getStayTel() {
        return this.stayTel;
    }

    public void setStayTel(String stayTel) {
        this.stayTel = stayTel;
    }

    @Column(name = "stu_num", length = 36)
    public String getStuNum() {
        return this.stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    @Column(name = "selfstudy_nightflg", length = 2)
    public String getSelfstudyNightflg() {
        return this.selfstudyNightflg;
    }

    public void setSelfstudyNightflg(String selfstudyNightflg) {
        this.selfstudyNightflg = selfstudyNightflg;
    }

    @Column(name = "selfstudy_noonflg", length = 2)
    public String getSelfstudyNoonflg() {
        return this.selfstudyNoonflg;
    }

    public void setSelfstudyNoonflg(String selfstudyNoonflg) {
        this.selfstudyNoonflg = selfstudyNoonflg;
    }

    @Column(name = "remark", length = 100)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "createdatetime", length = 19)
    public Date getCreatedatetime() {
        return this.createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    @Column(name = "modifydatetime", length = 19)
    public Date getModifydatetime() {
        return this.modifydatetime;
    }

    public void setModifydatetime(Date modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    @Column(name = "year", length = 4)
    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column(name = "study_fee", precision = 22, scale = 0)
    public BigDecimal getStudyFee() {
        return this.studyFee;
    }

    public void setStudyFee(BigDecimal studyFee) {
        this.studyFee = studyFee;
    }

    @Column(name = "stay_fee", precision = 22, scale = 0)
    public BigDecimal getStayFee() {
        return this.stayFee;
    }

    public void setStayFee(BigDecimal stayFee) {
        this.stayFee = stayFee;
    }

    @Column(name = "self_fee", precision = 22, scale = 0)
    public BigDecimal getSelfFee() {
        return this.selfFee;
    }

    public void setSelfFee(BigDecimal selfFee) {
        this.selfFee = selfFee;
    }

    @Column(name = "sign_fee", precision = 22, scale = 0)
    public BigDecimal getSignFee() {
        return this.signFee;
    }

    public void setSignFee(BigDecimal signFee) {
        this.signFee = signFee;
    }

    @Column(name = "score_fee", precision = 22, scale = 0)
    public BigDecimal getScoreFee() {
        return this.scoreFee;
    }

    public void setScoreFee(BigDecimal scoreFee) {
        this.scoreFee = scoreFee;
    }

    @Column(name = "safety_fee", precision = 22, scale = 0)
    public BigDecimal getSafetyFee() {
        return this.safetyFee;
    }

    public void setSafetyFee(BigDecimal safetyFee) {
        this.safetyFee = safetyFee;
    }

    @Column(name = "water_fee", precision = 22, scale = 0)
    public BigDecimal getWaterFee() {
        return this.waterFee;
    }

    public void setWaterFee(BigDecimal waterFee) {
        this.waterFee = waterFee;
    }

    @Column(name = "count_fee", precision = 22, scale = 0)
    public BigDecimal getCountFee() {
        return this.countFee;
    }

    public void setCountFee(BigDecimal countFee) {
        this.countFee = countFee;
    }

    @Column(name = "arrearflg", length = 1)
    public String getArrearflg() {
        return this.arrearflg;
    }

    public void setArrearflg(String arrearflg) {
        this.arrearflg = arrearflg;
    }

    @Column(name = "num", length = 14)
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Column(name = "nation", length = 40)
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Column(name = "signed_flg", length = 1)
    public String getSignedFlg() {
        return signedFlg;
    }

    public void setSignedFlg(String signedFlg) {
        this.signedFlg = signedFlg;
    }

    @Column(name = "intention", length = 200)
    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    @Column(name = "tel", length = 20)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWlqfContent() {
        return wlqfContent;
    }

    public void setWlqfContent(String wlqfContent) {
        this.wlqfContent = wlqfContent;
    }

    public String getSexContent() {
        return sexContent;
    }

    public void setSexContent(String sexContent) {
        this.sexContent = sexContent;
    }

    public String getSignedContent() {
        return signedContent;
    }

    public void setSignedContent(String signedContent) {
        this.signedContent = signedContent;
    }

    public String getSelfstudyNightContent() {
        return selfstudyNightContent;
    }

    public void setSelfstudyNightContent(String selfstudyNightContent) {
        this.selfstudyNightContent = selfstudyNightContent;
    }

    public String getSelfstudyNoonContent() {
        return selfstudyNoonContent;
    }

    public void setSelfstudyNoonContent(String selfstudyNoonContent) {
        this.selfstudyNoonContent = selfstudyNoonContent;
    }

    public String getStayContent() {
        return stayContent;
    }

    public void setStayContent(String stayContent) {
        this.stayContent = stayContent;
    }

    public String getOldFileName() {
        try {
            this.oldFileName = this.tbFile.getFileName();
        } catch (Exception e) {
            this.oldFileName = "没有上传入学视频";
        }
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public String getOldIdFileName() {
        try {
            this.oldIdFileName = this.tbIdFile.getFileName();
        } catch (Exception e) {
            this.oldIdFileName = "没有身份证扫描件";
        }
        return oldIdFileName;
    }

    public void setOldIdFileName(String oldIdFileName) {
        this.oldIdFileName = oldIdFileName;
    }

    public String getOldReportFileName() {
        try {
            this.oldReportFileName = this.tbReportFile.getFileName();
        } catch (Exception e) {
            this.oldReportFileName = "没有成绩单扫描件";
        }
        return oldReportFileName;
    }

    public void setOldReportFileName(String oldReportFileName) {
        this.oldReportFileName = oldReportFileName;
    }

    public String getOldPhotoFileName() {
        try {
            this.oldPhotoFileName = this.tbPhotoFile.getFileName();
        } catch (Exception e) {
            this.oldPhotoFileName = "没有照片";
        }
        return oldPhotoFileName;
    }

    public void setOldPhotoFileName(String oldPhotoFileName) {
        this.oldPhotoFileName = oldPhotoFileName;
    }

    @Column(name = "dormitory_num", length = 50)
    public String getDormitoryNum() {
        return dormitoryNum;
    }

    public void setDormitoryNum(String dormitoryNum) {
        this.dormitoryNum = dormitoryNum;
    }

    @Column(name = "signup_money_flg", length = 2)
    public String getSignUpMoneyFlg() {
        return signUpMoneyFlg;
    }

    public void setSignUpMoneyFlg(String signUpMoneyFlg) {
        this.signUpMoneyFlg = signUpMoneyFlg;
    }

    @Column(name = "secure_flg", length = 2)
    public String getSecureFlg() {
        return secureFlg;
    }

    public void setSecureFlg(String secureFlg) {
        this.secureFlg = secureFlg;
    }

    @Column(name = "art_type", length = 2)
    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public String getClassTypeName() {
        return classTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }

    public String getSignUpMoneyContent() {
        return signUpMoneyContent;
    }

    public void setSignUpMoneyContent(String signUpMoneyContent) {
        this.signUpMoneyContent = signUpMoneyContent;
    }

    public String getSecureContent() {
        return secureContent;
    }

    public void setSecureContent(String secureContent) {
        this.secureContent = secureContent;
    }

    @Column(name = "isPayment_Flg", length = 1)
    public String getIsPaymentFlg() {
        return isPaymentFlg;
    }

    public void setIsPaymentFlg(String isPaymentFlg) {
        this.isPaymentFlg = isPaymentFlg;
    }

    @Column(name = "arrearFee", precision = 22, scale = 0)
    public BigDecimal getArrearFee() {
        return arrearFee;
    }

    public void setArrearFee(BigDecimal arrearFee) {
        this.arrearFee = arrearFee;
    }

    @Column(name = "refundFee", precision = 22, scale = 0)
    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    @Column(name = "preferentialFee", precision = 22, scale = 0)
    public BigDecimal getPreferentialFee() {
        return preferentialFee;
    }

    public void setPreferentialFee(BigDecimal preferentialFee) {
        this.preferentialFee = preferentialFee;
    }

    @Column(name = "bankSignUpMoneyFlg", length = 1)
    public String getBankSignUpMoneyFlg() {
        return bankSignUpMoneyFlg;
    }

    public void setBankSignUpMoneyFlg(String bankSignUpMoneyFlg) {
        this.bankSignUpMoneyFlg = bankSignUpMoneyFlg;
    }

    @Column(name = "lakalaSignUpMoneyFlg", length = 1)
    public String getLakalaSignUpMoneyFlg() {
        return lakalaSignUpMoneyFlg;
    }

    public void setLakalaSignUpMoneyFlg(String lakalaSignUpMoneyFlg) {
        this.lakalaSignUpMoneyFlg = lakalaSignUpMoneyFlg;
    }

    @Column(name = "aliSignUpMoneyFlg", length = 1)
    public String getAliSignUpMoneyFlg() {
        return aliSignUpMoneyFlg;
    }

    public void setAliSignUpMoneyFlg(String aliSignUpMoneyFlg) {
        this.aliSignUpMoneyFlg = aliSignUpMoneyFlg;
    }

    @Column(name = "studentType", length = 2)
    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("姓名:");
        if(this.name !=null && !"".equals(this.name)) {
            sb.append(this.name);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("学科:");
        if(this.wlqf !=null && !"".equals(this.wlqf)) {
            if ("91".equals(this.wlqf)) {
                sb.append("文科");
            } else if ("95".equals(this.wlqf)) {
                sb.append("理科");
            } else if ("93".equals(this.wlqf)) {
                sb.append("艺术文科");
            } else if ("97".equals(this.wlqf)) {
                sb.append("艺术理科");
            } else if ("94".equals(this.wlqf)) {
                sb.append("体育文科");
            } else if ("98".equals(this.wlqf)) {
                sb.append("体育理科");
            }
        } else {
            sb.append("无");
        }
        sb.append("，");
        sb.append("学生类型:");
        if(this.studentType !=null && !"".equals(this.studentType)) {
            if ("0".equals(this.studentType)) {
                sb.append("复读");
            } else if ("1".equals(this.studentType)) {
                sb.append("应届");
            } else if ("2".equals(this.studentType)) {
                sb.append("往届");
            }
        } else {
            sb.append("无");
        }
        sb.append("，");
        sb.append("考生号:");
        if(this.num !=null && !"".equals(this.num)) {
            sb.append(this.num);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("民族:");
        if(this.nation !=null && !"".equals(this.nation)) {
            sb.append(this.nation);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("性别:");
        if(this.sex !=null && !"".equals(this.sex)) {
            if ("0".equals(this.sex)) {
                sb.append("男");
            } else if ("1".equals(this.sex)) {
                sb.append("女");
            }
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("照片:");
        if (this.tbPhotoFile != null
                && this.tbPhotoFile.getFileName() != null
                && !"".equals(this.tbPhotoFile.getFileName())) {
            sb.append(this.tbPhotoFile.getFileName());
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("签约情况:");
        if(this.signedFlg !=null && !"".equals(this.signedFlg)) {
            if ("0".equals(this.signedFlg)) {
                sb.append("未签约");
            } else if ("1".equals(this.signedFlg)) {
                sb.append("已签约");
            }
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("班级类型:");
        if (this.tbClassType != null
                && this.tbClassType.getClassType() != null
                && !"".equals(this.tbClassType.getClassType())) {
            sb.append(this.tbClassType.getClassType());
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("身份证号:");
        if(this.idNum !=null && !"".equals(this.idNum)) {
            sb.append(this.idNum);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("本人电话:");
        if(this.tel !=null && !"".equals(this.tel)) {
            sb.append(this.tel);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("家庭电话:");
        if(this.homeTel !=null && !"".equals(this.homeTel)) {
            sb.append(this.homeTel);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("毕业学校:");
        if(this.graduateSchool !=null && !"".equals(this.graduateSchool)) {
            sb.append(this.graduateSchool);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("家庭住址:");
        if(this.address !=null && !"".equals(this.address)) {
            sb.append(this.address);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("父亲姓名:");
        if(this.fatherName !=null && !"".equals(this.fatherName)) {
            sb.append(this.fatherName);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("父亲电话：");
        if(this.fatherTel !=null && !"".equals(this.fatherTel)) {
            sb.append(this.fatherTel);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("父亲工作单位：");
        if(this.fatherWork !=null && !"".equals(this.fatherWork)) {
            sb.append(this.fatherWork);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("母亲姓名：");
        if(this.motherName !=null && !"".equals(this.motherName)) {
            sb.append(this.motherName);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("母亲电话：");
        if(this.motherTel !=null && !"".equals(this.motherTel)) {
            sb.append(this.motherTel);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("母亲工作单位：");
        if(this.motherWork !=null && !"".equals(this.motherWork)) {
            sb.append(this.motherWork);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("高考成绩_总分：");
        if(this.fractionCount !=null && !"".equals(this.fractionCount)) {
            sb.append(this.fractionCount);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("高考成绩_语文：");
        if(this.fractionLanguage !=null && !"".equals(this.fractionLanguage)) {
            sb.append(this.fractionLanguage);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("高考成绩_数学：");
        if(this.fractionMath !=null && !"".equals(this.fractionMath)) {
            sb.append(this.fractionMath);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("高考成绩_外语：");
        if(this.fractionEnglish !=null && !"".equals(this.fractionEnglish)) {
            sb.append(this.fractionEnglish);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("高考成绩_历史/物理：");
        if(this.fractionComp1 !=null && !"".equals(this.fractionComp1)) {
            sb.append(this.fractionComp1);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("高考成绩_地理/化学：");
        if(this.fractionComp2 !=null && !"".equals(this.fractionComp2)) {
            sb.append(this.fractionComp2);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("高考成绩_政治/生物：");
        if(this.fractionComp3 !=null && !"".equals(this.fractionComp3)) {
            sb.append(this.fractionComp3);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("高考成绩_综合总分：");
        if(this.fractionCompCount !=null && !"".equals(this.fractionCompCount)) {
            sb.append(this.fractionCompCount);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("班级：");
        if(this.className !=null && !"".equals(this.className)) {
            sb.append(this.className);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("报名费：");
        if(this.signUpMoneyFlg !=null && !"".equals(this.signUpMoneyFlg)) {
            if ("1".equals(this.signUpMoneyFlg)) {
                if(this.bankSignUpMoneyFlg !=null && !"".equals(this.bankSignUpMoneyFlg)) {
                    sb.append("银行交付");
                } else if(this.bankSignUpMoneyFlg !=null && !"".equals(this.bankSignUpMoneyFlg)) {
                    sb.append("拉卡拉pos机交付");
                } else if(this.bankSignUpMoneyFlg !=null && !"".equals(this.bankSignUpMoneyFlg)) {
                    sb.append("支付宝交付");
                } else {
                    sb.append("现金交付");
                }
            } else{
                sb.append("未交");
            }
        } else{
            sb.append("无");
        }
        if (this.stayFlg !=null && !"".equals(this.stayFlg)) {
            if("1".equals(this.stayFlg)) {
                sb.append("，");
                sb.append("住宿");
            } else if ("0".equals(this.stayFlg)) {
                sb.append("，");
                sb.append("走读");
            }
        }
        sb.append("，");
        sb.append("宿舍号：");
        if(this.dormitoryNum !=null && !"".equals(this.dormitoryNum)) {
            sb.append(this.dormitoryNum);
        } else{
            sb.append("无");
        }
        if (this.selfstudyNightflg !=null && !"".equals(this.selfstudyNightflg)) {
            if("1".equals(this.selfstudyNightflg)) {
                sb.append("，");
                sb.append("晚自习");
            }
        }
        if (this.selfstudyNoonflg !=null && !"".equals(this.selfstudyNoonflg)) {
            if("1".equals(this.selfstudyNoonflg)) {
                sb.append("，");
                sb.append("午休");
            }
        }
        sb.append("，");
        sb.append("学号：");
        if(this.stuNum !=null && !"".equals(this.stuNum)) {
            sb.append(this.stuNum);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("预约缴费时间：");
        if(this.intention !=null && !"".equals(this.intention)) {
            sb.append(this.intention);
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("艺术生类型：");
        if(this.artType !=null && !"".equals(this.artType)) {
            sb.append(this.artType);
        } else{
            sb.append("无");
        }
        if (this.secureFlg !=null && !"".equals(this.secureFlg)) {
            if("1".equals(this.secureFlg)) {
                sb.append("，");
                sb.append("保险");
            }
        }
        sb.append("，");
        sb.append("入学视频：");
        if (this.tbFile != null
                && this.tbFile.getFileName() != null
                && !"".equals(this.tbFile.getFileName())) {
            sb.append(this.tbFile.getFileName());
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("成绩单上传：");
        if (this.tbReportFile != null
                && this.tbReportFile.getFileName() != null
                && !"".equals(this.tbReportFile.getFileName())) {
            sb.append(this.tbReportFile.getFileName());
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("身份证上传：");
        if (this.tbIdFile != null
                && this.tbIdFile.getFileName() != null
                && !"".equals(this.tbIdFile.getFileName())) {
            sb.append(this.tbIdFile.getFileName());
        } else{
            sb.append("无");
        }
        sb.append("，");
        sb.append("备注：");
        if(this.remark !=null && !"".equals(this.remark)) {
            sb.append(this.remark);
        } else {
            sb.append("无");
        }
        return sb.toString();
    }

    public String getStuTypeContent() {
        return stuTypeContent;
    }

    public void setStuTypeContent(String stuTypeContent) {
        this.stuTypeContent = stuTypeContent;
    }
}
