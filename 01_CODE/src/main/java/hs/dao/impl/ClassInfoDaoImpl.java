package hs.dao.impl;

import hs.dao.ClassInfoDaoI;
import hs.model.TbClassInfo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("classInfoDao")
public class ClassInfoDaoImpl extends BaseDaoImpl<TbClassInfo> implements ClassInfoDaoI {

    private static String type = "hs.model.TbClassInfo";

    @Override
    public List<TbClassInfo> findByExample(TbClassInfo t) {
        List<TbClassInfo> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbClassInfo> findByExample(TbClassInfo t, int page, int rows) {
        List<TbClassInfo> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbClassInfo> findByExample(TbClassInfo t, int page, int rows, String sortName, String sortOrder) {
        List<TbClassInfo> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
