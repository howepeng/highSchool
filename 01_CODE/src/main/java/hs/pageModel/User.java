package hs.pageModel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String username;
    private String password;
    private Date createdatetime;
    private Date createdatetimeStart;
    private Date createdatetimeEnd;
    private Date modifydatetime;
    private Date modifydatetimeStart;
    private Date modifydatetimeEnd;
    private int page;
    private int rows;
    private String sort;
    private String order;
    private String ids;//删除用 id字符串（用逗号分隔）
    private String roleIds;
    private String roleNames;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getCreatedatetime() {
        return createdatetime;
    }
    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }
    public Date getModifydatetime() {
        return modifydatetime;
    }
    public void setModifydatetime(Date modifydatetime) {
        this.modifydatetime = modifydatetime;
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
    public Date getModifydatetimeStart() {
        return modifydatetimeStart;
    }
    public void setModifydatetimeStart(Date modifydatetimeStart) {
        this.modifydatetimeStart = modifydatetimeStart;
    }
    public Date getModifydatetimeEnd() {
        return modifydatetimeEnd;
    }
    public void setModifydatetimeEnd(Date modifydatetimeEnd) {
        this.modifydatetimeEnd = modifydatetimeEnd;
    }
    public String getRoleIds() {
        return roleIds;
    }
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
    public String getRoleNames() {
        return roleNames;
    }
    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
