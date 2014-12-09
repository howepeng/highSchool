package hs.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import hs.common.exception.ApplicationException;
import hs.util.MonitorLogUtil;
import hs.common.vo.SqlParameterVO;
import hs.common.vo.SqlResultVO;

public class QueryDAOiBatisImpl extends SqlMapClientDaoSupport implements
        QueryDAO {

    @Override
    @SuppressWarnings("unchecked")
    public <P> P queryForObject(String sql, SqlParameterVO params,
            Class<P> objectType) {
        MonitorLogUtil.writeMonitorLog(sql, params);
        SqlMapClientTemplate template = getSqlMapClientTemplate();
        Object obj = template.queryForObject(sql, params);

        if (obj == null) {
            return null;
        }

        if (obj.getClass() instanceof Class<?>) {
            return (P) obj;
        } else {
            throw new ApplicationException(
                    ApplicationException.SQL_RESULT_TYPE_INCORRECT, sql, obj
                            .getClass().toString(), objectType.toString());
        }
    }

    @Override
    public SqlResultVO queryForObject(String sql, SqlParameterVO params) {
        MonitorLogUtil.writeMonitorLog(sql, params);
        return queryForObject(sql, params, SqlResultVO.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <P> List<P> queryForList(String sql, SqlParameterVO params,
            Class<P> objectType) {

        MonitorLogUtil.writeMonitorLog(sql, params);
        SqlMapClientTemplate template = getSqlMapClientTemplate();
        List<Object> objList = template.queryForList(sql, params);

        if (objList == null || objList.isEmpty()) {
            return new ArrayList<P>();
        }

        Object type = objList.get(0).getClass();
        if (type instanceof Class<?>) {
            List<P> result = new ArrayList<P>();
            for (Object obj : objList) {
                result.add((P) obj);
            }

            return result;
        } else {
            throw new ApplicationException(
                    ApplicationException.SQL_RESULT_TYPE_INCORRECT, sql, type
                            .toString().toString(), objectType.toString());
        }
    }

    @Override
    public List<SqlResultVO> queryForList(String sql, SqlParameterVO params) {
        MonitorLogUtil.writeMonitorLog(sql, params);
        return queryForList(sql, params, SqlResultVO.class);
    }
}
