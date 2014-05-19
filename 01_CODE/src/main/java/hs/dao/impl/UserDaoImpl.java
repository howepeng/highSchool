package hs.dao.impl;

import hs.dao.UserDaoI;
import hs.model.TbUser;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<TbUser> implements UserDaoI {

	private static String type = "hs.model.TbUser";

	@Override
	public List<TbUser> findByExample(TbUser t) {
		List<TbUser> results = super.findByE(t, type);
		return results;
	}

	@Override
	public List<TbUser> findByExample(TbUser t, int page, int rows) {
		List<TbUser> results = super.findByE(t, type, page, rows);
		return results;
	}

	@Override
	public List<TbUser> findByExample(TbUser t, int page, int rows, String sortName, String sortOrder) {
		List<TbUser> results = super.findByE(t, type, page, rows, sortName, sortOrder);
		return results;
	}
}
