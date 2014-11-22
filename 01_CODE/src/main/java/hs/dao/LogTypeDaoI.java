package hs.dao;

import hs.model.TbLogType;

import java.util.List;

public interface LogTypeDaoI extends BaseDaoI<TbLogType> {

    public List<TbLogType> findByExample(TbLogType t);

    public List<TbLogType> findByExample(TbLogType t, int page, int rows);

    public List<TbLogType> findByExample(TbLogType t, int page, int rows, String sortName, String sortOrder);

}
