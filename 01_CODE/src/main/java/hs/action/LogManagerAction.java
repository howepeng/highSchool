package hs.action;

import hs.pageModel.CalendarEvent;
import hs.pageModel.ClassTime;
import hs.pageModel.Json;
import hs.pageModel.LogInfo;
import hs.pageModel.ShowCalendarInfo;
import hs.service.ClassTimeServiceI;
import hs.service.LogManagerServiceI;
import hs.util.ConvertUtil;
import hs.util.UVOUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "logManagerAction", results = { @Result(name = "logManager", location = "/jsp/director/logManage.jsp"),
                                                @Result(name = "logManagerAdd", location = "/jsp/director/logManagerAdd.jsp")})
public class LogManagerAction extends BaseAction implements ModelDriven<LogInfo> {

    LogInfo logInfo = new LogInfo();

    @Override
    public LogInfo getModel() {
        return logInfo;
    }

    private LogManagerServiceI logManagerService;

    @Autowired
    public void setLogManagerService(LogManagerServiceI logManagerService) {
        this.logManagerService = logManagerService;
    }

    private ClassTimeServiceI classTimeService;

    @Autowired
    public void setClassTimeService(ClassTimeServiceI classTimeService) {
        this.classTimeService = classTimeService;
    }

    public void showCalendar(){
        List<CalendarEvent> output = new ArrayList<CalendarEvent>();
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatDate = new SimpleDateFormat("YYYY-MM-dd");

        try {
            List<ShowCalendarInfo> ret = logManagerService.getShowCalendar(logInfo);
            String msg = "{0} ({1})";
            if (ret != null && 0 < ret.size()) {
                int count = ret.size();
                for (int i = 0; i < count; i++) {
                    ShowCalendarInfo item = ret.get(i);
                    CalendarEvent newItem = new CalendarEvent();
                    String date = formatDate.format(item.getDate());
                    newItem.setStart(date + " " + formatTime.format(item.getStartTime()));
                    newItem.setEnd(date + " " + formatTime.format(item.getEndTime()));
                    newItem.setTitle(ConvertUtil.messageFormat(msg, item.getLogTypeName(), item.getCount() + ""));
                    newItem.setId(item.getClassTimeId() + "," + item.getLogTypeId() + "," + item.getClassId());
                    output.add(newItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.writeJson(output);
    }

    public void showAdd(){
        Json json = new Json();
        try {
            json.setSuccess(true);
            json.setMsg("显示成功");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat formatDate = new SimpleDateFormat("YYYY-MM-dd");
            logInfo.setShowDate(formatDate.format(logInfo.getDate()));
            logInfo.setShowStartTime(formatTime.format(logInfo.getStartTime()));
            logInfo.setShowEndTime(formatTime.format(logInfo.getEndTime()));
            json.setReturnObject(logInfo);
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void changeClassTime(){
        Json json = new Json();
        try {
            ClassTime ret = classTimeService.getClassTime(logInfo.getClassTimeId());
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
            String startTime = formatTime.format(ret.getStartTime());
            String endTime = formatTime.format(ret.getEndTime());
            ret.setShowStartTime(startTime);
            ret.setShowEndTime(endTime);
            json.setReturnObject(ret);
            json.setSuccess(true);
            json.setMsg("显示成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void datagrid(){
        if (UVOUtil.isDirector()) {
            super.writeJson(logManagerService.datagrid(logInfo));
        }
    }

    public String gotoLogManager() {
        String[] ids = logInfo.getIds().split(",");
        logInfo.setClassTimeId(ids[0]);
        logInfo.setTypeId(ids[1]);
        logInfo.setClassId(ids[2]);
        return "logManager";
    }

    public void add() {
        Json json = new Json();
        try {
            logManagerService.add(logInfo);
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
            logManagerService.remove(logInfo.getIds());
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
            logManagerService.edit(logInfo);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void getClassTime(){
        super.writeJson(logManagerService.getLogInfo(logInfo.getId()));
    }
}
