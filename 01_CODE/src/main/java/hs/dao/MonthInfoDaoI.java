package hs.dao;

import hs.model.TbMonthInfo;

import java.util.List;

public interface MonthInfoDaoI extends BaseDaoI<TbMonthInfo> {

    public List<TbMonthInfo> findByExample(TbMonthInfo t);

    public List<TbMonthInfo> findByExample(TbMonthInfo t, int page, int rows);

    public List<TbMonthInfo> findByExample(TbMonthInfo t, int page, int rows, String sortName, String sortOrder);

}
