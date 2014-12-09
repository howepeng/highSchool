package hs.common.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import hs.common.exception.ApplicationException;
import hs.util.MonitorLogUtil;
import hs.common.vo.BaseVO;
import hs.common.vo.EntUserVO;
import hs.common.vo.SqlParameterVO;

@SuppressWarnings("rawtypes")
public class UpdateDAOiBatisImpl extends SqlMapClientDaoSupport implements
        UpdateDAO {

    private static Map<Class, String> simpleInsertSqlIdMap = null;

    static {
        simpleInsertSqlIdMap = new HashMap<Class, String>();
        simpleInsertSqlIdMap.put(EntUserVO.class, "insertEntUser");
    }

    @Override
    public Number insert(BaseVO tableData) {

        String sql = simpleInsertSqlIdMap.get(tableData.getClass());
        if (sql == null) {
            throw new ApplicationException(
                    ApplicationException.SQL_INSERT_ID_NOT_EXIST, tableData
                            .getClass().getSimpleName());
        }

        SqlMapClientTemplate template = getSqlMapClientTemplate();
        MonitorLogUtil.writeMonitorLog("common." + sql, tableData);
        Object result = template.insert("common." + sql, tableData);
        if (result == null) {
            return null;
        }

        return (Number) result;
    }

    @Override
    public Integer update(String sql, SqlParameterVO params) {

        MonitorLogUtil.writeMonitorLog(sql, params);
        SqlMapClientTemplate template = getSqlMapClientTemplate();
        int result = template.update(sql, params);

        return result;
    }
}
