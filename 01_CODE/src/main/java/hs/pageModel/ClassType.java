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
public class ClassType implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String classType;
    private BigDecimal studyFee;
    private BigDecimal stayFee;
    private BigDecimal selfFee;
    private BigDecimal signFee;
    private BigDecimal scoreFee;
    private BigDecimal safetyFee;
    private BigDecimal waterFee;
    private BigDecimal countFee;
    private int page;
    private int rows;
    private String sort;
    private String order;
    private String ids;// 删除用 id字符串（用逗号分隔）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
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

    public BigDecimal getSignFee() {
        return signFee;
    }

    public void setSignFee(BigDecimal signFee) {
        this.signFee = signFee;
    }

    public BigDecimal getScoreFee() {
        return scoreFee;
    }

    public void setScoreFee(BigDecimal scoreFee) {
        this.scoreFee = scoreFee;
    }

    public BigDecimal getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(BigDecimal waterFee) {
        this.waterFee = waterFee;
    }

    public BigDecimal getCountFee() {
        return countFee;
    }

    public void setCountFee(BigDecimal countFee) {
        this.countFee = countFee;
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

    public BigDecimal getSafetyFee() {
        return safetyFee;
    }

    public void setSafetyFee(BigDecimal safetyFee) {
        this.safetyFee = safetyFee;
    }

}
