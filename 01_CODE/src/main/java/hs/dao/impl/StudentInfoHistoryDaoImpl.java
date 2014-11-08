package hs.dao.impl;

import hs.dao.StudentInfoHistoryDaoI;
import hs.model.TbStudentInfoHistory;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("studentInfoHistoryDao")
public class StudentInfoHistoryDaoImpl extends BaseDaoImpl<TbStudentInfoHistory> implements StudentInfoHistoryDaoI {

    private static String type = "hs.model.TbStudent";

    @Override
    public List<TbStudentInfoHistory> findByExample(TbStudentInfoHistory t) {
        List<TbStudentInfoHistory> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbStudentInfoHistory> findByExample(TbStudentInfoHistory t, int page, int rows) {
        List<TbStudentInfoHistory> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbStudentInfoHistory> findByExample(TbStudentInfoHistory t, int page, int rows, String sortName, String sortOrder) {
        List<TbStudentInfoHistory> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
