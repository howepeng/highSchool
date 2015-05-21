package hs.pageModel;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class MonthScore implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String studentId;
    private String studentName;
    private String monthTimeId;
    private String monthTimeName;
    private String professionalId;
    private String professionalName;
    private String className;
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
    private Date createdatetime;
    private int page;
    private int rows;
    private String sort;
    private String order;
    private String ids;// 删除用 id字符串（用逗号分隔）
    private File monthScoreFile;// 上传的文件
    private String monthScoreFileContentType; // 封装上传文件类型的属性
    private String monthScoreFileFileName; // 封装上传文件名的属性
    private String subjectId;
    private String averageId;
    private String objectId;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMonthTimeId() {
        return monthTimeId;
    }

    public void setMonthTimeId(String monthTimeId) {
        this.monthTimeId = monthTimeId;
    }

    public String getMonthTimeName() {
        return monthTimeName;
    }

    public void setMonthTimeName(String monthTimeName) {
        this.monthTimeName = monthTimeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public File getMonthScoreFile() {
        return monthScoreFile;
    }

    public void setMonthScoreFile(File monthScoreFile) {
        this.monthScoreFile = monthScoreFile;
    }

    public String getMonthScoreFileFileName() {
        return monthScoreFileFileName;
    }

    public void setMonthScoreFileFileName(String monthScoreFileFileName) {
        this.monthScoreFileFileName = monthScoreFileFileName;
    }

    public String getMonthScoreFileContentType() {
        return monthScoreFileContentType;
    }

    public void setMonthScoreFileContentType(String monthScoreFileContentType) {
        this.monthScoreFileContentType = monthScoreFileContentType;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getAverageId() {
        return averageId;
    }

    public void setAverageId(String averageId) {
        this.averageId = averageId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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
