package hs.dao.impl;

import hs.dao.RoleDaoI;
import hs.model.TbRole;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<TbRole> implements RoleDaoI {

    private static String type = "hs.model.TbRole";

    @Override
    public List<TbRole> findByExample(TbRole t) {
        List<TbRole> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbRole> findByExample(TbRole t, int page, int rows) {
        List<TbRole> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbRole> findByExample(TbRole t, int page, int rows, String sortName, String sortOrder) {
        List<TbRole> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
