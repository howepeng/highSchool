package hs.dao;

import hs.model.TbClassTime;

import java.util.List;

public interface ClassTimeDaoI extends BaseDaoI<TbClassTime> {

    public List<TbClassTime> findByExample(TbClassTime t);

    public List<TbClassTime> findByExample(TbClassTime t, int page, int rows);

    public List<TbClassTime> findByExample(TbClassTime t, int page, int rows, String sortName, String sortOrder);

}
