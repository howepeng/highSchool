package hs.dao.impl;

import hs.dao.LogTypeDaoI;
import hs.model.TbLogType;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("logTypeDao")
public class LogTypeDaoImpl extends BaseDaoImpl<TbLogType> implements LogTypeDaoI {

    private static String type = "hs.model.TbLogType";

    @Override
    public List<TbLogType> findByExample(TbLogType t) {
        List<TbLogType> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbLogType> findByExample(TbLogType t, int page, int rows) {
        List<TbLogType> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbLogType> findByExample(TbLogType t, int page, int rows, String sortName, String sortOrder) {
        List<TbLogType> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
