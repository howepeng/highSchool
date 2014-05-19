package hs.dao.impl;

import java.util.List;

import hs.dao.FileDaoI;
import hs.model.TbFile;

import org.springframework.stereotype.Repository;

@Repository("fileDao")
public class FileDaoImpl extends BaseDaoImpl<TbFile> implements FileDaoI {
    private static String type = "hs.model.TbFile";

    @Override
    public List<TbFile> findByExample(TbFile t) {
        List<TbFile> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbFile> findByExample(TbFile t, int page, int rows) {
        List<TbFile> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbFile> findByExample(TbFile t, int page, int rows, String sortName, String sortOrder) {
        List<TbFile> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
