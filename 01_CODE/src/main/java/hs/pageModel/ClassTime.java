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
public class ClassTime implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String startDicId;
    private String startDicName;
    private String endDicId;
    private String endDicName;
    private int page;
    private int rows;
    private String sort;
    private String order;
    private String ids;//删除用 id字符串（用逗号分隔）
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStartDicId() {
        return startDicId;
    }
    public void setStartDicId(String startDicId) {
        this.startDicId = startDicId;
    }
    public String getStartDicName() {
        return startDicName;
    }
    public void setStartDicName(String startDicName) {
        this.startDicName = startDicName;
    }
    public String getEndDicId() {
        return endDicId;
    }
    public void setEndDicId(String endDicId) {
        this.endDicId = endDicId;
    }
    public String getEndDicName() {
        return endDicName;
    }
    public void setEndDicName(String endDicName) {
        this.endDicName = endDicName;
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
