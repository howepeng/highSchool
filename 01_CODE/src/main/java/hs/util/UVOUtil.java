package hs.util;

import org.apache.struts2.ServletActionContext;

import hs.common.vo.UserInfoBean;
import hs.pageModel.SessionInfo;

public class UVOUtil {
    private UVOUtil() {

    }

    private static ThreadLocal<UserInfoBean> USER_INFO
        = new ThreadLocal<UserInfoBean>();

    public static UserInfoBean getThreadLocalUserInfo() {
        return USER_INFO.get();
    }

    public static void setThreadLocalUserInfo(UserInfoBean userInfo) {
        USER_INFO.set(userInfo);
    }

    public static void removeThreadLocalUserInfo() {
        USER_INFO.remove();
    }

    private static ThreadLocal<String> USER_IP
        = new ThreadLocal<String>();

    public static String getThreadLocalIP() {
        return USER_IP.get();
    }

    public static void setThreadLocalIP(String value) {
        USER_IP.set(value);
    }

    public static void removeThreadLocalIP() {
        USER_IP.remove();
    }

    public static boolean isDirector(){
        SessionInfo sessionInfo =
                (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
        if (sessionInfo.getRoleIds() != null) {
            String[] rid = sessionInfo.getRoleIds().split(",");
            for (String item : rid) {
                if ("0".equals(item) || "df3ab88d-c5a2-4f82-aef0-597468fd70d0".equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }
}
