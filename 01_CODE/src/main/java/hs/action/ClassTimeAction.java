package hs.action;

import hs.pageModel.ClassTime;
import hs.pageModel.Json;
import hs.service.ClassTimeServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "classTimeAction", results = { @Result(name = "classTime", location = "/jsp/comn/classTimeAction.jsp") })
public class ClassTimeAction extends BaseAction implements ModelDriven<ClassTime> {

    ClassTime classTime = new ClassTime();

    @Override
    public ClassTime getModel() {
        return classTime;
    }

    private ClassTimeServiceI classTimeService;

    @Autowired
    public void setClassTimeService(ClassTimeServiceI classTimeService) {
        this.classTimeService = classTimeService;
    }

    public void datagrid(){
        super.writeJson(classTimeService.datagrid(classTime));
    }

    public void add() {
        Json json = new Json();
        try {
            classTimeService.add(classTime);
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
            classTimeService.remove(classTime.getIds());
            json.setSuccess(true);
            json.setMsg("删除成功");
        } catch (DataIntegrityViolationException d){
            d.printStackTrace();
            json.setMsg("有使用该课程的日志存在，请先删除使用该课程的日志！");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void edit(){
        Json json = new Json();
        try {
            classTimeService.edit(classTime);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void getClassTime(){
        super.writeJson(classTimeService.getClassTime(classTime.getId()));
    }
}
