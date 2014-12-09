package hs.model;

import java.sql.Date;
import java.sql.Time;

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
@Table(name = "tb_log_info", catalog = "highschool")
public class TbLogInfo implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private Time startTime;
    private Time endTime;
    private Date date;
    private String classId;
    private String remark;
    private TbLogType tbLogType;
    private TbClassTime tbClassTime;
    private TbStudent tbStudent;
    private TbLogResult tbLogResult;
    // Constructors
    @Column(name = "classId", length = 36, nullable = false)
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Column(name = "remark", length = 100)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** default constructor */
    public TbLogInfo() {
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

    @Column(name = "startTime", nullable = false)
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Column(name = "endTime", nullable = false)
    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeId")
    public TbLogType getTbLogType() {
        return tbLogType;
    }

    public void setTbLogType(TbLogType tbLogType) {
        this.tbLogType = tbLogType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classTimeId")
    public TbClassTime getTbClassTime() {
        return tbClassTime;
    }

    public void setTbClassTime(TbClassTime tbClassTime) {
        this.tbClassTime = tbClassTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    public TbStudent getTbStudent() {
        return tbStudent;
    }

    public void setTbStudent(TbStudent tbStudent) {
        this.tbStudent = tbStudent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resultId")
    public TbLogResult getTbLogResult() {
        return tbLogResult;
    }

    public void setTbLogResult(TbLogResult tbLogResult) {
        this.tbLogResult = tbLogResult;
    }

    public Date getDate() {
        return date;
    }

    @Column(name = "date", nullable = false)
    public void setDate(Date date) {
        this.date = date;
    }
}
