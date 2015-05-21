package hs.pageModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class ScoreCount implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String monthTime1;
    private String monthTime2;
    private String professionalId;
    private String professionalName;
    private String classInfoId;
    private String className;
    private String studentId;
    private String studentName;
    private String subjectId;
    private String subjectName;
    private BigDecimal fraction;
    private BigDecimal fractionLanguage;
    private BigDecimal fractionMath;
    private BigDecimal fractionEnglish;
    private BigDecimal fractionComp1;
    private BigDecimal fractionComp2;
    private BigDecimal fractionComp3;
    private BigDecimal fractionComp_count;
    private BigDecimal fractionCountStart;
    private BigDecimal fractionCountEnd;
    private BigDecimal fractionCount;
    private int classRank;
    private int gradeRank;
    private int classChange;
    private int gradeChange;
    private String classId;
    private String[] studentIds;
    private String modeId;
    private int page;
    private int rows;
    private String sort;
    private String order;
    private String ids;// 删除用 id字符串（用逗号分隔）
    public String getMonthTime1() {
        return monthTime1;
    }
    public void setMonthTime1(String monthTime1) {
        this.monthTime1 = monthTime1;
    }
    public String getMonthTime2() {
        return monthTime2;
    }
    public void setMonthTime2(String monthTime2) {
        this.monthTime2 = monthTime2;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public BigDecimal getFractionLanguage() {
        return fractionLanguage;
    }
    public void setFractionLanguage(BigDecimal fractionLanguage) {
        this.fractionLanguage = fractionLanguage;
    }
    public BigDecimal getFractionMath() {
        return fractionMath;
    }
    public void setFractionMath(BigDecimal fractionMath) {
        this.fractionMath = fractionMath;
    }
    public BigDecimal getFractionEnglish() {
        return fractionEnglish;
    }
    public void setFractionEnglish(BigDecimal fractionEnglish) {
        this.fractionEnglish = fractionEnglish;
    }
    public BigDecimal getFractionComp1() {
        return fractionComp1;
    }
    public void setFractionComp1(BigDecimal fractionComp1) {
        this.fractionComp1 = fractionComp1;
    }
    public BigDecimal getFractionComp2() {
        return fractionComp2;
    }
    public void setFractionComp2(BigDecimal fractionComp2) {
        this.fractionComp2 = fractionComp2;
    }
    public BigDecimal getFractionComp3() {
        return fractionComp3;
    }
    public void setFractionComp3(BigDecimal fractionComp3) {
        this.fractionComp3 = fractionComp3;
    }
    public BigDecimal getFractionComp_count() {
        return fractionComp_count;
    }
    public void setFractionComp_count(BigDecimal fractionComp_count) {
        this.fractionComp_count = fractionComp_count;
    }
    public BigDecimal getFractionCountStart() {
        return fractionCountStart;
    }
    public void setFractionCountStart(BigDecimal fractionCountStart) {
        this.fractionCountStart = fractionCountStart;
    }
    public BigDecimal getFractionCountEnd() {
        return fractionCountEnd;
    }
    public void setFractionCountEnd(BigDecimal fractionCountEnd) {
        this.fractionCountEnd = fractionCountEnd;
    }
    public BigDecimal getFractionCount() {
        return fractionCount;
    }
    public void setFractionCount(BigDecimal fractionCount) {
        this.fractionCount = fractionCount;
    }
    public int getClassRank() {
        return classRank;
    }
    public void setClassRank(int classRank) {
        this.classRank = classRank;
    }
    public int getGradeRank() {
        return gradeRank;
    }
    public void setGradeRank(int gradeRank) {
        this.gradeRank = gradeRank;
    }
    public int getClassChange() {
        return classChange;
    }
    public void setClassChange(int classChange) {
        this.classChange = classChange;
    }
    public int getGradeChange() {
        return gradeChange;
    }
    public void setGradeChange(int gradeChange) {
        this.gradeChange = gradeChange;
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
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    /**
     * @return the subjectId
     */
    public String getSubjectId() {
        return subjectId;
    }
    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
    /**
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }
    /**
     * @param subjectName the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    /**
     * @return the fraction
     */
    public BigDecimal getFraction() {
        return fraction;
    }
    /**
     * @param fraction the fraction to set
     */
    public void setFraction(BigDecimal fraction) {
        this.fraction = fraction;
    }
    /**
     * @return the classId
     */
    public String getClassId() {
        return classId;
    }
    /**
     * @param classId the classId to set
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }
    /**
     * @return the studentIds
     */
    public String[] getStudentIds() {
        return studentIds;
    }
    /**
     * @param studentIds the studentIds to set
     */
    public void setStudentIds(String[] studentIds) {
        this.studentIds = studentIds;
    }
    /**
     * @return the modeId
     */
    public String getModeId() {
        return modeId;
    }
    /**
     * @param modeId the modeId to set
     */
    public void setModeId(String modeId) {
        this.modeId = modeId;
    }
    public String getClassInfoId() {
        return classInfoId;
    }
    public void setClassInfoId(String classInfoId) {
        this.classInfoId = classInfoId;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getProfessionalId() {
        return professionalId;
    }
    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }
    public String getProfessionalName() {
        return professionalName;
    }
    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }



}
