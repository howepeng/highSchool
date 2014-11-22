package hs.service;

import hs.pageModel.DataGrid;
import hs.pageModel.Dictionary;

import java.util.List;

public interface DictionaryServiceI {

    public List<Dictionary> combox(Dictionary dictionary);

    public DataGrid datagrid(Dictionary dictionary);

    public void add(Dictionary dictionary);

    public void remove(String ids);

    public void edit(Dictionary dictionary);
}
