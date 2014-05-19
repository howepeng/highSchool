package hs.pageModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

/**
 * TbFinance entity. @author MyEclipse Persistence Tools
 */
@Entity
public class Finances implements java.io.Serializable {

    // Fields
    private List<Finance> finance;
    private String num;
    private String id;
    private String payee;
    private String classType;
    private String classTypeName;
    private String name;
    private String idNum;
    private String studentId;

    private BigDecimal studyFee;
    private BigDecimal stayFee;
    private BigDecimal selfFee;
    private BigDecimal signFee;
    private BigDecimal scoreFee;
    private BigDecimal safetyFee;
    private BigDecimal waterFee;
    private BigDecimal cashFee;
    private BigDecimal transferFee;
    private BigDecimal payAgainFee;
    private BigDecimal countPayFee;
    private BigDecimal preferentialFee;
    private BigDecimal arrearFee;
    private BigDecimal refundFee;
    private BigDecimal countReport;

    private Date createdatetime;
    private Date createdatetimeStart;
    private Date createdatetimeEnd;
    private int page;
    private int rows;
    private String sort;
    private String order;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPayee() {
        return payee;
    }
    public void setPayee(String payee) {
        this.payee = payee;
    }
    public String getClassType() {
        return classType;
    }
    public void setClassType(String classType) {
        this.classType = classType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdNum() {
        return idNum;
    }
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }
    public BigDecimal getSignFee() {
        return signFee;
    }
    public void setSignFee(BigDecimal signFee) {
        this.signFee = signFee;
    }
    public BigDecimal getArrearFee() {
        return arrearFee;
    }
    public void setArrearFee(BigDecimal arrearFee) {
        this.arrearFee = arrearFee;
    }
    public BigDecimal getRefundFee() {
        return refundFee;
    }
    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }
    public Date getCreatedatetime() {
        return createdatetime;
    }
    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }
    public Date getCreatedatetimeStart() {
        return createdatetimeStart;
    }
    public void setCreatedatetimeStart(Date createdatetimeStart) {
        this.createdatetimeStart = createdatetimeStart;
    }
    public Date getCreatedatetimeEnd() {
        return createdatetimeEnd;
    }
    public void setCreatedatetimeEnd(Date createdatetimeEnd) {
        this.createdatetimeEnd = createdatetimeEnd;
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
    public List<Finance> getFinance() {
        return finance;
    }
    public void setFinance(List<Finance> finance) {
        this.finance = finance;
    }
    public String getClassTypeName() {
        return classTypeName;
    }
    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public BigDecimal getStudyFee() {
        return studyFee;
    }
    public void setStudyFee(BigDecimal studyFee) {
        this.studyFee = studyFee;
    }
    public BigDecimal getStayFee() {
        return stayFee;
    }
    public void setStayFee(BigDecimal stayFee) {
        this.stayFee = stayFee;
    }
    public BigDecimal getSelfFee() {
        return selfFee;
    }
    public void setSelfFee(BigDecimal selfFee) {
        this.selfFee = selfFee;
    }
    public BigDecimal getScoreFee() {
        return scoreFee;
    }
    public void setScoreFee(BigDecimal scoreFee) {
        this.scoreFee = scoreFee;
    }
    public BigDecimal getSafetyFee() {
        return safetyFee;
    }
    public void setSafetyFee(BigDecimal safetyFee) {
        this.safetyFee = safetyFee;
    }
    public BigDecimal getWaterFee() {
        return waterFee;
    }
    public void setWaterFee(BigDecimal waterFee) {
        this.waterFee = waterFee;
    }
    public BigDecimal getCashFee() {
        return cashFee;
    }
    public void setCashFee(BigDecimal cashFee) {
        this.cashFee = cashFee;
    }
    public BigDecimal getTransferFee() {
        return transferFee;
    }
    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }
    public BigDecimal getPayAgainFee() {
        return payAgainFee;
    }
    public void setPayAgainFee(BigDecimal payAgainFee) {
        this.payAgainFee = payAgainFee;
    }
    public BigDecimal getCountPayFee() {
        return countPayFee;
    }
    public void setCountPayFee(BigDecimal countPayFee) {
        this.countPayFee = countPayFee;
    }
    public BigDecimal getPreferentialFee() {
        return preferentialFee;
    }
    public void setPreferentialFee(BigDecimal preferentialFee) {
        this.preferentialFee = preferentialFee;
    }
    public BigDecimal getCountReport() {
        return countReport;
    }
    public void setCountReport(BigDecimal countReport) {
        this.countReport = countReport;
    }
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }




}
