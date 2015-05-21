package hs.pageModel;

import java.math.BigDecimal;
import java.util.Date;

public class DeductionFee implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private BigDecimal deductionFee;
    private String timeType;
    private String timeTypeShow;
    private Date startDate;
    private Date endDate;
    private String startDateShow;
    private String endDateShow;
    private String yearInfoId;
    private String yearInfoName;
    private String status;
    private String statusShow;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDeductionFee() {
        return deductionFee;
    }

    public void setDeductionFee(BigDecimal deductionFee) {
        this.deductionFee = deductionFee;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getYearInfoId() {
        return yearInfoId;
    }

    public void setYearInfoId(String yearInfoId) {
        this.yearInfoId = yearInfoId;
    }

    public String getYearInfoName() {
        return yearInfoName;
    }

    public void setYearInfoName(String yearInfoName) {
        this.yearInfoName = yearInfoName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusShow() {
        return statusShow;
    }

    public void setStatusShow(String statusShow) {
        this.statusShow = statusShow;
    }

    public String getTimeTypeShow() {
        return timeTypeShow;
    }

    public void setTimeTypeShow(String timeTypeShow) {
        this.timeTypeShow = timeTypeShow;
    }

    public String getStartDateShow() {
        return startDateShow;
    }

    public void setStartDateShow(String startDateShow) {
        this.startDateShow = startDateShow;
    }

    public String getEndDateShow() {
        return endDateShow;
    }

    public void setEndDateShow(String endDateShow) {
        this.endDateShow = endDateShow;
    }
}
