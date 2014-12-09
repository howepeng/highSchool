package hs.pageModel;

import java.io.Serializable;
import java.sql.Time;

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
    private Time startTime;
    private Time endTime;
    private String showStartTime;
    private String showEndTime;
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
}
