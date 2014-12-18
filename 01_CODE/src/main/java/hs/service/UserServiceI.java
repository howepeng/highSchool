package hs.service;

import java.util.List;

import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.User;

public interface UserServiceI {

    public List<Combobox> combox();

    public User saveRegisterMessage(User user);

    public User login(User user);

    public DataGrid getUserList(User user);

    public void removeUser(String ids);

    public void editUser(User user);

    public void editUserPwd(User user);

}
