package hs.service;

import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.DormitoryInfo;

import java.util.List;

public interface DormitoryInfoServiceI {

    public List<Combobox> combox();

    public DataGrid datagrid(DormitoryInfo dormitoryInfo);

    public void add(DormitoryInfo dormitoryInfo);

    public boolean remove(String ids);

    public void edit(DormitoryInfo dormitoryInfo);

    public DormitoryInfo getDormitoryInfo(String id);
}
