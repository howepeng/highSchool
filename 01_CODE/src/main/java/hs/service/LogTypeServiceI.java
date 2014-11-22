package hs.service;

import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.LogType;

import java.util.List;

public interface LogTypeServiceI {

    public List<Combobox> combox();

    public DataGrid datagrid(LogType logType);

    public void add(LogType logType);

    public void remove(String ids);

    public void edit(LogType logType);

    public LogType getLogType(String id);
}
