package hs.service;

import hs.pageModel.DataGrid;
import hs.pageModel.User;

public interface UserServiceI {

	public User saveRegisterMessage(User user);

	public User login(User user);
	
	public DataGrid getUserList(User user);

	public void removeUser(String ids);

	public void editUser(User user);
	
	public void editUserPwd(User user);
		
}
