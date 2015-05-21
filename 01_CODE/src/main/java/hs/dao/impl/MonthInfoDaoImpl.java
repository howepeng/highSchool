package hs.dao.impl;

import hs.dao.MonthInfoDaoI;
import hs.model.TbMonthInfo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("monthInfoDao")
public class MonthInfoDaoImpl extends BaseDaoImpl<TbMonthInfo> implements MonthInfoDaoI {

    private static String type = "hs.model.TbMonthInfo";

    @Override
    public List<TbMonthInfo> findByExample(TbMonthInfo t) {
        List<TbMonthInfo> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbMonthInfo> findByExample(TbMonthInfo t, int page, int rows) {
        List<TbMonthInfo> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbMonthInfo> findByExample(TbMonthInfo t, int page, int rows, String sortName, String sortOrder) {
        List<TbMonthInfo> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
