package hs.dao;

import hs.model.TbPhotoFile;

import java.util.List;

public interface PhotoFileDaoI extends BaseDaoI<TbPhotoFile> {

    public List<TbPhotoFile> findByExample(TbPhotoFile t);

    public List<TbPhotoFile> findByExample(TbPhotoFile t, int page, int rows);

    public List<TbPhotoFile> findByExample(TbPhotoFile t, int page, int rows, String sortName, String sortOrder);
}
