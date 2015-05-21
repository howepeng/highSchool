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
@Table(name = "tb_finance", catalog = "highschool2015")
public class TbFinance implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private TbUser tbUser;
    private TbClassType tbClassType;
    private TbStudent tbStudent;
    private String name;
    private String idNum;
    private Date createdatetime;

    private BigDecimal studyFee = new BigDecimal(0);
    private BigDecimal stayFee = new BigDecimal(0);
    private BigDecimal selfFee = new BigDecimal(0);
    private BigDecimal signFee = new BigDecimal(0);
    private BigDecimal scoreFee = new BigDecimal(0);
    private BigDecimal safetyFee = new BigDecimal(0);
    private BigDecimal waterFee = new BigDecimal(0);
    private BigDecimal cashFee = new BigDecimal(0);
    private BigDecimal bankFee = new BigDecimal(0);
    private BigDecimal lakalaFee = new BigDecimal(0);
    private BigDecimal aliFee = new BigDecimal(0);
    private BigDecimal payAgainFee = new BigDecimal(0);
    private BigDecimal countPayFee = new BigDecimal(0);
    private BigDecimal preferentialFee = new BigDecimal(0);
    private BigDecimal deductionFee = new BigDecimal(0);
    private BigDecimal arrearFee = new BigDecimal(0);
    private BigDecimal refundFee = new BigDecimal(0);
    private BigDecimal cashRefundFee = new BigDecimal(0);
    private BigDecimal bankRefundFee = new BigDecimal(0);
    private BigDecimal lakalaRefundFee = new BigDecimal(0);
    private BigDecimal aliRefundFee = new BigDecimal(0);
    private BigDecimal cashPayAgainFee = new BigDecimal(0);
    private BigDecimal bankPayAgainFee = new BigDecimal(0);
    private BigDecimal lakalaPayAgainFee = new BigDecimal(0);
    private BigDecimal aliPayAgainFee = new BigDecimal(0);
    private String cancelflg;
    private String crashHistoryType;
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
    BigDecimal bankFee,
    BigDecimal lakalaFee,
    BigDecimal aliFee,
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
        this.bankFee = bankFee;
        this.lakalaFee = lakalaFee;
        this.aliFee = aliFee;
        this.payAgainFee = payAgainFee;
        this.countPayFee = countPayFee;
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

    @Column(name = "bankFee", precision = 22, scale = 0)
    public BigDecimal getBankFee() {
        return bankFee;
    }

    public void setBankFee(BigDecimal bankFee) {
        this.bankFee = bankFee;
    }
    public BigDecimal getLakalaFee() {
        return lakalaFee;
    }

    @Column(name = "lakalaFee", precision = 22, scale = 0)
    public void setLakalaFee(BigDecimal lakalaFee) {
        this.lakalaFee = lakalaFee;
    }

    public BigDecimal getAliFee() {
        return aliFee;
    }

    @Column(name = "aliFee", precision = 22, scale = 0)
    public void setAliFee(BigDecimal aliFee) {
        this.aliFee = aliFee;
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

    @Column(name = "bankRefundFee", precision = 22, scale = 0)
    public BigDecimal getBankRefundFee() {
        return bankRefundFee;
    }

    public void setBankRefundFee(BigDecimal bankRefundFee) {
        this.bankRefundFee = bankRefundFee;
    }

    @Column(name = "lakalaRefundFee", precision = 22, scale = 0)
    public BigDecimal getLakalaRefundFee() {
        return lakalaRefundFee;
    }

    public void setLakalaRefundFee(BigDecimal lakalaRefundFee) {
        this.lakalaRefundFee = lakalaRefundFee;
    }

    @Column(name = "aliRefundFee", precision = 22, scale = 0)
    public BigDecimal getAliRefundFee() {
        return aliRefundFee;
    }

    public void setAliRefundFee(BigDecimal aliRefundFee) {
        this.aliRefundFee = aliRefundFee;
    }

    @Column(name = "cashPayAgainFee", precision = 22, scale = 0)
    public BigDecimal getCashPayAgainFee() {
        return cashPayAgainFee;
    }

    public void setCashPayAgainFee(BigDecimal cashPayAgainFee) {
        this.cashPayAgainFee = cashPayAgainFee;
    }

    @Column(name = "bankPayAgainFee", precision = 22, scale = 0)
    public BigDecimal getBankPayAgainFee() {
        return bankPayAgainFee;
    }

    public void setBankPayAgainFee(BigDecimal bankPayAgainFee) {
        this.bankPayAgainFee = bankPayAgainFee;
    }

    @Column(name = "lakalaPayAgainFee", precision = 22, scale = 0)
    public BigDecimal getLakalaPayAgainFee() {
        return lakalaPayAgainFee;
    }

    public void setLakalaPayAgainFee(BigDecimal lakalaPayAgainFee) {
        this.lakalaPayAgainFee = lakalaPayAgainFee;
    }

    @Column(name = "aliPayAgainFee", precision = 22, scale = 0)
    public BigDecimal getAliPayAgainFee() {
        return aliPayAgainFee;
    }

    public void setAliPayAgainFee(BigDecimal aliPayAgainFee) {
        this.aliPayAgainFee = aliPayAgainFee;
    }

    @Column(name = "cancel_flg", length = 2)
    public String getCancelflg() {
        return cancelflg;
    }

    public void setCancelflg(String cancelflg) {
        this.cancelflg = cancelflg;
    }

    @Column(name = "crashHistoryType", length = 50)
    public String getCrashHistoryType() {
        return crashHistoryType;
    }

    public void setCrashHistoryType(String crashHistoryType) {
        this.crashHistoryType = crashHistoryType;
    }

    @Column(name = "deductionFee", precision = 22, scale = 0)
    public BigDecimal getDeductionFee() {
        return deductionFee;
    }

    public void setDeductionFee(BigDecimal deductionFee) {
        this.deductionFee = deductionFee;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    public TbStudent getTbStudent() {
        return tbStudent;
    }

    public void setTbStudent(TbStudent tbStudent) {
        this.tbStudent = tbStudent;
    }




}
