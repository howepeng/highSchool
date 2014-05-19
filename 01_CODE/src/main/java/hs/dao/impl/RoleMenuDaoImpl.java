package hs.dao.impl;

import hs.dao.RoleMenuDaoI;
import hs.model.TbRoleMenu;

import org.springframework.stereotype.Repository;

@Repository("roleMenuDao")
public class RoleMenuDaoImpl extends BaseDaoImpl<TbRoleMenu> implements RoleMenuDaoI {

}
