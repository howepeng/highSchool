package hs.common.vo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import hs.common.dao.QueryDAO;

public class SystemSettingBean extends BaseBean {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 2202073133168143443L;

    @Autowired
    private QueryDAO queryDAO = null;

    private Map<String, String> systemSettingMap = null;

    public void load() {

//        List<SqlResultVO> sqlResultList = queryDAO.queryForList(
//                "common.getSystemSetting", null);
//
//        systemSettingMap = new HashMap<String, String>();
//        for (SqlResultVO sqlResult : sqlResultList) {
//            systemSettingMap.put(sqlResult.getString("NAME"),
//                    sqlResult.getString("VALUE"));
//        }
    }

    public Map<String, String> getSystenSettingInfo() {
        return systemSettingMap;
    }
}
