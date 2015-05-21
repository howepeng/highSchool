package hs.dao;

import hs.model.TbDormitoryInfo;

import java.util.List;

public interface DormitoryInfoDaoI extends BaseDaoI<TbDormitoryInfo> {

    public List<TbDormitoryInfo> findByExample(TbDormitoryInfo t);

    public List<TbDormitoryInfo> findByExample(TbDormitoryInfo t, int page, int rows);

    public List<TbDormitoryInfo> findByExample(TbDormitoryInfo t, int page, int rows, String sortName, String sortOrder);

}
