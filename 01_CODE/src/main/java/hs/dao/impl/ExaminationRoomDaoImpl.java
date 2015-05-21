package hs.dao.impl;

import hs.dao.ExaminationRoomDaoI;
import hs.model.TbExaminationRoom;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("examinationRoomDao")
public class ExaminationRoomDaoImpl extends BaseDaoImpl<TbExaminationRoom> implements ExaminationRoomDaoI {

    private static String type = "hs.model.TbExaminationRoom";

    @Override
    public List<TbExaminationRoom> findByExample(TbExaminationRoom t) {
        List<TbExaminationRoom> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbExaminationRoom> findByExample(TbExaminationRoom t, int page, int rows) {
        List<TbExaminationRoom> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbExaminationRoom> findByExample(TbExaminationRoom t, int page, int rows, String sortName, String sortOrder) {
        List<TbExaminationRoom> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
