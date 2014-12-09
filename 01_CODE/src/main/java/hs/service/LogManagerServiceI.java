package hs.service;

import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.LogInfo;
import hs.pageModel.ShowCalendarInfo;

import java.util.List;

public interface LogManagerServiceI {

    public List<Combobox> combox();

    public DataGrid datagrid(LogInfo logInfo);

    public void add(LogInfo logInfo);

    public void remove(String ids);

    public void edit(LogInfo logInfo);

    public LogInfo getLogInfo(String id);

    public List<ShowCalendarInfo> getShowCalendar(LogInfo logInfo);
}
