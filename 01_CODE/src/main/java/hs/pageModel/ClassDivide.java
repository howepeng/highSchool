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
public class ClassDivide implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String yearId;
    private String classType;
    private String classNum;
    private String classModeId;
    private String classPrefixion;
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
    public String getYearId() {
        return yearId;
    }
    public void setYearId(String yearId) {
        this.yearId = yearId;
    }
    public String getClassType() {
        return classType;
    }
    public void setClassType(String classType) {
        this.classType = classType;
    }
    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
    public String getClassModeId() {
        return classModeId;
    }
    public void setClassModeId(String classModeId) {
        this.classModeId = classModeId;
    }
    public String getClassPrefixion() {
        return classPrefixion;
    }
    public void setClassPrefixion(String classPrefixion) {
        this.classPrefixion = classPrefixion;
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


}
