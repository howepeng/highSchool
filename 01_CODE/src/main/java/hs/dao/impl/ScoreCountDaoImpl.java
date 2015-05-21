package hs.dao.impl;

import hs.dao.ScoreCountDaoI;
import hs.model.TbScoreCount;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("scoreCountDao")
public class ScoreCountDaoImpl extends BaseDaoImpl<TbScoreCount> implements ScoreCountDaoI {

    private static String type = "hs.model.TbScoreCount";

    @Override
    public List<TbScoreCount> findByExample(TbScoreCount t) {
        List<TbScoreCount> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbScoreCount> findByExample(TbScoreCount t, int page, int rows) {
        List<TbScoreCount> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbScoreCount> findByExample(TbScoreCount t, int page, int rows, String sortName, String sortOrder) {
        List<TbScoreCount> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
