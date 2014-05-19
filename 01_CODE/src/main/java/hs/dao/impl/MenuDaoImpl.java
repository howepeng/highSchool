package hs.dao.impl;

import hs.dao.MenuDaoI;
import hs.model.TbMenu;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<TbMenu> implements MenuDaoI {

	@Override
	public List<TbMenu> findByExample(TbMenu t) {
		List<TbMenu> results = super.findByE(t, "hs.model.TbMenu");
		return results;
	}
}
