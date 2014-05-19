package hs.service;

import hs.pageModel.Menu;
import hs.pageModel.SessionInfo;
import hs.pageModel.TreeNode;

import java.util.List;

public interface MenuServiceI {

	public List<TreeNode> getTreeNode(Menu menu);

	public List<Menu> getAllTreeNode(SessionInfo sessionInfo);

	public List<Menu> treegrid(Menu menu);

	public void add(Menu menu);

	public void remove(String ids);

	public void edit(Menu menu);

}
