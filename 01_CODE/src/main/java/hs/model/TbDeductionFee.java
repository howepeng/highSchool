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
 * TbPreferential entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_deduction_fee", catalog = "highschool2015")
public class TbDeductionFee implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private BigDecimal deductionFee;
    private String timeType;
    private Date startDate;
    private Date endDate;
    private TbYearInfo tbYearInfo;
    private String status;
    // Constructors

    /** default constructor */
    public TbDeductionFee() {
    }

    /** minimal constructor */
    public TbDeductionFee(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbDeductionFee(String id, String name, BigDecimal deductionFee) {
        this.id = id;
        this.name = name;
        this.deductionFee = deductionFee;
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

    @Column(name = "name", length = 40, nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "deduction_fee", precision = 22, scale = 0)
    public BigDecimal getDeductionFee() {
        return this.deductionFee;
    }

    public void setDeductionFee(BigDecimal deductionFee) {
        this.deductionFee = deductionFee;
    }

    @Column(name = "start_date", nullable = false, length = 19)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date", nullable = false, length = 19)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id", nullable = false)
    public TbYearInfo getTbYearInfo() {
        return tbYearInfo;
    }

    public void setTbYearInfo(TbYearInfo tbYearInfo) {
        this.tbYearInfo = tbYearInfo;
    }

    @Column(name = "time_type", nullable = false, length = 36)
    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    @Column(name = "status", nullable = false, length = 36)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
