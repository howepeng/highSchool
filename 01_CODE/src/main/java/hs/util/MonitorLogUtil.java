/**
 *
 */
package hs.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import hs.common.vo.UserInfoBean;
import hs.common.CommonConst;
import hs.common.vo.SqlParameterVO;

public class MonitorLogUtil {
    private static Log log = LogFactory.getLog(MonitorLogUtil.class);

    private static String SPLIT_CHAR = "\t";
    private static String PROP_SPLIT_CHAR = ",";
    private static String SQLID_SELECT = "SELECT";
    private static String SQLID_INSERT = "INSERT";
    private static String SQLID_UPDATE = "UPDATE";
    private static String SQLID_DELETE = "DELETE";
    private static String SQLID_LOGIN = "LOGIN";
    private static String SQLID_LOGOUT = "LOGOUT";
    private static String SQLID_DOWNLOAD = "DOWNLOAD";
    private static String SQLID_UPLOAD = "UPLOAD";
    public static String PROP_LOGIN = "monitor.login";
    public static String PROP_LOGOUT = "monitor.logout";
    public static String PROP_DL = "file.download";
    public static String PROP_UP = "file.upload";
    private static String LOGIN_SQLID = "xtlj999.updateUserInfo";
    private static String MONITOR_MAP_FILE_NAME = "monitoermap.properties";
    private static Properties PROP = null;

    private MonitorLogUtil() {

    }

    public static void writeMonitorLog(String sqlId, Object parmObject) {
        String operationString = null;
        UserInfoBean uvo = null;
        if (log.isInfoEnabled()
                && (operationString = getOperationString(sqlId, parmObject)) != null
                && (uvo = UVOUtil.getThreadLocalUserInfo()) != null) {
            try {
                StringBuilder sb = new StringBuilder();

                String[] params = operationString.split(PROP_SPLIT_CHAR);
                params = splitString(operationString, PROP_SPLIT_CHAR);

                //IP
                sb.append(UVOUtil.getThreadLocalIP()).append(SPLIT_CHAR);

                sb.append(uvo.getBaseInfo().getUserId()).append(SPLIT_CHAR);

                sb.append(params[0]).append(SPLIT_CHAR);

                sb.append(getDestId(params[2], parmObject)).append(SPLIT_CHAR);

                sb.append(getOperationContent(params[1], params[3], parmObject)).append(SPLIT_CHAR);

                //sql id
                sb.append(params[4]);

                log.info(sb.toString());
            } catch (Exception ex) {

            }
        }
    }

    private static String getOperationString(String sqlId, Object parmObject) {
        StringBuilder sbReturn = null;
        String oprString = null;
        String sqlType = null;

        try {
            int isLogin = isLogin(sqlId, parmObject);
            switch (isLogin) {
            case CommonConst.ON_LINE:
                oprString = getOperationValue(PROP_LOGIN);
                sqlType = SQLID_LOGIN;
                break;
            case CommonConst.OFF_LINE:
                oprString = getOperationValue(PROP_LOGOUT);
                sqlType = SQLID_LOGOUT;
                break;
            default:
                oprString = getOperationValue(sqlId);
                if (oprString != null) {
                    if (sqlId.equals(PROP_DL)) {
                        //����
                        sqlType = SQLID_DOWNLOAD;
                    } else if (sqlId.equals(PROP_UP)) {
                        //�ϴ�
                        sqlType = SQLID_UPLOAD;
                    } else {
                        sqlId = sqlId.substring(sqlId.indexOf(".") + 1).toUpperCase();
                        if (sqlId.startsWith(SQLID_INSERT)
                            || sqlId.startsWith(SQLID_UPDATE)
                            || sqlId.startsWith(SQLID_DELETE)) {
                        sqlType = sqlId.substring(0, 6);
                        } else {
                            sqlType = SQLID_SELECT;
                        }
                    }
                }
            }
            if (oprString != null) {
                sbReturn = new StringBuilder();
                sbReturn.append(sqlType).append(PROP_SPLIT_CHAR);
                sbReturn.append(oprString).append(PROP_SPLIT_CHAR);
                sbReturn.append(sqlId);
            }

            if (sbReturn == null) {
                return null;
            } else {
                return sbReturn.toString();
            }
        } catch (Exception ex) {
            return null;
        }
    }

    private static String getDestId(String prop, Object parmObject) {
        String destId = "";
        if (!StringUtils.isEmpty(prop) && parmObject != null) {
            try {
                Method method = parmObject.getClass().getMethod(prop);
                Object invokeValue = method.invoke(parmObject);
                if (invokeValue != null) {
                    return invokeValue.toString();
                }
            } catch (Exception e) {
            }
        }

        return destId;
    }

    private static String getOperationContent(String operationText, String prop, Object parmObject) {
        String replace = "";

        try {
            if (!StringUtils.isEmpty(prop)) {
                if (parmObject instanceof Map<?, ?>) {
                    Object mapValue = ((Map<?, ?>)parmObject).get(prop);
                    if (mapValue != null) {
                        replace = mapValue.toString();
                    }
                } else {
                    Method method = parmObject.getClass().getMethod(prop);
                    Object invokeValue = method.invoke(parmObject);
                    if (invokeValue != null) {
                        replace = invokeValue.toString();
                    }
                }
            }
        } catch (Exception ex) {

        }

        return operationText.replace("{0}", replace);
    }

    private static synchronized String getOperationValue(String sqlId) {
        if (PROP == null) {
            try {
                PROP = new Properties();
                ClassLoader classLoader = Thread.currentThread()
                        .getContextClassLoader();
                if (classLoader == null) {
                    classLoader = MonitorLogUtil.class.getClassLoader();
                }
                InputStream is = Thread.currentThread()
                        .getContextClassLoader().getResourceAsStream(MONITOR_MAP_FILE_NAME);

                PROP.load(is);
            } catch (IOException e) {
                log.error("can not be load monitor map config:[" + MONITOR_MAP_FILE_NAME + "]",
                        e);
            }
        }

        if (PROP != null) {
            return PROP.getProperty(sqlId);
        } else {
            return null;
        }
    }

    private static int isLogin(String sqlId, Object parmObject) {
        if (LOGIN_SQLID.equals(sqlId)) {
            SqlParameterVO sqlParam = (SqlParameterVO) parmObject;
            if (sqlParam.get("online") != null) {
                return ((Number)sqlParam.get("online")).intValue();
            } else {
                return 99;
            }
        } else {
            return 99;
        }
    }

    private static String[] splitString(String source, String split) {
        if (source == null) return new String[0];

        List<Integer> lsz = new ArrayList<Integer>();
        lsz.add(-1);

        int splitIdx = 0;
        while ((splitIdx = source.indexOf(split, lsz.get(lsz.size() - 1) + 1)) >= 0) {
            lsz.add(splitIdx);
        }

        String[] result = new String[lsz.size()];
        int i;
        for (i = 0; i < result.length - 1; i++) {
            result[i] = source.substring(lsz.get(i) + 1, lsz.get(i + 1));
        }
        result[i] = source.substring(lsz.get(i) + 1);

        return result;
    }
}
