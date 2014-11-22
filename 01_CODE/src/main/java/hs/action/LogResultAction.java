package hs.action;

import hs.pageModel.Json;
import hs.pageModel.LogResult;
import hs.service.LogResultServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "logResultAction", results = { @Result(name = "logResult", location = "/jsp/comn/logResult.jsp") })
public class LogResultAction extends BaseAction implements ModelDriven<LogResult> {

    LogResult logResult = new LogResult();

    @Override
    public LogResult getModel() {
        return logResult;
    }

    private LogResultServiceI logResultService;

    @Autowired
    public void setLogResultService(LogResultServiceI logResultService) {
        this.logResultService = logResultService;
    }

    public void datagrid(){
        super.writeJson(logResultService.datagrid(logResult));
    }

    public void add() {
        Json json = new Json();
        try {
            logResultService.add(logResult);
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
            logResultService.remove(logResult.getIds());
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
            logResultService.edit(logResult);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void getClassTime(){
        super.writeJson(logResultService.getLogResult(logResult.getId()));
    }
}
