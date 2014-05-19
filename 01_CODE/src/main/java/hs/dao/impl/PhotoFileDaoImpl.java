package hs.dao.impl;

import hs.dao.PhotoFileDaoI;
import hs.model.TbPhotoFile;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("photoDao")
public class PhotoFileDaoImpl extends BaseDaoImpl<TbPhotoFile> implements PhotoFileDaoI {
    private static String type = "hs.model.TbPhotoFile";

    @Override
    public List<TbPhotoFile> findByExample(TbPhotoFile t) {
        List<TbPhotoFile> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbPhotoFile> findByExample(TbPhotoFile t, int page, int rows) {
        List<TbPhotoFile> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbPhotoFile> findByExample(TbPhotoFile t, int page, int rows, String sortName, String sortOrder) {
        List<TbPhotoFile> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
