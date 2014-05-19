package hs.dao;

import hs.model.TbRole;

import java.util.List;

public interface RoleDaoI extends BaseDaoI<TbRole> {

	public List<TbRole> findByExample(TbRole t);

	public List<TbRole> findByExample(TbRole t, int page, int rows);

	public List<TbRole> findByExample(TbRole t, int page, int rows, String sortName, String sortOrder);

}
