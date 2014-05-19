package hs.dao.impl;

import hs.dao.StudentDaoI;
import hs.model.TbStudent;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("studentDao")
public class StudentDaoImpl extends BaseDaoImpl<TbStudent> implements StudentDaoI {

	private static String type = "hs.model.TbStudent";

	@Override
	public List<TbStudent> findByExample(TbStudent t) {
		List<TbStudent> results = super.findByE(t, type);
		return results;
	}

	@Override
	public List<TbStudent> findByExample(TbStudent t, int page, int rows) {
		List<TbStudent> results = super.findByE(t, type, page, rows);
		return results;
	}

	@Override
	public List<TbStudent> findByExample(TbStudent t, int page, int rows, String sortName, String sortOrder) {
		List<TbStudent> results = super.findByE(t, type, page, rows, sortName, sortOrder);
		return results;
	}
}
