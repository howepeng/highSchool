package hs.dao;

import hs.model.TbLogResult;

import java.util.List;

public interface LogResultDaoI extends BaseDaoI<TbLogResult> {

    public List<TbLogResult> findByExample(TbLogResult t);

    public List<TbLogResult> findByExample(TbLogResult t, int page, int rows);

    public List<TbLogResult> findByExample(TbLogResult t, int page, int rows, String sortName, String sortOrder);

}
