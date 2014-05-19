package hs.dao.impl;

import hs.dao.PreferentialDaoI;
import hs.model.TbPreferential;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("preferentailDao")
public class PreferentialDaoImpl extends BaseDaoImpl<TbPreferential> implements PreferentialDaoI {

	private static String type = "hs.model.TbPreferential";

	@Override
	public List<TbPreferential> findByExample(TbPreferential t) {
		List<TbPreferential> results = super.findByE(t, type);
		return results;
	}

	@Override
	public List<TbPreferential> findByExample(TbPreferential t, int page, int rows) {
		List<TbPreferential> results = super.findByE(t, type, page, rows);
		return results;
	}

	@Override
	public List<TbPreferential> findByExample(TbPreferential t, int page, int rows, String sortName, String sortOrder) {
		List<TbPreferential> results = super.findByE(t, type, page, rows, sortName, sortOrder);
		return results;
	}
}
