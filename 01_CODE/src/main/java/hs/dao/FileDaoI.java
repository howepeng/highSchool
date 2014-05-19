package hs.dao;

import java.util.List;

import hs.model.TbFile;

public interface FileDaoI extends BaseDaoI<TbFile> {

    public List<TbFile> findByExample(TbFile t);

    public List<TbFile> findByExample(TbFile t, int page, int rows);

    public List<TbFile> findByExample(TbFile t, int page, int rows, String sortName, String sortOrder);
}
