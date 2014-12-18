package hs.dao;

import hs.model.TbYearInfo;

import java.util.List;

public interface YearInfoDaoI extends BaseDaoI<TbYearInfo> {

    public List<TbYearInfo> findByExample(TbYearInfo t);

    public List<TbYearInfo> findByExample(TbYearInfo t, int page, int rows);

    public List<TbYearInfo> findByExample(TbYearInfo t, int page, int rows, String sortName, String sortOrder);

}
