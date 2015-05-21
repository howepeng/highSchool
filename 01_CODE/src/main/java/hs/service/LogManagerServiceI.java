package hs.service;

import hs.pageModel.AttenceInfo;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.LogInfo;
import hs.pageModel.SessionInfo;
import hs.pageModel.ShowCalendarInfo;

import java.util.List;

public interface LogManagerServiceI {

    public List<Combobox> combox();

    public DataGrid datagrid(LogInfo logInfo, SessionInfo sessionInfo);

    public int add(LogInfo logInfo);

    public void remove(String ids);

    public int edit(LogInfo logInfo);

    public LogInfo getLogInfo(String id);

    public List<ShowCalendarInfo> getShowCalendar(LogInfo logInfo);

    public int getStudentCountByAttence(AttenceInfo attenceInfo);

    public int getStudentCountByDelay(AttenceInfo attenceInfo);
}
