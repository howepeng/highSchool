package hs.dao;

import hs.model.TbMenu;

import java.util.List;

public interface MenuDaoI extends BaseDaoI<TbMenu> {

	public List<TbMenu> findByExample(TbMenu t);
}
