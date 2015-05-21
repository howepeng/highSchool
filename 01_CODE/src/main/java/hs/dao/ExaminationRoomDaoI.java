package hs.dao;

import hs.model.TbExaminationRoom;

import java.util.List;

public interface ExaminationRoomDaoI extends BaseDaoI<TbExaminationRoom> {

    public List<TbExaminationRoom> findByExample(TbExaminationRoom t);

    public List<TbExaminationRoom> findByExample(TbExaminationRoom t, int page, int rows);

    public List<TbExaminationRoom> findByExample(TbExaminationRoom t, int page, int rows, String sortName, String sortOrder);

}
