package hs.dao.impl;

import hs.dao.MonthScoreDaoI;
import hs.model.TbMonthScore;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("monthScoreDao")
public class MonthScoreDaoImpl extends BaseDaoImpl<TbMonthScore> implements MonthScoreDaoI {

    private static String type = "hs.model.TbMonthScore";

    @Override
    public List<TbMonthScore> findByExample(TbMonthScore t) {
        List<TbMonthScore> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbMonthScore> findByExample(TbMonthScore t, int page, int rows) {
        List<TbMonthScore> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbMonthScore> findByExample(TbMonthScore t, int page, int rows, String sortName, String sortOrder) {
        List<TbMonthScore> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
