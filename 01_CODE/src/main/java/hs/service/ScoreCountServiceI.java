package hs.service;

import hs.common.vo.SqlParameterVO;
import hs.common.vo.SqlResultVO;
import hs.pageModel.DataGrid;
import hs.pageModel.ScoreCount;
import hs.pageModel.SessionInfo;

import java.util.List;

public interface ScoreCountServiceI {

    public DataGrid datagrid(ScoreCount scoreCount, SessionInfo sessionInfo);
    public void remove(String ids);
    public void calculate(ScoreCount scoreCount);
    public SqlResultVO getScoreCountForFractionLine(SqlParameterVO param);
    public SqlResultVO getFractionForFractionLine(SqlParameterVO param);
    public List<SqlResultVO> getStudentListForFractionLine(SqlParameterVO param);
    public List<ScoreCount> getScoreCountList(ScoreCount scoreCount, SessionInfo sessionInfo);
}
