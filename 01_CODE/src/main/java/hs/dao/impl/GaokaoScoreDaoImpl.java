package hs.dao.impl;

import hs.dao.GaokaoScoreDaoI;
import hs.model.TbGaokaoScore;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("gaokaoScoreDao")
public class GaokaoScoreDaoImpl extends BaseDaoImpl<TbGaokaoScore> implements GaokaoScoreDaoI {

    private static String type = "hs.model.TbGaokaoScore";

    @Override
    public List<TbGaokaoScore> findByExample(TbGaokaoScore t) {
        List<TbGaokaoScore> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbGaokaoScore> findByExample(TbGaokaoScore t, int page, int rows) {
        List<TbGaokaoScore> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbGaokaoScore> findByExample(TbGaokaoScore t, int page, int rows, String sortName, String sortOrder) {
        List<TbGaokaoScore> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
