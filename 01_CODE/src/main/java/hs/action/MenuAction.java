package hs.action;

import hs.pageModel.Json;
import hs.pageModel.Menu;
import hs.pageModel.SessionInfo;
import hs.service.MenuServiceI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "menuAction")
public class MenuAction extends BaseAction implements ModelDriven<Menu> {
	private Menu menu = new Menu();

	private MenuServiceI menuService; 
	
	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}
	@Override
	public Menu getModel() {
		return menu;
	}
	public void getTreeNode(){
		super.writeJson(menuService.getTreeNode(menu));
	}
	public void getAllTreeNode(){
		SessionInfo sessionInfo = (SessionInfo)ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		super.writeJson(menuService.getAllTreeNode(sessionInfo));
	}

	/**
	 * 获得菜单treegrid
	 */
	public void treegrid() {
		super.writeJson(menuService.treegrid(menu));
	}
	
	public void add() {
		Json json = new Json();
		try {
			menuService.add(menu);
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
			menuService.remove(menu.getId());
			json.setSuccess(true);
			json.setMsg("删除成功");
			json.setReturnObject(menu.getId());
		} catch (Exception e) {	
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		super.writeJson(json);
		
	}
	public void edit(){
		Json json = new Json();
		try {
			menuService.edit(menu);
			json.setSuccess(true);
			json.setMsg("修改成功");
		} catch (Exception e) {	
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		super.writeJson(json);
	}
}
