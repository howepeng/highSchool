package hs.dao.impl;

import hs.dao.LogManagerDaoI;
import hs.model.TbLogInfo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("logManagerDao")
public class LogManagerDaoImpl extends BaseDaoImpl<TbLogInfo> implements LogManagerDaoI {

    private static String type = "hs.model.TbLogInfo";

    @Override
    public List<TbLogInfo> findByExample(TbLogInfo t) {
        List<TbLogInfo> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbLogInfo> findByExample(TbLogInfo t, int page, int rows) {
        List<TbLogInfo> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbLogInfo> findByExample(TbLogInfo t, int page, int rows, String sortName, String sortOrder) {
        List<TbLogInfo> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
