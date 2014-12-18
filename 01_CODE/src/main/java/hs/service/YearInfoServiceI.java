package hs.service;

import hs.model.TbYearInfo;
import hs.pageModel.YearInfo;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;

import java.util.List;

public interface YearInfoServiceI {

    public List<Combobox> combox();

    public DataGrid datagrid(YearInfo yearInfo);

    public void add(YearInfo yearInfo);

    public boolean remove(String ids);

    public void edit(YearInfo yearInfo);

    public YearInfo getYearInfo(String id);

    public int setNotDefaultYear(YearInfo yearInfo);

    public Long getDefaultYearCount();

    public TbYearInfo getDefaultYear();
}
