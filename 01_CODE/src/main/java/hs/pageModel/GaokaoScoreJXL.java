package hs.pageModel;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class GaokaoScoreJXL implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String gaokaoNum;
    private String fractionLanguage;
    private String fractionMath;
    private String fractionEnglish;
    private String fractionComp1;
    private String fractionComp2;
    private String fractionComp3;
    private String fractionComp_count;
    private String fractionCount;
    private String admissionSchoolName;
    private String studentId;
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
    public String getFractionComp_count() {
        return fractionComp_count;
    }
    public void setFractionComp_count(String fractionComp_count) {
        this.fractionComp_count = fractionComp_count;
    }
    public String getFractionCount() {
        return fractionCount;
    }
    public void setFractionCount(String fractionCount) {
        this.fractionCount = fractionCount;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getGaokaoNum() {
        return gaokaoNum;
    }
    public void setGaokaoNum(String gaokaoNum) {
        this.gaokaoNum = gaokaoNum;
    }
    public String getAdmissionSchoolName() {
        return admissionSchoolName;
    }
    public void setAdmissionSchoolName(String admissionSchoolName) {
        this.admissionSchoolName = admissionSchoolName;
    }
}
