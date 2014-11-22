package hs.service.impl;

import hs.dao.DictionaryDaoI;
import hs.model.TbDictionary;
import hs.pageModel.DataGrid;
import hs.pageModel.Dictionary;
import hs.service.DictionaryServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryServiceI {

    private DictionaryDaoI dictionaryDao;

    @Autowired
    public void setictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public List<Dictionary> combox(Dictionary dictionary) {
        List<Dictionary> rl = new ArrayList<Dictionary>();
        List<TbDictionary> l = dictionaryDao.find("from TbDictionary where fatherId = '"+ dictionary.getFatherId() +"'");
        if (l != null && l.size() > 0) {
            for (TbDictionary t : l) {
                Dictionary r = new Dictionary();
                r.setId(t.getId());
                r.setName(t.getName());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(Dictionary dictionary) {
        DataGrid j = new DataGrid();
        j.setRows(dictionaryDao.find("FROM TbDictionary where fatherId = '"+ dictionary.getFatherId() +"'", dictionary.getPage(), dictionary.getRows()));
        j.setTotal(dictionaryDao.count("SELECT count(*) FROM TbDictionary where fatherId = '"+ dictionary.getFatherId() +"'"));
        return j;
    }

    @Override
    public void add(Dictionary dictionary) {
        TbDictionary r = new TbDictionary();
        BeanUtils.copyProperties(dictionary, r);
        r.setId(UUID.randomUUID().toString());
        dictionaryDao.save(r);
    }

    @Override
    public void edit(Dictionary dictionary) {
        TbDictionary r = dictionaryDao.getById(TbDictionary.class, dictionary.getId());
        BeanUtils.copyProperties(dictionary, r, new String[] { "id" });
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbDictionary r = dictionaryDao.getById(TbDictionary.class, id.trim());
                if (r != null) {
                    dictionaryDao.delete(r);
                }
            }
        }
    }
}
