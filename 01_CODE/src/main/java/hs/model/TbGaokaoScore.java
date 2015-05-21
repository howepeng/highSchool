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
 * TbClassType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_gaokao_score", catalog = "highschool2015")
public class TbGaokaoScore implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private TbStudent tbStudent;
    private String admissionSchoolName;
    private String gaokaoNum;
    private BigDecimal fractionLanguage;
    private BigDecimal fractionMath;
    private BigDecimal fractionEnglish;
    private BigDecimal fractionComp1;
    private BigDecimal fractionComp2;
    private BigDecimal fractionComp3;
    private BigDecimal fractionComp_count;
    private BigDecimal fractionCount;
    private Date createdatetime;
    private TbYearInfo tbYearInfo;
    // Constructors

    /** default constructor */
    public TbGaokaoScore() {
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

    @Column(name = "createdatetime", length = 19)
    public Date getCreatedatetime() {
        return this.createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    public TbStudent getTbStudent() {
        return this.tbStudent;
    }

    public void setTbStudent(TbStudent tbStudent) {
        this.tbStudent = tbStudent;
    }

    @Column(name = "fraction_language", nullable = true, length = 5)
    public BigDecimal getFractionLanguage() {
        return fractionLanguage;
    }

    public void setFractionLanguage(BigDecimal fractionLanguage) {
        this.fractionLanguage = fractionLanguage;
    }

    @Column(name = "fraction_math", nullable = true, length = 5)
    public BigDecimal getFractionMath() {
        return fractionMath;
    }

    public void setFractionMath(BigDecimal fractionMath) {
        this.fractionMath = fractionMath;
    }

    @Column(name = "fraction_english", nullable = true, length = 5)
    public BigDecimal getFractionEnglish() {
        return fractionEnglish;
    }

    public void setFractionEnglish(BigDecimal fractionEnglish) {
        this.fractionEnglish = fractionEnglish;
    }

    @Column(name = "fraction_comp1", nullable = true, length = 5)
    public BigDecimal getFractionComp1() {
        return fractionComp1;
    }

    public void setFractionComp1(BigDecimal fractionComp1) {
        this.fractionComp1 = fractionComp1;
    }

    @Column(name = "fraction_comp2", nullable = true, length = 5)
    public BigDecimal getFractionComp2() {
        return fractionComp2;
    }

    public void setFractionComp2(BigDecimal fractionComp2) {
        this.fractionComp2 = fractionComp2;
    }

    @Column(name = "fraction_comp3", nullable = true, length = 5)
    public BigDecimal getFractionComp3() {
        return fractionComp3;
    }

    public void setFractionComp3(BigDecimal fractionComp3) {
        this.fractionComp3 = fractionComp3;
    }

    @Column(name = "fraction_comp_count", nullable = true, length = 5)
    public BigDecimal getFractionComp_count() {
        return fractionComp_count;
    }

    public void setFractionComp_count(BigDecimal fractionComp_count) {
        this.fractionComp_count = fractionComp_count;
    }

    @Column(name = "fraction_count", nullable = true, length = 5)
    public BigDecimal getFractionCount() {
        return fractionCount;
    }

    public void setFractionCount(BigDecimal fractionCount) {
        this.fractionCount = fractionCount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id")
    public TbYearInfo getTbYearInfo() {
        return tbYearInfo;
    }

    public void setTbYearInfo(TbYearInfo tbYearInfo) {
        this.tbYearInfo = tbYearInfo;
    }

    @Column(name = "admission_school_name", nullable = true, length = 150)
    public String getAdmissionSchoolName() {
        return admissionSchoolName;
    }

    public void setAdmissionSchoolName(String admissionSchoolName) {
        this.admissionSchoolName = admissionSchoolName;
    }

    @Column(name = "gaokao_num", nullable = true, length = 50)
    public String getGaokaoNum() {
        return gaokaoNum;
    }

    public void setGaokaoNum(String gaokaoNum) {
        this.gaokaoNum = gaokaoNum;
    }

}
