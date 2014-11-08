package hs.dao;

import hs.model.TbStudentInfoHistory;

import java.util.List;

public interface StudentInfoHistoryDaoI extends BaseDaoI<TbStudentInfoHistory> {

    public List<TbStudentInfoHistory> findByExample(TbStudentInfoHistory t);

    public List<TbStudentInfoHistory> findByExample(TbStudentInfoHistory t, int page, int rows);

    public List<TbStudentInfoHistory> findByExample(TbStudentInfoHistory t, int page, int rows, String sortName, String sortOrder);

}
