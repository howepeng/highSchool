package hs.service;

import hs.pageModel.ClassType;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;

import java.util.List;

public interface ClassTypeServiceI {

	public List<Combobox> combox();

	public DataGrid datagrid(ClassType classType);

	public void add(ClassType classType);

	public void remove(String ids);

	public void edit(ClassType classType);

	public ClassType getClassType(String id);
}
