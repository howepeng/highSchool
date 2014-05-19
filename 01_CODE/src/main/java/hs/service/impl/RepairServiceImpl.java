package hs.service.impl;

import hs.dao.MenuDaoI;
import hs.dao.UserDaoI;
import hs.model.TbMenu;
import hs.model.TbUser;
import hs.service.RepairServiceI;
import hs.util.MD5Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("repairService")
public class RepairServiceImpl implements RepairServiceI {

	private MenuDaoI menuDao;

	@Autowired
	public void setMenuDao(MenuDaoI menuDao) {
		this.menuDao = menuDao;
	}
	
	private UserDaoI userDao;

	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	public void repair() {
		
		repairMenu();
		repairUser();
	}


	private void repairMenu() {
		TbMenu root = new TbMenu();
		root.setId("0");
		root.setText("首页");
		menuDao.saveOrUpdate(root);
		
		TbMenu xtgl = new TbMenu();
		xtgl.setId("xtgl");
		xtgl.setText("系统管理");
		xtgl.setTbMenu(root);
		menuDao.saveOrUpdate(xtgl);
		
		TbMenu yhgl = new TbMenu();
		yhgl.setId("yhgl");
		yhgl.setText("用户管理");
		yhgl.setTbMenu(xtgl);
		yhgl.setUrl("jsp/comn/userManager.jsp");
		menuDao.saveOrUpdate(yhgl);
		
		TbMenu jsgl = new TbMenu();
		jsgl.setId("jsgl");
		jsgl.setText("角色管理");
		jsgl.setTbMenu(xtgl);
		menuDao.saveOrUpdate(jsgl);
		
		TbMenu qxgl = new TbMenu();
		qxgl.setId("qxgl");
		qxgl.setText("权限管理");
		qxgl.setTbMenu(xtgl);
		menuDao.saveOrUpdate(qxgl);
		
		TbMenu cdgl = new TbMenu();
		cdgl.setId("cdgl");
		cdgl.setText("菜单管理");
		cdgl.setTbMenu(xtgl);
		menuDao.saveOrUpdate(cdgl);
		
		TbMenu buggl = new TbMenu();
		buggl.setId("buggl");
		buggl.setText("bug管理");
		buggl.setTbMenu(xtgl);
		menuDao.saveOrUpdate(buggl);
	}
	
	private void repairUser(){
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("name", "admin");
		TbUser t = userDao.get("FROM Tuser t WHERE t.name= :name and t.id!='0'", m);
		if(t!=null){
			t.setUsername(UUID.randomUUID().toString());
		}
//		userDao.update(t);
		TbUser admin = new TbUser();
		admin.setUsername("admin");
		admin.setId("0");
		admin.setPassword(MD5Util.md5("admin"));
		admin.setModifydatetime(new Date());
		
		userDao.saveOrUpdate(admin);
	}
}
