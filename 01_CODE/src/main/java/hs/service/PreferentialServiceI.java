package hs.service;

import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.Preferential;

import java.util.List;

public interface PreferentialServiceI {

	public List<Combobox> combox();

	public DataGrid datagrid(Preferential preferential);

	public void add(Preferential preferential);

	public void remove(String ids);

	public void edit(Preferential preferential);

	public Preferential getPreferential(String id);
}
