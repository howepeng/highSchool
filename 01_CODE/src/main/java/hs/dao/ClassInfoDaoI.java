package hs.dao;

import hs.model.TbClassInfo;

import java.util.List;

public interface ClassInfoDaoI extends BaseDaoI<TbClassInfo> {

    public List<TbClassInfo> findByExample(TbClassInfo t);

    public List<TbClassInfo> findByExample(TbClassInfo t, int page, int rows);

    public List<TbClassInfo> findByExample(TbClassInfo t, int page, int rows, String sortName, String sortOrder);

}
