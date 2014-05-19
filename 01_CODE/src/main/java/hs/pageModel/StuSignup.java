package hs.pageModel;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TbStuSingup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_stu_singup", catalog = "highschool")
public class StuSignup implements java.io.Serializable {

	private String id;
	private String name;
	private String sex;
	private String wlqf;
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
	private String selfstudyNightflg = "0";
	private String selfstudyNoonflg = "0";
	private String remark;
	private Date createdatetime;
	private Date modifydatetime;

	public StuSignup() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public Date getModifydatetime() {
		return modifydatetime;
	}

	public void setModifydatetime(Date modifydatetime) {
		this.modifydatetime = modifydatetime;
	}

}