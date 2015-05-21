package hs.model;

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
@Table(name = "tb_score_count", catalog = "highschool2015")
public class TbScoreCount implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private TbMonthInfo tbMonthInfo1;
    private TbMonthInfo tbMonthInfo2;
    private TbStudent tbStudent;
    private TbYearInfo tbYearInfo;
    private int classRank;
    private int gradeRank;
    private int classChange;
    private int gradeChange;
    private String subjectId;
    // Constructors

    /** default constructor */
    public TbScoreCount() {
    }

    // Property accessors
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month_time1", nullable = false)
    public TbMonthInfo getTbMonthInfo1() {
        return tbMonthInfo1;
    }

    public void setTbMonthInfo1(TbMonthInfo tbMonthInfo1) {
        this.tbMonthInfo1 = tbMonthInfo1;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month_time2", nullable = false)
    public TbMonthInfo getTbMonthInfo2() {
        return tbMonthInfo2;
    }

    public void setTbMonthInfo2(TbMonthInfo tbMonthInfo2) {
        this.tbMonthInfo2 = tbMonthInfo2;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    public TbStudent getTbStudent() {
        return this.tbStudent;
    }

    public void setTbStudent(TbStudent tbStudent) {
        this.tbStudent = tbStudent;
    }

    @Column(name = "class_rank", nullable = true, length = 5)
    public int getClassRank() {
        return classRank;
    }

    public void setClassRank(int classRank) {
        this.classRank = classRank;
    }

    @Column(name = "grade_rank", nullable = true, length = 5)
    public int getGradeRank() {
        return gradeRank;
    }

    public void setGradeRank(int gradeRank) {
        this.gradeRank = gradeRank;
    }

    @Column(name = "class_change", nullable = true, length = 5)
    public int getClassChange() {
        return classChange;
    }

    public void setClassChange(int classChange) {
        this.classChange = classChange;
    }

    @Column(name = "grade_change", nullable = true, length = 5)
    public int getGradeChange() {
        return gradeChange;
    }

    public void setGradeChange(int gradeChange) {
        this.gradeChange = gradeChange;
    }

    @Id
    @Column(name = "subject_id", length = 6)
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id")
    public TbYearInfo getTbYearInfo() {
        return tbYearInfo;
    }

    public void setTbYearInfo(TbYearInfo tbYearInfo) {
        this.tbYearInfo = tbYearInfo;
    }
}
