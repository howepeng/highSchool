package hs.dao.impl;

import hs.dao.ClassTypeDaoI;
import hs.model.TbClassType;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("classTypeDao")
public class RoleDaoImpl extends BaseDaoImpl<TbClassType> implements ClassTypeDaoI {

	private static String type = "hs.model.TbClassType";

	@Override
	public List<TbClassType> findByExample(TbClassType t) {
		List<TbClassType> results = super.findByE(t, type);
		return results;
	}

	@Override
	public List<TbClassType> findByExample(TbClassType t, int page, int rows) {
		List<TbClassType> results = super.findByE(t, type, page, rows);
		return results;
	}

	@Override
	public List<TbClassType> findByExample(TbClassType t, int page, int rows, String sortName, String sortOrder) {
		List<TbClassType> results = super.findByE(t, type, page, rows, sortName, sortOrder);
		return results;
	}
}
