package hs.pageModel;

import java.io.Serializable;

public class SessionInfo implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String password;
    private String roleIds;
    private String yearId;
    private boolean isOnlyDirector;

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
    public String getRoleIds() {
        return roleIds;
    }
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
    public String getYearId() {
        return yearId;
    }
    public void setYearId(String yearId) {
        this.yearId = yearId;
    }
    public boolean isOnlyDirector() {
        return isOnlyDirector;
    }
    public void setOnlyDirector(boolean isOnlyDirector) {
        this.isOnlyDirector = isOnlyDirector;
    }
}
