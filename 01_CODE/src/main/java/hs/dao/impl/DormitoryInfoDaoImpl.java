package hs.dao.impl;

import hs.dao.DormitoryInfoDaoI;
import hs.model.TbDormitoryInfo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("dormitoryInfoDao")
public class DormitoryInfoDaoImpl extends BaseDaoImpl<TbDormitoryInfo> implements DormitoryInfoDaoI {

    private static String type = "hs.model.TbDormitoryInfo";

    @Override
    public List<TbDormitoryInfo> findByExample(TbDormitoryInfo t) {
        List<TbDormitoryInfo> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbDormitoryInfo> findByExample(TbDormitoryInfo t, int page, int rows) {
        List<TbDormitoryInfo> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbDormitoryInfo> findByExample(TbDormitoryInfo t, int page, int rows, String sortName, String sortOrder) {
        List<TbDormitoryInfo> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
