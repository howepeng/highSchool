package hs.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbClassType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_class_type", catalog = "highschool2015")
public class TbClassType implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String classType;
    private String professionalId;
    private BigDecimal studyFee;
    private BigDecimal stayFee;
    private BigDecimal selfFee;
    private BigDecimal signFee;
    private BigDecimal scoreFee;
    private BigDecimal safetyFee;
    private BigDecimal waterFee;
    private BigDecimal countFee;

    // Constructors

    /** default constructor */
    public TbClassType() {
    }

    /** minimal constructor */
    public TbClassType(String id) {
        this.id = id;
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

    @Column(name = "class_type", length = 40)
    public String getClassType() {
        return this.classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
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

    @Column(name = "professional_id", length = 6)
    public String getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }

}
