package hs.dao;

import hs.model.TbStudent;

import java.util.List;

public interface StudentDaoI extends BaseDaoI<TbStudent> {

	public List<TbStudent> findByExample(TbStudent t);

	public List<TbStudent> findByExample(TbStudent t, int page, int rows);

	public List<TbStudent> findByExample(TbStudent t, int page, int rows, String sortName, String sortOrder);

}
