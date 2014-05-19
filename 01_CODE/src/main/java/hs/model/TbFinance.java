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
 * TbFinance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_finance", catalog = "highschool")
public class TbFinance implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private TbUser tbUser;
    private TbClassType tbClassType;
    private String name;
    private String idNum;
    private Date createdatetime;
    private String studentId;

    private BigDecimal studyFee = new BigDecimal(0);
    private BigDecimal stayFee = new BigDecimal(0);
    private BigDecimal selfFee = new BigDecimal(0);
    private BigDecimal signFee = new BigDecimal(0);
    private BigDecimal scoreFee = new BigDecimal(0);
    private BigDecimal safetyFee = new BigDecimal(0);
    private BigDecimal waterFee = new BigDecimal(0);
    private BigDecimal cashFee = new BigDecimal(0);
    private BigDecimal transferFee = new BigDecimal(0);
    private BigDecimal payAgainFee = new BigDecimal(0);
    private BigDecimal countPayFee = new BigDecimal(0);
    private BigDecimal preferentialFee = new BigDecimal(0);
    private BigDecimal arrearFee = new BigDecimal(0);
    private BigDecimal refundFee = new BigDecimal(0);
    private BigDecimal cashRefundFee = new BigDecimal(0);
    private BigDecimal transferRefundFee = new BigDecimal(0);
    private BigDecimal cashPayAgainFee = new BigDecimal(0);
    private BigDecimal transferPayAgainFee = new BigDecimal(0);
    // Constructors

    /** default constructor */
    public TbFinance() {
    }

    /** minimal constructor */
    public TbFinance(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbFinance(String id, TbUser tbUser,TbClassType tbClassType, String name,String idNum, BigDecimal signFee, String paymentFee,BigDecimal arrearFee,BigDecimal refundFee, Date createdatetime, Date paydatetime, String payflg, BigDecimal studyFee,BigDecimal stayFee,
    BigDecimal selfFee,
    BigDecimal scoreFee,
    BigDecimal safetyFee,
    BigDecimal waterFee,
    BigDecimal cashFee,
    BigDecimal transferFee,
    BigDecimal payAgainFee,
    BigDecimal countPayFee,
    String studentId,
    BigDecimal preferentialFee) {
        this.id = id;
        this.tbUser = tbUser;
        this.tbClassType = tbClassType;
        this.name =name;
        this.idNum = idNum;
        this.signFee = signFee;
        this.arrearFee = arrearFee;
        this.refundFee = refundFee;
        this.createdatetime = createdatetime;
        this.studyFee = studyFee;
        this.stayFee = stayFee;
        this.selfFee = selfFee;
        this.scoreFee = scoreFee;
        this.safetyFee = safetyFee;
        this.waterFee = waterFee;
        this.cashFee = cashFee;
        this.transferFee = transferFee;
        this.payAgainFee = payAgainFee;
        this.countPayFee = countPayFee;
        this.studentId = studentId;
        this.preferentialFee = preferentialFee;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payee")
    public TbUser getTbUser() {
        return this.tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_type")
    public TbClassType getTbClassType() {
        return this.tbClassType;
    }

    public void setTbClassType(TbClassType tbClassType) {
        this.tbClassType = tbClassType;
    }

    @Column(name = "name", length = 10)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "id_num", length = 30)
    public String getIdNum() {
        return this.idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Column(name = "sign_fee", precision = 22, scale = 0)
    public BigDecimal getSignFee() {
        return this.signFee;
    }

    public void setSignFee(BigDecimal signFee) {
        this.signFee = signFee;
    }

    @Column(name = "refundFee", precision = 22, scale = 0)
    public BigDecimal getRefundFee() {
        return this.refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    @Column(name = "createdatetime", length = 19)
    public Date getCreatedatetime() {
        return this.createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
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

    @Column(name = "arrearFee", precision = 22, scale = 0)
    public BigDecimal getArrearFee() {
        return arrearFee;
    }

    public void setArrearFee(BigDecimal arrearFee) {
        this.arrearFee = arrearFee;
    }

    @Column(name = "count_pay_fee", precision = 22, scale = 0)
    public BigDecimal getCountPayFee() {
        return countPayFee;
    }

    public void setCountPayFee(BigDecimal countPayFee) {
        this.countPayFee = countPayFee;
    }

    @Column(name = "cashFee", precision = 22, scale = 0)
    public BigDecimal getCashFee() {
        return cashFee;
    }

    public void setCashFee(BigDecimal cashFee) {
        this.cashFee = cashFee;
    }

    @Column(name = "payAgainFee", precision = 22, scale = 0)
    public BigDecimal getPayAgainFee() {
        return payAgainFee;
    }

    public void setPayAgainFee(BigDecimal payAgainFee) {
        this.payAgainFee = payAgainFee;
    }

    @Column(name = "transferFee", precision = 22, scale = 0)
    public BigDecimal getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }

    @Column(name = "studentId", length = 36)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Column(name = "preferentialFee", precision = 22, scale = 0)
    public BigDecimal getPreferentialFee() {
        return preferentialFee;
    }

    public void setPreferentialFee(BigDecimal preferentialFee) {
        this.preferentialFee = preferentialFee;
    }

    @Column(name = "cashRefundFee", precision = 22, scale = 0)
    public BigDecimal getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(BigDecimal cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
    }

    @Column(name = "transferRefundFee", precision = 22, scale = 0)
    public BigDecimal getTransferRefundFee() {
        return transferRefundFee;
    }

    public void setTransferRefundFee(BigDecimal transferRefundFee) {
        this.transferRefundFee = transferRefundFee;
    }

    @Column(name = "cashPayAgainFee", precision = 22, scale = 0)
    public BigDecimal getCashPayAgainFee() {
        return cashPayAgainFee;
    }

    public void setCashPayAgainFee(BigDecimal cashPayAgainFee) {
        this.cashPayAgainFee = cashPayAgainFee;
    }

    @Column(name = "transferPayAgainFee", precision = 22, scale = 0)
    public BigDecimal getTransferPayAgainFee() {
        return transferPayAgainFee;
    }

    public void setTransferPayAgainFee(BigDecimal transferPayAgainFee) {
        this.transferPayAgainFee = transferPayAgainFee;
    }
}
