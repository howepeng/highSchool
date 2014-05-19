package hs.dao;

import hs.model.TbClassType;

import java.util.List;

public interface ClassTypeDaoI extends BaseDaoI<TbClassType> {

	public List<TbClassType> findByExample(TbClassType t);

	public List<TbClassType> findByExample(TbClassType t, int page, int rows);

	public List<TbClassType> findByExample(TbClassType t, int page, int rows, String sortName, String sortOrder);

}
