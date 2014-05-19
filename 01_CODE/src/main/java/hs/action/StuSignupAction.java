package hs.action;

import hs.pageModel.Json;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;
import hs.service.StuSignupServiceI;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "stuSignupAction")
public class StuSignupAction extends BaseAction implements ModelDriven<Student> {

    Student student = new Student();

    @Override
    public Student getModel() {
        return student;
    }

    private StuSignupServiceI stuSignupService;

    @Autowired
    public void setStuSignupService(StuSignupServiceI stuSignupService) {
        this.stuSignupService = stuSignupService;
    }

    public void signup() {
        Json json = new Json();
        try {
            String text = "报名成功";
            if (student.getId() != null && !"".equals(student.getId())) {
                text = "修改成功";
            }
            SessionInfo sessionInfo = (SessionInfo) ServletActionContext
                    .getRequest().getSession().getAttribute("sessionInfo");
            String id = stuSignupService.signup(student, sessionInfo);
            student.setId(id);
            stuSignupService.upload(student);
            json.setReturnObject(id);
            json.setSuccess(true);
            json.setMsg(text);
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void getNum() throws Exception {
        Student stu = new Student();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String strNow = dateFormat.format(now);
        String num = strNow.substring(2,4) + "120";
        stu.setNum(num);
        super.writeJson(stu);
    }

    public void uploadPhoto() {
        Json json = new Json();
        String text = "上传失败";
        try {
            String path = ServletActionContext.getServletContext().getRealPath("/");
            String ret[] = stuSignupService.uploadPhoto(student, path);
            json.setReturnObject(ret);
            json.setSuccess(true);
            text = "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        } finally {
            json.setMsg(text);
        }
        super.writeJson(json);
    }
    public String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
              sb = new StringBuffer();
              sb.append("0").append(str);// 左(前)补0
           // sb.append(str).append("0");//右(后)补0
              str = sb.toString();
              strLen = str.length();
        }
        return str;
    }
}
