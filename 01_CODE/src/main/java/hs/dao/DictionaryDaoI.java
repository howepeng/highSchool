package hs.dao;

import hs.model.TbDictionary;

import java.util.List;

public interface DictionaryDaoI extends BaseDaoI<TbDictionary> {

    public List<TbDictionary> findByExample(TbDictionary t);

    public List<TbDictionary> findByExample(TbDictionary t, int page, int rows);

    public List<TbDictionary> findByExample(TbDictionary t, int page, int rows, String sortName, String sortOrder);

}
