package hs.common.vo;

public class EntUserVO extends BaseVO {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 8274181893191926789L;

    private int userId = 0;

    private String userMail = null;

    private String password = null;

    private String realName = null;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
