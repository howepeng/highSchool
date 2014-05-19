package hs.service;

import hs.pageModel.DataGrid;
import hs.pageModel.Role;

import java.util.List;

public interface RoleServiceI {

	public List<Role> combox();

	public DataGrid datagrid(Role role);

	public void add(Role role);

	public void remove(String ids);

	public void edit(Role role);
}
