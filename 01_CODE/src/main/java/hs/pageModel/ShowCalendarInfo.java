package hs.pageModel;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class ShowCalendarInfo implements Serializable {

     /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String classTimeId;
    private String classTimeName;
    private String logTypeId;
    private String typeName;
    private String logTypeName;
    private Time startTime;
    private Time endTime;
    private Date date;
    private int count;
    private String classId;
    private String className;
    private String studentId;
    private String studentName;
    private String resultId;
    private String resultName;
    private String remark;
    private String showStartTime;
    private String showEndTime;
    private String showDate;
    private int page;
    private int rows;
    private String sort;
    private String order;
    private String ids;//删除用 id字符串（用逗号分隔）
    public int getPage() {
        return page;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
    public Time getStartTime() {
        return startTime;
    }
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    public Time getEndTime() {
        return endTime;
    }
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    public String getClassTimeId() {
        return classTimeId;
    }
    public void setClassTimeId(String classTimeId) {
        this.classTimeId = classTimeId;
    }
    public String getClassTimeName() {
        return classTimeName;
    }
    public void setClassTimeName(String classTimeName) {
        this.classTimeName = classTimeName;
    }
    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getResultId() {
        return resultId;
    }
    public void setResultId(String resultId) {
        this.resultId = resultId;
    }
    public String getResultName() {
        return resultName;
    }
    public void setResultName(String resultName) {
        this.resultName = resultName;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getShowStartTime() {
        return showStartTime;
    }
    public void setShowStartTime(String showStartTime) {
        this.showStartTime = showStartTime;
    }
    public String getShowEndTime() {
        return showEndTime;
    }
    public void setShowEndTime(String showEndTime) {
        this.showEndTime = showEndTime;
    }
    public String getShowDate() {
        return showDate;
    }
    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }
	public String getLogTypeId() {
		return logTypeId;
	}
	public void setLogTypeId(String logTypeId) {
		this.logTypeId = logTypeId;
	}
	public String getLogTypeName() {
		return logTypeName;
	}
	public void setLogTypeName(String logTypeName) {
		this.logTypeName = logTypeName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
