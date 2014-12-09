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


}
