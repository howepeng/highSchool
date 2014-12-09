package hs.dao;

import hs.model.TbLogInfo;

import java.util.List;

public interface LogManagerDaoI extends BaseDaoI<TbLogInfo> {

    public List<TbLogInfo> findByExample(TbLogInfo t);

    public List<TbLogInfo> findByExample(TbLogInfo t, int page, int rows);

    public List<TbLogInfo> findByExample(TbLogInfo t, int page, int rows, String sortName, String sortOrder);

}
