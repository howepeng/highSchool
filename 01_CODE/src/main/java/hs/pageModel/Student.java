package hs.pageModel;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Student {

    private String id;
    private String num;
    private String classType;
    private String preferential;
    private String payee;
    private String name;
    private String sex;
    private String nation;
    private String signedFlg = "0";
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
    private String tel;
    private String intention;
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
    private String stayFlg = "0";
    private String stayNum;
    private String stayTel;
    private String stuNum;
    private String selfstudyNightflg = "0";
    private String selfstudyNoonflg = "0";
    private String remark;
    private Timestamp createdatetime;
    private Timestamp createdatetimeStart;
    private Timestamp createdatetimeEnd;
    private Timestamp modifydatetime;
    private String year;
    private BigDecimal studyFee = new BigDecimal(0);
    private BigDecimal stayFee = new BigDecimal(0);
    private BigDecimal selfFee = new BigDecimal(0);
    private BigDecimal signFee = new BigDecimal(0);
    private BigDecimal scoreFee = new BigDecimal(0);
    private BigDecimal waterFee = new BigDecimal(0);
    private BigDecimal safetyFee = new BigDecimal(0);
    private BigDecimal countFee = new BigDecimal(0);
    private String arrearflg = "0";
    private BigDecimal arrearFee = new BigDecimal(0);
    private int page;
    private int rows;
    private String sort;
    private String order;
    private String ids;// 删除用 id字符串（用逗号分隔）
    private File video;// 上传的文件
    private String videoContentType; // 封装上传文件类型的属性
    private String videoFileName; // 封装上传文件名的属性

    private String wlqfContent;
    private String stuTypeContent;
    private String sexContent;
    private String oldFileName;

    private File reportFile;// 上传的文件
    private String reportFileContentType; // 封装上传文件类型的属性
    private String reportFileFileName; // 封装上传文件名的属性

    private File idNUmFile;// 上传的文件
    private String idNUmFileContentType; // 封装上传文件类型的属性
    private String idNUmFileFileName; // 封装上传文件名的属性

    private File photoFile;// 上传的文件
    private String photoFileContentType; // 封装上传文件类型的属性
    private String photoFileFileName; // 封装上传文件名的属性
    private String photoId;
    private String photoImgSrc;
    private String dormitoryNum;
    private String signUpMoneyFlg = "0";
    private String secureFlg = "0";
    private String artType;

    private String oldIdFileName;
    private String oldPhotoFileName;
    private String oldReportFileName;

    private BigDecimal cashFee = new BigDecimal(0);
    private BigDecimal bankFee = new BigDecimal(0);
    private BigDecimal lakalaFee = new BigDecimal(0);
    private BigDecimal aliFee = new BigDecimal(0);
    private BigDecimal preferentialFee = new BigDecimal(0);
    private BigDecimal payAgainFee = new BigDecimal(0);
    private BigDecimal refundFee = new BigDecimal(0);

    private BigDecimal stu_stuPayment_signFee = new BigDecimal(0);
    private BigDecimal stu_stuPayment_studyFee = new BigDecimal(0);
    private BigDecimal stu_stuPayment_stayFee = new BigDecimal(0);
    private BigDecimal stu_stuPayment_selfFee = new BigDecimal(0);
    private BigDecimal stu_stuPayment_scoreFee = new BigDecimal(0);
    private BigDecimal stu_stuPayment_safetyFee = new BigDecimal(0);
    private BigDecimal stu_stuPayment_waterFee = new BigDecimal(0);
    private BigDecimal stu_stuPayment_preferentialHd = new BigDecimal(0);
    private BigDecimal stu_stuPayment_countFee = new BigDecimal(0);
    private String classTypeName;
    private String bankSignUpMoneyFlg = "0";
    private String lakalaSignUpMoneyFlg = "0";
    private String aliSignUpMoneyFlg = "0";
    private String signedContent;
    private String stayContent;
    private String selfstudyNightContent;
    private String selfstudyNoonContent;
    private String signUpMoneyContent;
    private String secureContent;
    public BigDecimal cashRefundFee = new BigDecimal(0);
    public BigDecimal bankRefundFee = new BigDecimal(0);
    public BigDecimal lakalaRefundFee = new BigDecimal(0);
    public BigDecimal aliRefundFee = new BigDecimal(0);
    public BigDecimal cashPayAgainFee = new BigDecimal(0);
    public BigDecimal bankPayAgainFee = new BigDecimal(0);
    public BigDecimal lakalaPayAgainFee = new BigDecimal(0);
    public BigDecimal aliPayAgainFee = new BigDecimal(0);
    public String fractionCountStart;
    public String fractionCountEnd;
    public String index;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWlqf() {
        return wlqf;
    }

    public void setWlqf(String wlqf) {
        this.wlqf = wlqf;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherTel() {
        return fatherTel;
    }

    public void setFatherTel(String fatherTel) {
        this.fatherTel = fatherTel;
    }

    public String getFatherWork() {
        return fatherWork;
    }

    public void setFatherWork(String fatherWork) {
        this.fatherWork = fatherWork;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherTel() {
        return motherTel;
    }

    public void setMotherTel(String motherTel) {
        this.motherTel = motherTel;
    }

    public String getMotherWork() {
        return motherWork;
    }

    public void setMotherWork(String motherWork) {
        this.motherWork = motherWork;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getFractionLanguage() {
        return fractionLanguage;
    }

    public void setFractionLanguage(String fractionLanguage) {
        this.fractionLanguage = fractionLanguage;
    }

    public String getFractionMath() {
        return fractionMath;
    }

    public void setFractionMath(String fractionMath) {
        this.fractionMath = fractionMath;
    }

    public String getFractionEnglish() {
        return fractionEnglish;
    }

    public void setFractionEnglish(String fractionEnglish) {
        this.fractionEnglish = fractionEnglish;
    }

    public String getFractionComp1() {
        return fractionComp1;
    }

    public void setFractionComp1(String fractionComp1) {
        this.fractionComp1 = fractionComp1;
    }

    public String getFractionComp2() {
        return fractionComp2;
    }

    public void setFractionComp2(String fractionComp2) {
        this.fractionComp2 = fractionComp2;
    }

    public String getFractionComp3() {
        return fractionComp3;
    }

    public void setFractionComp3(String fractionComp3) {
        this.fractionComp3 = fractionComp3;
    }

    public String getFractionCompCount() {
        return fractionCompCount;
    }

    public void setFractionCompCount(String fractionCompCount) {
        this.fractionCompCount = fractionCompCount;
    }

    public String getFractionCount() {
        return fractionCount;
    }

    public void setFractionCount(String fractionCount) {
        this.fractionCount = fractionCount;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStayFlg() {
        return stayFlg;
    }

    public void setStayFlg(String stayFlg) {
        this.stayFlg = stayFlg;
    }

    public String getStayNum() {
        return stayNum;
    }

    public void setStayNum(String stayNum) {
        this.stayNum = stayNum;
    }

    public String getStayTel() {
        return stayTel;
    }

    public void setStayTel(String stayTel) {
        this.stayTel = stayTel;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getSelfstudyNightflg() {
        return selfstudyNightflg;
    }

    public void setSelfstudyNightflg(String selfstudyNightflg) {
        this.selfstudyNightflg = selfstudyNightflg;
    }

    public String getSelfstudyNoonflg() {
        return selfstudyNoonflg;
    }

    public void setSelfstudyNoonflg(String selfstudyNoonflg) {
        this.selfstudyNoonflg = selfstudyNoonflg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Timestamp createdatetime) {
        this.createdatetime = createdatetime;
    }

    public Timestamp getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(Timestamp modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BigDecimal getStudyFee() {
        return studyFee;
    }

    public void setStudyFee(BigDecimal studyFee) {
        this.studyFee = studyFee;
    }

    public BigDecimal getStayFee() {
        return stayFee;
    }

    public void setStayFee(BigDecimal stayFee) {
        this.stayFee = stayFee;
    }

    public BigDecimal getSelfFee() {
        return selfFee;
    }

    public void setSelfFee(BigDecimal selfFee) {
        this.selfFee = selfFee;
    }

    public BigDecimal getSignFee() {
        return signFee;
    }

    public void setSignFee(BigDecimal signFee) {
        this.signFee = signFee;
    }

    public BigDecimal getScoreFee() {
        return scoreFee;
    }

    public void setScoreFee(BigDecimal scoreFee) {
        this.scoreFee = scoreFee;
    }

    public BigDecimal getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(BigDecimal waterFee) {
        this.waterFee = waterFee;
    }

    public BigDecimal getCountFee() {
        return countFee;
    }

    public void setCountFee(BigDecimal countFee) {
        this.countFee = countFee;
    }

    public String getArrearflg() {
        return arrearflg;
    }

    public void setArrearflg(String arrearflg) {
        this.arrearflg = arrearflg;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Timestamp getCreatedatetimeStart() {
        return createdatetimeStart;
    }

    public void setCreatedatetimeStart(Timestamp createdatetimeStart) {
        this.createdatetimeStart = createdatetimeStart;
    }

    public Timestamp getCreatedatetimeEnd() {
        return createdatetimeEnd;
    }

    public void setCreatedatetimeEnd(Timestamp createdatetimeEnd) {
        this.createdatetimeEnd = createdatetimeEnd;
    }

    public String getPreferential() {
        return preferential;
    }

    public void setPreferential(String preferential) {
        this.preferential = preferential;
    }

    public BigDecimal getSafetyFee() {
        return safetyFee;
    }

    public void setSafetyFee(BigDecimal safetyFee) {
        this.safetyFee = safetyFee;
    }

    public BigDecimal getArrearFee() {
        return arrearFee;
    }

    public void setArrearFee(BigDecimal arrearFee) {
        this.arrearFee = arrearFee;
    }

//    public File getFiledata() {
//        return filedata;
//    }
//
//    public void setFiledata(File filedata) {
//        this.filedata = filedata;
//    }
//
//    public String getFiledataFileName() {
//        return filedataFileName;
//    }
//
//    public void setFiledataFileName(String filedataFileName) {
//        this.filedataFileName = filedataFileName;
//    }
//
//    public String getFiledataContentType() {
//        return filedataContentType;
//    }
//
//    public void setFiledataContentType(String filedataContentType) {
//        this.filedataContentType = filedataContentType;
//    }
//
//    public String getFilename() {
//        return filename;
//    }
//
//    public void setFilename(String filename) {
//        this.filename = filename;
//    }
//
//    public String getUpload() {
//        return upload;
//    }
//
//    public void setUpload(String upload) {
//        this.upload = upload;
//    }

    public File getVideo() {
        return video;
    }

    public void setVideo(File video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getSignedFlg() {
        return signedFlg;
    }

    public void setSignedFlg(String signedFlg) {
        this.signedFlg = signedFlg;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
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

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public File getReportFile() {
        return reportFile;
    }

    public void setReportFile(File reportFile) {
        this.reportFile = reportFile;
    }

    public String getReportFileContentType() {
        return reportFileContentType;
    }

    public void setReportFileContentType(String reportFileContentType) {
        this.reportFileContentType = reportFileContentType;
    }

    public File getIdNUmFile() {
        return idNUmFile;
    }

    public void setIdNUmFile(File idNUmFile) {
        this.idNUmFile = idNUmFile;
    }

    public String getIdNUmFileContentType() {
        return idNUmFileContentType;
    }

    public void setIdNUmFileContentType(String idNUmFileContentType) {
        this.idNUmFileContentType = idNUmFileContentType;
    }

    public File getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(File photoFile) {
        this.photoFile = photoFile;
    }

    public String getPhotoFileContentType() {
        return photoFileContentType;
    }

    public void setPhotoFileContentType(String photoFileContentType) {
        this.photoFileContentType = photoFileContentType;
    }

    public String getDormitoryNum() {
        return dormitoryNum;
    }

    public void setDormitoryNum(String dormitoryNum) {
        this.dormitoryNum = dormitoryNum;
    }

    public String getSignUpMoneyFlg() {
        return signUpMoneyFlg;
    }

    public void setSignUpMoneyFlg(String signUpMoneyFlg) {
        this.signUpMoneyFlg = signUpMoneyFlg;
    }

    public String getSecureFlg() {
        return secureFlg;
    }

    public void setSecureFlg(String secureFlg) {
        this.secureFlg = secureFlg;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public String getReportFileFileName() {
        return reportFileFileName;
    }

    public void setReportFileFileName(String reportFileFileName) {
        this.reportFileFileName = reportFileFileName;
    }

    public String getIdNUmFileFileName() {
        return idNUmFileFileName;
    }

    public void setIdNUmFileFileName(String idNUmFileFileName) {
        this.idNUmFileFileName = idNUmFileFileName;
    }

    public String getPhotoFileFileName() {
        return photoFileFileName;
    }

    public void setPhotoFileFileName(String photoFileFileName) {
        this.photoFileFileName = photoFileFileName;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getOldIdFileName() {
        return oldIdFileName;
    }

    public void setOldIdFileName(String oldIdFileName) {
        this.oldIdFileName = oldIdFileName;
    }

    public String getOldPhotoFileName() {
        return oldPhotoFileName;
    }

    public void setOldPhotoFileName(String oldPhotoFileName) {
        this.oldPhotoFileName = oldPhotoFileName;
    }

    public String getOldReportFileName() {
        return oldReportFileName;
    }

    public void setOldReportFileName(String oldReportFileName) {
        this.oldReportFileName = oldReportFileName;
    }

    public String getPhotoImgSrc() {
        return photoImgSrc;
    }

    public void setPhotoImgSrc(String photoImgSrc) {
        this.photoImgSrc = photoImgSrc;
    }

    public BigDecimal getCashFee() {
        return cashFee;
    }

    public void setCashFee(BigDecimal cashFee) {
        this.cashFee = cashFee;
    }

    public BigDecimal getBankFee() {
        return bankFee;
    }

    public void setBankFee(BigDecimal bankFee) {
        this.bankFee = bankFee;
    }

    public BigDecimal getLakalaFee() {
        return lakalaFee;
    }

    public void setLakalaFee(BigDecimal lakalaFee) {
        this.lakalaFee = lakalaFee;
    }

    public BigDecimal getAliFee() {
        return aliFee;
    }

    public void setAliFee(BigDecimal aliFee) {
        this.aliFee = aliFee;
    }

    public BigDecimal getPreferentialFee() {
        return preferentialFee;
    }

    public void setPreferentialFee(BigDecimal preferentialFee) {
        this.preferentialFee = preferentialFee;
    }

    public BigDecimal getPayAgainFee() {
        return payAgainFee;
    }

    public void setPayAgainFee(BigDecimal payAgainFee) {
        this.payAgainFee = payAgainFee;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public BigDecimal getStu_stuPayment_signFee() {
        return stu_stuPayment_signFee;
    }

    public void setStu_stuPayment_signFee(BigDecimal stu_stuPayment_signFee) {
        this.stu_stuPayment_signFee = stu_stuPayment_signFee;
    }

    public BigDecimal getStu_stuPayment_studyFee() {
        return stu_stuPayment_studyFee;
    }

    public void setStu_stuPayment_studyFee(BigDecimal stu_stuPayment_studyFee) {
        this.stu_stuPayment_studyFee = stu_stuPayment_studyFee;
    }

    public BigDecimal getStu_stuPayment_stayFee() {
        return stu_stuPayment_stayFee;
    }

    public void setStu_stuPayment_stayFee(BigDecimal stu_stuPayment_stayFee) {
        this.stu_stuPayment_stayFee = stu_stuPayment_stayFee;
    }

    public BigDecimal getStu_stuPayment_selfFee() {
        return stu_stuPayment_selfFee;
    }

    public void setStu_stuPayment_selfFee(BigDecimal stu_stuPayment_selfFee) {
        this.stu_stuPayment_selfFee = stu_stuPayment_selfFee;
    }

    public BigDecimal getStu_stuPayment_scoreFee() {
        return stu_stuPayment_scoreFee;
    }

    public void setStu_stuPayment_scoreFee(BigDecimal stu_stuPayment_scoreFee) {
        this.stu_stuPayment_scoreFee = stu_stuPayment_scoreFee;
    }

    public BigDecimal getStu_stuPayment_safetyFee() {
        return stu_stuPayment_safetyFee;
    }

    public void setStu_stuPayment_safetyFee(BigDecimal stu_stuPayment_safetyFee) {
        this.stu_stuPayment_safetyFee = stu_stuPayment_safetyFee;
    }

    public BigDecimal getStu_stuPayment_waterFee() {
        return stu_stuPayment_waterFee;
    }

    public void setStu_stuPayment_waterFee(BigDecimal stu_stuPayment_waterFee) {
        this.stu_stuPayment_waterFee = stu_stuPayment_waterFee;
    }

    public BigDecimal getStu_stuPayment_preferentialHd() {
        return stu_stuPayment_preferentialHd;
    }

    public void setStu_stuPayment_preferentialHd(
            BigDecimal stu_stuPayment_preferentialHd) {
        this.stu_stuPayment_preferentialHd = stu_stuPayment_preferentialHd;
    }

    public BigDecimal getStu_stuPayment_countFee() {
        return stu_stuPayment_countFee;
    }

    public void setStu_stuPayment_countFee(BigDecimal stu_stuPayment_countFee) {
        this.stu_stuPayment_countFee = stu_stuPayment_countFee;
    }

    public String getClassTypeName() {
        return classTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }

    public String getBankSignUpMoneyFlg() {
        return bankSignUpMoneyFlg;
    }

    public void setBankSignUpMoneyFlg(String bankSignUpMoneyFlg) {
        this.bankSignUpMoneyFlg = bankSignUpMoneyFlg;
    }

    public String getLakalaSignUpMoneyFlg() {
        return lakalaSignUpMoneyFlg;
    }

    public void setLakalaSignUpMoneyFlg(String lakalaSignUpMoneyFlg) {
        this.lakalaSignUpMoneyFlg = lakalaSignUpMoneyFlg;
    }

    public String getAliSignUpMoneyFlg() {
        return aliSignUpMoneyFlg;
    }

    public void setAliSignUpMoneyFlg(String aliSignUpMoneyFlg) {
        this.aliSignUpMoneyFlg = aliSignUpMoneyFlg;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getStuTypeContent() {
        return stuTypeContent;
    }

    public void setStuTypeContent(String stuTypeContent) {
        this.stuTypeContent = stuTypeContent;
    }

    public String getSignedContent() {
        return signedContent;
    }

    public void setSignedContent(String signedContent) {
        this.signedContent = signedContent;
    }

    public String getStayContent() {
        return stayContent;
    }

    public void setStayContent(String stayContent) {
        this.stayContent = stayContent;
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
}
