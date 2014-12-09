package hs.util;

import hs.common.DemoApplicationContext;
import hs.common.vo.SystemSettingBean;
import hs.common.CommonConst;
import hs.common.exception.ApplicationException;

public abstract class SystemSettingUtil {

    private static SystemSettingBean systemSetting = null;

    public static void reloadSetteingInfo() {
        getSystemSettingBean();
        systemSetting.load();
    }

    public static boolean getUpgradePermit() {

        String value = getSettingValue(CommonConst.SYSTEM_SETTING_UPGRADE_PERMIT);
        if (value.equals("1")) {
            return true;
        }

        return false;
    }

    public static int getExperienceTimeLimit() {
        return ConvertUtil
                .toInt(getSettingValue(CommonConst.SYSTEM_SETTING_EXPERIENCE_TIME_LIMIT));
    }

    public static int getPageItemCount() {
        return ConvertUtil
                .toInt(getSettingValue(CommonConst.SYSTEM_SETTING_PAGE_ITEM_COUNT));
    }

    public static int getMsgLimit() {
        return ConvertUtil
                .toInt(getSettingValue(CommonConst.SYSTEM_SETTING_MSG_LIMIT));
    }

    public static long getUploadFileSizeLimit() {
        long setting = ConvertUtil
                .toInt(getSettingValue(CommonConst.SYSTEM_SETTING_UPLOAD_FILE_SIZE_LIMIT));
        return setting * 1024 * 1024;
    }

    public static long getResourceSpaceSizeLimit() {
        long setting = ConvertUtil
                .toInt(getSettingValue(CommonConst.SYSTEM_SETTING_RESOURCE_SPACE_SIZE_LIMIT));
        return setting * 1024 * 1024;
    }

    public static long getIconSizeLimit() {
        long setting = ConvertUtil
                .toInt(getSettingValue(CommonConst.SYSTEM_SETTING_ICON_SIZE_LIMIT));
        return setting * 1024 * 1024;
    }

    public static String getFilesystemDir() {
        return getSettingValue(CommonConst.SYSTEM_SETTING_FILESYSTEM_DIR);
    }

    private static String getSettingValue(String name) {
        getSystemSettingBean();

        String result = systemSetting.getSystenSettingInfo().get(name);
        if (result == null) {
            throw new ApplicationException(
                    ApplicationException.SYSTEM_SETTING_NOT_EXIST, name);
        }

        return result;
    }

    private synchronized static void getSystemSettingBean() {
        if (systemSetting == null) {
            systemSetting = (SystemSettingBean) DemoApplicationContext
                    .getBean("systemSetting");
        }
    }
}
