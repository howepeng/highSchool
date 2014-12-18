package hs.service;

import hs.pageModel.ClassInfo;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;

import java.util.List;

public interface ClassInfoServiceI {

    public List<Combobox> combox();

    public DataGrid datagrid(ClassInfo classInfo);

    public void add(ClassInfo classInfo);

    public void remove(String ids);

    public void edit(ClassInfo classInfo);

    public ClassInfo getClassInfo(String id);

    public DataGrid datagridClassInfo(ClassInfo classInfo);
}
