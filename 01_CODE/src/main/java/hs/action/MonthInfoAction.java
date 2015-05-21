package hs.action;

import hs.pageModel.Json;
import hs.pageModel.MonthInfo;
import hs.service.MonthInfoServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "monthInfoAction", results = { @Result(name = "monthInfo", location = "/jsp/comn/MonthInfoAction.jsp") })
public class MonthInfoAction extends BaseAction implements ModelDriven<MonthInfo> {

    MonthInfo monthInfo = new MonthInfo();

    @Override
    public MonthInfo getModel() {
        return monthInfo;
    }

    private MonthInfoServiceI monthInfoService;

    @Autowired
    public void setMonthInfoService(MonthInfoServiceI monthInfoService) {
        this.monthInfoService = monthInfoService;
    }

    public void combox() {
        super.writeJson(monthInfoService.combox());
    }

    public void datagrid(){
        super.writeJson(monthInfoService.datagrid(monthInfo));
    }

    public void add() {
        Json json = new Json();
        try {
            monthInfoService.add(monthInfo);
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
            monthInfoService.remove(monthInfo.getIds());
            json.setSuccess(true);
            json.setMsg("删除成功");
        } catch (DataIntegrityViolationException d){
            d.printStackTrace();
            json.setMsg("有使用该月考的信息存在，请先删除使用该月考的数据！");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void edit(){
        Json json = new Json();
        try {
            monthInfoService.edit(monthInfo);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void getMonthInfo(){
        super.writeJson(monthInfoService.getMonthInfo(monthInfo.getId()));
    }
}
