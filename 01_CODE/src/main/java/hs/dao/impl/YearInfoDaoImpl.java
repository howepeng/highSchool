package hs.dao.impl;

import hs.dao.YearInfoDaoI;
import hs.model.TbYearInfo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("yearInfoDao")
public class YearInfoDaoImpl extends BaseDaoImpl<TbYearInfo> implements YearInfoDaoI {

    private static String type = "hs.model.TbYearInfo";

    @Override
    public List<TbYearInfo> findByExample(TbYearInfo t) {
        List<TbYearInfo> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbYearInfo> findByExample(TbYearInfo t, int page, int rows) {
        List<TbYearInfo> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbYearInfo> findByExample(TbYearInfo t, int page, int rows, String sortName, String sortOrder) {
        List<TbYearInfo> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
