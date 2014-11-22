package hs.service;

import hs.pageModel.ClassTime;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;

import java.util.List;

public interface ClassTimeServiceI {

    public List<Combobox> combox();

    public DataGrid datagrid(ClassTime classTime);

    public void add(ClassTime classTime);

    public void remove(String ids);

    public void edit(ClassTime classTime);

    public ClassTime getClassTime(String id);
}
