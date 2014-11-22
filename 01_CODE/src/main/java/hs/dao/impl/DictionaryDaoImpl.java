package hs.dao.impl;

import hs.dao.DictionaryDaoI;
import hs.model.TbDictionary;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("dictionaryDao")
public class DictionaryDaoImpl extends BaseDaoImpl<TbDictionary> implements DictionaryDaoI {

    private static String type = "hs.model.TbDictionary";

    @Override
    public List<TbDictionary> findByExample(TbDictionary t) {
        List<TbDictionary> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbDictionary> findByExample(TbDictionary t, int page, int rows) {
        List<TbDictionary> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbDictionary> findByExample(TbDictionary t, int page, int rows, String sortName, String sortOrder) {
        List<TbDictionary> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
