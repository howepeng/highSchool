package hs.dao;

import hs.model.TbScoreCount;

import java.util.List;

public interface ScoreCountDaoI extends BaseDaoI<TbScoreCount> {

    public List<TbScoreCount> findByExample(TbScoreCount t);

    public List<TbScoreCount> findByExample(TbScoreCount t, int page, int rows);

    public List<TbScoreCount> findByExample(TbScoreCount t, int page, int rows, String sortName, String sortOrder);

}
