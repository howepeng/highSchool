package hs.dao;

import hs.model.TbGaokaoScore;

import java.util.List;

public interface GaokaoScoreDaoI extends BaseDaoI<TbGaokaoScore> {

    public List<TbGaokaoScore> findByExample(TbGaokaoScore t);

    public List<TbGaokaoScore> findByExample(TbGaokaoScore t, int page, int rows);

    public List<TbGaokaoScore> findByExample(TbGaokaoScore t, int page, int rows, String sortName, String sortOrder);

}
