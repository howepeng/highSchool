package hs.dao;

import hs.model.TbMonthScore;

import java.util.List;

public interface MonthScoreDaoI extends BaseDaoI<TbMonthScore> {

    public List<TbMonthScore> findByExample(TbMonthScore t);

    public List<TbMonthScore> findByExample(TbMonthScore t, int page, int rows);

    public List<TbMonthScore> findByExample(TbMonthScore t, int page, int rows, String sortName, String sortOrder);

}
