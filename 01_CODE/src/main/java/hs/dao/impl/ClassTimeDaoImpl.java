package hs.dao.impl;

import hs.dao.ClassTimeDaoI;
import hs.model.TbClassTime;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("classTimeDao")
public class ClassTimeDaoImpl extends BaseDaoImpl<TbClassTime> implements ClassTimeDaoI {

    private static String type = "hs.model.TbClassTime";

    @Override
    public List<TbClassTime> findByExample(TbClassTime t) {
        List<TbClassTime> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbClassTime> findByExample(TbClassTime t, int page, int rows) {
        List<TbClassTime> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbClassTime> findByExample(TbClassTime t, int page, int rows, String sortName, String sortOrder) {
        List<TbClassTime> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
