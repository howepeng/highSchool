package hs.action;

import hs.pageModel.Json;
import hs.pageModel.LogType;
import hs.service.LogTypeServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "logTypeAction", results = { @Result(name = "logType", location = "/jsp/comn/logType.jsp") })
public class LogTypeAction extends BaseAction implements ModelDriven<LogType> {

    LogType logType = new LogType();

    @Override
    public LogType getModel() {
        return logType;
    }

    private LogTypeServiceI logTypeService;

    @Autowired
    public void setLogTypeService(LogTypeServiceI logTypeService) {
        this.logTypeService = logTypeService;
    }

    public void datagrid(){
        super.writeJson(logTypeService.datagrid(logType));
    }

    public void add() {
        Json json = new Json();
        try {
            logTypeService.add(logType);
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
            logTypeService.remove(logType.getIds());
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
            logTypeService.edit(logType);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void getClassTime(){
        super.writeJson(logTypeService.getLogType(logType.getId()));
    }
}
