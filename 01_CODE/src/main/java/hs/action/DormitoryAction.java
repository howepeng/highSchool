package hs.action;

import hs.pageModel.DormitoryInfo;
import hs.pageModel.Json;
import hs.service.DormitoryInfoServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "dormitoryAction")
public class DormitoryAction extends BaseAction implements ModelDriven<DormitoryInfo> {

    private DormitoryInfo dormitory = new DormitoryInfo();

    @Override
    public DormitoryInfo getModel() {
        return dormitory;
    }

    private DormitoryInfoServiceI dormitoryInfoService;

    @Autowired
    public void setDormitoryInfoService(DormitoryInfoServiceI dormitoryInfoService) {
        this.dormitoryInfoService = dormitoryInfoService;
    }

    public void combox() {
        super.writeJson(dormitoryInfoService.combox());
    }

    public void datagrid(){
        super.writeJson(dormitoryInfoService.datagrid(dormitory));
    }

    public void add() {
        Json json = new Json();
        try {
            dormitoryInfoService.add(dormitory);
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
            boolean ret = dormitoryInfoService.remove(dormitory.getIds());
            if (ret) {
                json.setSuccess(true);
                json.setMsg("删除成功");
            } else {
                json.setSuccess(false);
                json.setMsg("不能删除当前宿舍");
            }
        } catch (DataIntegrityViolationException d){
            d.printStackTrace();
            json.setMsg("有使用该宿舍的学生存在，请先删除使用该宿舍的学生！");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);

    }
    public void edit(){
        Json json = new Json();
        try {
            dormitoryInfoService.edit(dormitory);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void getYear(){
        super.writeJson(dormitoryInfoService.getDormitoryInfo(dormitory.getId()));
    }
}
