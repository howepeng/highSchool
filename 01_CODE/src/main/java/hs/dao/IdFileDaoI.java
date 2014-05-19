package hs.dao;

import hs.model.TbIdFile;

import java.util.List;

public interface IdFileDaoI extends BaseDaoI<TbIdFile> {

    public List<TbIdFile> findByExample(TbIdFile t);

    public List<TbIdFile> findByExample(TbIdFile t, int page, int rows);

    public List<TbIdFile> findByExample(TbIdFile t, int page, int rows, String sortName, String sortOrder);
}
