package hs.service;

import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.LogResult;

import java.util.List;

public interface LogResultServiceI {

    public List<Combobox> combox();

    public DataGrid datagrid(LogResult logResult);

    public void add(LogResult logResult);

    public void remove(String ids);

    public void edit(LogResult logResult);

    public LogResult getLogResult(String id);
}
