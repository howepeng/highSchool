package hs.dao.impl;

import hs.dao.IdFileDaoI;
import hs.model.TbIdFile;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("idDao")
public class IdFileDaoImpl extends BaseDaoImpl<TbIdFile> implements IdFileDaoI {
    private static String type = "hs.model.TbIdFile";

    @Override
    public List<TbIdFile> findByExample(TbIdFile t) {
        List<TbIdFile> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbIdFile> findByExample(TbIdFile t, int page, int rows) {
        List<TbIdFile> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbIdFile> findByExample(TbIdFile t, int page, int rows, String sortName, String sortOrder) {
        List<TbIdFile> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
