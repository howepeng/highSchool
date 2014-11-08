package hs.action;

import hs.dao.UserDaoI;
import hs.model.TbUser;
import hs.pageModel.Json;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;
import hs.service.StudentServiceI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "studentAction")
public class StudentAction extends BaseAction implements ModelDriven<Student> {

    private Student student = new Student();

    @Override
    public Student getModel() {
        return student;
    }

    private UserDaoI userDao;

    @Autowired
    public void setUserDao(UserDaoI userDao) {
        this.userDao = userDao;
    }
    private StudentServiceI studentService;

    @Autowired
    public void setStudentService(StudentServiceI studentService) {
        this.studentService = studentService;
    }

    /**
     * 缴费
     */
    public void datagridBeforePay(){
        super.writeJson(studentService.datagridBeforePay(student));
    }

    /**
     * 补交费
     */
    public void datagridAfterPay(){
        super.writeJson(studentService.datagridAfterPay(student));
    }

    /**
     * 退款
     */
    public void datagridReturnPay(){
        super.writeJson(studentService.datagridReturnPay(student));
    }

    /**
     * 学生管理
     */
    public void datagridStudent(){
        super.writeJson(studentService.datagridStudent(student));
    }

    public void feePay(){
        Json json = new Json();
        try {
            SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
            studentService.feePay(student,sessionInfo);
            json.setSuccess(true);
            json.setMsg("缴费成功");

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void arrearsPay(){
        Json json = new Json();
        try {
            SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
            studentService.arrearsPay(student,sessionInfo);
            json.setSuccess(true);
            json.setMsg("缴费成功");

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void refunPay(){
        Json json = new Json();
        try {
            SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
            studentService.refunPay(student,sessionInfo);
            json.setSuccess(true);
            json.setMsg("退款成功");

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void remove(){
        Json json = new Json();
        SessionInfo sessionInfo = (SessionInfo) ServletActionContext
                .getRequest().getSession().getAttribute("sessionInfo");
        TbUser tbUser = userDao.getById(TbUser.class, sessionInfo.getId());
        try {
            String ret = "";
            if (student.getIds() != null && !"".equals(student.getIds())) {
                String[] ids = student.getIds().split(",");
                for(String id : ids){
                    ret = studentService.deleteStudent(id,tbUser);
                    if (!"success".equals(ret)) {
                        break;
                    }
                }
            }
            if ("success".equals(ret)) {
                json.setSuccess(true);
                json.setMsg("删除成功");
            } else {
                json.setSuccess(false);
                json.setMsg("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);

    }
}
