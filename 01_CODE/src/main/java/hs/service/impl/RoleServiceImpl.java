package hs.service.impl;

import hs.dao.MenuDaoI;
import hs.dao.RoleDaoI;
import hs.dao.RoleMenuDaoI;
import hs.model.TbMenu;
import hs.model.TbRole;
import hs.model.TbRoleMenu;
import hs.pageModel.DataGrid;
import hs.pageModel.Role;
import hs.service.RoleServiceI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleServiceI {

	private RoleDaoI roleDao;
	private MenuDaoI menuDao;
	private RoleMenuDaoI roleMenuDao;

	@Autowired
	public void setRoleDao(RoleDaoI roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setMenuDao(MenuDaoI menuDao) {
		this.menuDao = menuDao;
	}
	
	@Autowired
	public void setRoleMenuDao(RoleMenuDaoI roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
	}

	@Override
	public List<Role> combox() {
		List<Role> rl = new ArrayList<Role>();
		List<TbRole> l = roleDao.find("from TbRole");
		if (l != null && l.size() > 0) {
			for (TbRole t : l) {
				Role r = new Role();
				r.setId(t.getId());
				r.setName(t.getName());
				rl.add(r);
			}
		}
		return rl;
	}

	@Override
	public DataGrid datagrid(Role role) {
		DataGrid j = new DataGrid();
		j.setRows(changeModel(find(role)));
		j.setTotal(total(role));
		return j;
	}

	private List<Role> changeModel(List<TbRole> tbRoles) {
		List<Role> roles = new ArrayList<Role>();
		if (tbRoles != null && tbRoles.size() > 0) {
			for (TbRole tu : tbRoles) {
				Role u = new Role();
				BeanUtils.copyProperties(tu, u);

				Set<TbRoleMenu> troletauths = tu.getTbRoleMenus();
				String menuIds = "";
				String menuNames = "";
				boolean b = false;
				if (troletauths != null && troletauths.size() > 0) {
					for (TbRoleMenu TbRoleMenu : troletauths) {
						if (TbRoleMenu.getTbMenu() != null) {
							if (b) {
								menuIds += ",";
								menuNames += ",";
							}
							menuIds += TbRoleMenu.getTbMenu().getId();
							menuNames += TbRoleMenu.getTbMenu().getText();
							b = true;
						}
					}
				}
				u.setMenuIds(menuIds);
				u.setMenuNames(menuNames);
				roles.add(u);
			}
		}
		return roles;
	}

	private List<TbRole> find(Role role) {
		String hql = "FROM TbRole t where 1=1 ";

		if (role.getSort() != null && role.getOrder() != null) {
			hql += " ORDER BY " + role.getSort() + " " + role.getOrder();
		}
		return roleDao.find(hql, role.getPage(), role.getRows());
	}

	private Long total(Role role) {
		String hql = "SELECT count(*) FROM TbRole t WHERE 1=1 ";
		return roleDao.count(hql);
	}
	
	@Override
	public void add(Role role) {
		TbRole r = new TbRole();
		BeanUtils.copyProperties(role, r);
		r.setId(UUID.randomUUID().toString());
		roleDao.save(r);

		this.saveRoleAuth(role, r);
	}

	/**
	 * 保存Trole和Tauth的关系
	 * 
	 * @param role
	 * @param r
	 */
	private void saveRoleAuth(Role role, TbRole r) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("tbRole", r);
		roleMenuDao.excuteHQL("delete TbRoleMenu t where t.tbRole = :tbRole", m);
		if (role.getMenuIds() != null) {
			for (String id : role.getMenuIds().split(",")) {
				TbRoleMenu TbRoleMenu = new TbRoleMenu();
				TbRoleMenu.setId(UUID.randomUUID().toString());
				TbRoleMenu.setTbMenu(menuDao.getById(TbMenu.class, id.trim()));
				TbRoleMenu.setTbRole(r);
				roleMenuDao.save(TbRoleMenu);
			}
		}
	}
	
	@Override
	public void edit(Role role) {
		TbRole r = roleDao.getById(TbRole.class, role.getId());
		BeanUtils.copyProperties(role, r, new String[] { "id" });

		this.saveRoleAuth(role, r);
	}
	
	@Override
	public void remove(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				TbRole r = roleDao.getById(TbRole.class, id.trim());
				if (r != null) {
					roleDao.delete(r);
				}
			}
		}
	}
}
