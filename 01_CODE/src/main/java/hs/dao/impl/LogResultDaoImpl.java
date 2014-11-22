package hs.dao.impl;

import hs.dao.LogResultDaoI;
import hs.model.TbLogResult;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("logResultDao")
public class LogResultDaoImpl extends BaseDaoImpl<TbLogResult> implements LogResultDaoI {

    private static String type = "hs.model.TbLogResult";

    @Override
    public List<TbLogResult> findByExample(TbLogResult t) {
        List<TbLogResult> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbLogResult> findByExample(TbLogResult t, int page, int rows) {
        List<TbLogResult> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbLogResult> findByExample(TbLogResult t, int page, int rows, String sortName, String sortOrder) {
        List<TbLogResult> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
