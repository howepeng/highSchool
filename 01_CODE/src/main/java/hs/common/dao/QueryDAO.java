package hs.common.dao;

import java.util.List;

import hs.common.vo.SqlParameterVO;
import hs.common.vo.SqlResultVO;

public interface QueryDAO {

    public <P> P queryForObject(String sql, SqlParameterVO params,
            Class<P> objectType);

    public SqlResultVO queryForObject(String sql, SqlParameterVO params);

    public <P> List<P> queryForList(String sql, SqlParameterVO params,
            Class<P> objectType);

    public List<SqlResultVO> queryForList(String sql, SqlParameterVO params);
}
