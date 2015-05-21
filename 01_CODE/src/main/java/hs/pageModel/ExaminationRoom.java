package hs.pageModel;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class ExaminationRoom implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String professionalId;
    private String professionalName;
    private String dispatchType;
    private String dispatchTypeName;
    private String row;
    private String column;
    private String name;
    private String rangeOrder;
    private String rangeOrderName;
    private String monthTimeId1;
    private String monthTimeName1;
    private String monthTimeId2;
    private String monthTimeName2;
    private String fileName;
    private int page;
    private int rows;
    private String sort;
    private String order;
    private String ids;// 删除用 id字符串（用逗号分隔）

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

    public String getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonthTimeId1() {
        return monthTimeId1;
    }

    public void setMonthTimeId1(String monthTimeId1) {
        this.monthTimeId1 = monthTimeId1;
    }

    public String getMonthTimeName1() {
        return monthTimeName1;
    }

    public void setMonthTimeName1(String monthTimeName1) {
        this.monthTimeName1 = monthTimeName1;
    }

    public String getMonthTimeId2() {
        return monthTimeId2;
    }

    public void setMonthTimeId2(String monthTimeId2) {
        this.monthTimeId2 = monthTimeId2;
    }

    public String getMonthTimeName2() {
        return monthTimeName2;
    }

    public void setMonthTimeName2(String monthTimeName2) {
        this.monthTimeName2 = monthTimeName2;
    }

    public String getRangeOrder() {
        return rangeOrder;
    }

    public void setRangeOrder(String rangeOrder) {
        this.rangeOrder = rangeOrder;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public String getDispatchTypeName() {
        return dispatchTypeName;
    }

    public void setDispatchTypeName(String dispatchTypeName) {
        this.dispatchTypeName = dispatchTypeName;
    }

    public String getRangeOrderName() {
        return rangeOrderName;
    }

    public void setRangeOrderName(String rangeOrderName) {
        this.rangeOrderName = rangeOrderName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
