package hs.dao;

import hs.model.TbUser;

import java.util.List;

public interface UserDaoI extends BaseDaoI<TbUser> {

	public List<TbUser> findByExample(TbUser t);

	public List<TbUser> findByExample(TbUser t, int page, int rows);

	public List<TbUser> findByExample(TbUser t, int page, int rows, String sortName, String sortOrder);

}
