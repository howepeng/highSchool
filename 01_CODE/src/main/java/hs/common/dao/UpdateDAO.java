package hs.common.dao;

import hs.common.vo.BaseVO;
import hs.common.vo.SqlParameterVO;

public interface UpdateDAO {

    public Number insert(BaseVO tableData);

    public Integer update(String sql, SqlParameterVO params);
}
