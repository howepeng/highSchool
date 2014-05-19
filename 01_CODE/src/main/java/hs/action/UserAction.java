package hs.action;

import hs.pageModel.Json;
import hs.pageModel.SessionInfo;
import hs.pageModel.User;
import hs.service.UserServiceI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "userAction", results = { @Result(name = "main", location = "/jsp/comn/main.jsp") })
public class UserAction extends BaseAction implements ModelDriven<User> {

    User user = new User();

    @Override
    public User getModel() {
        return user;
    }

    private UserServiceI userService;

    @Autowired
    public void setUserService(UserServiceI userService) {
        this.userService = userService;
    }

    public String doNotNeedSession_main() {
        return "main";
    }

    public void doNotNeedSession_register() {
        Json json = new Json();
        try {
            userService.saveRegisterMessage(user);
            json.setSuccess(true);
            json.setMsg("注册成功");

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void doNotNeedSession_login() {
        user = userService.login(user);
        Json json = new Json();
        if (null != user) {
            SessionInfo sessionInfo = new SessionInfo();
            BeanUtils.copyProperties(user, sessionInfo);
            ServletActionContext.getRequest().getSession().setAttribute("sessionInfo", sessionInfo);
            json.setSuccess(true);
            json.setMsg("登陆成功");
        } else {
            json.setMsg("登陆失败  </br> 用户名或密码错误");
        }
        super.writeJson(json);
    }

    public void datagrid() {
        super.writeJson(userService.getUserList(user));
    }

    public void add() {
        Json json = new Json();
        try {
            json.setReturnObject(userService.saveRegisterMessage(user));
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
            userService.removeUser(user.getIds());
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
            userService.editUser(user);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void editPwd(){
        Json json = new Json();
        try {
            userService.editUserPwd(user);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void logOut() {
        Json json = new Json();
        SessionInfo sessionInfo = new SessionInfo();
        BeanUtils.copyProperties(user, sessionInfo);
        ServletActionContext.getRequest().getSession().removeAttribute("sessionInfo");
        json.setSuccess(true);
        super.writeJson(json);
    }
}
