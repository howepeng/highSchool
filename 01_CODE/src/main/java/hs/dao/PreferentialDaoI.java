package hs.dao;

import hs.model.TbPreferential;

import java.util.List;

public interface PreferentialDaoI extends BaseDaoI<TbPreferential> {

	public List<TbPreferential> findByExample(TbPreferential t);

	public List<TbPreferential> findByExample(TbPreferential t, int page, int rows);

	public List<TbPreferential> findByExample(TbPreferential t, int page, int rows, String sortName, String sortOrder);

}
