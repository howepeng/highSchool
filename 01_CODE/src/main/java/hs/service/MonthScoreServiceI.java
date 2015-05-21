package hs.service;

import java.util.List;

import hs.common.vo.SqlResultVO;
import hs.pageModel.DataGrid;
import hs.pageModel.MonthScore;
import hs.pageModel.SessionInfo;

public interface MonthScoreServiceI {

    public DataGrid datagrid(MonthScore monthScore, SessionInfo sessionInfo);
    public void remove(String ids);
    public String[] uploadClassScore(MonthScore monthScore);
    public SqlResultVO averageClassType(MonthScore monthScore);
    public SqlResultVO averageClassName(MonthScore monthScore);
    public List<MonthScore> getMonthScoreList(MonthScore monthScore, SessionInfo sessionInfo);

}
