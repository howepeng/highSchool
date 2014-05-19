package hs.action;

import hs.pageModel.Json;
import hs.pageModel.Role;
import hs.service.RoleServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "roleAction")
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	private Role role = new Role();

	@Override
	public Role getModel() {
		return role;
	}

	private RoleServiceI roleService;

	@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

	public void combox() {
		super.writeJson(roleService.combox());
	}
	
	public void datagrid(){
		super.writeJson(roleService.datagrid(role));
	}
	
	public void add() {
		Json json = new Json();
		try {
			roleService.add(role);
			json.setSuccess(true);
			json.setMsg("添加成功");

		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		super.writeJson(json);
	}
	public void remove(){
		Json json = new Json();
		try {
			roleService.remove(role.getIds());
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {	
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		super.writeJson(json);
		
	}
	public void edit(){
		Json json = new Json();
		try {
			roleService.edit(role);
			json.setSuccess(true);
			json.setMsg("修改成功");
		} catch (Exception e) {	
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		super.writeJson(json);
	}
}
