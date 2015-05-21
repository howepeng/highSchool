package hs.service;

import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.MonthInfo;

import java.util.List;

public interface MonthInfoServiceI {

    public List<Combobox> combox();

    public DataGrid datagrid(MonthInfo monthInfo);

    public void add(MonthInfo monthInfo);

    public void remove(String ids);

    public void edit(MonthInfo monthInfo);

    public MonthInfo getMonthInfo(String id);
}
