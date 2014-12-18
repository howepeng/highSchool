package hs.action;

import hs.pageModel.Json;
import hs.pageModel.YearInfo;
import hs.service.YearInfoServiceI;
import hs.util.HSConstants;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "yearAction")
public class YearAction extends BaseAction implements ModelDriven<YearInfo> {

    private YearInfo yearInfo = new YearInfo();

    @Override
    public YearInfo getModel() {
        return yearInfo;
    }

    private YearInfoServiceI yearInfoService;

    @Autowired
    public void setYearInfoService(YearInfoServiceI yearInfoService) {
        this.yearInfoService = yearInfoService;
    }

    public void combox() {
        super.writeJson(yearInfoService.combox());
    }

    public void datagrid(){
        super.writeJson(yearInfoService.datagrid(yearInfo));
    }

    public void add() {
        Json json = new Json();
        try {
            if (HSConstants.DIC_IS_DEFAULT.equals(yearInfo.getIsDefault() + "")) {
                yearInfoService.setNotDefaultYear(yearInfo);
            } else {
                Long count = yearInfoService.getDefaultYearCount();
                if (count == null || 0 == count) {
                    json.setSuccess(false);
                    json.setMsg("必须设定一个当前学年");
                    super.writeJson(json);
                    return;
                }
            }
            yearInfoService.add(yearInfo);
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
            boolean ret = yearInfoService.remove(yearInfo.getIds());
            if (ret) {
                json.setSuccess(true);
                json.setMsg("删除成功");
            } else {
                json.setSuccess(false);
                json.setMsg("不能删除当前学年");
            }
        } catch (DataIntegrityViolationException d){
            d.printStackTrace();
            json.setMsg("有使用该学年的学生存在，请先删除使用该学年的学生！");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);

    }
    public void edit(){
        Json json = new Json();
        try {
            if (HSConstants.DIC_IS_DEFAULT.equals(yearInfo.getIsDefault() + "")) {
                yearInfoService.setNotDefaultYear(yearInfo);
            } else {
                YearInfo old = yearInfoService.getYearInfo(yearInfo.getId());
                if (HSConstants.DIC_IS_DEFAULT.equals(old.getIsDefault() + "")) {
                    json.setSuccess(false);
                    json.setMsg("必须设定一个当前学年");
                    super.writeJson(json);
                    return;
                }
            }
            yearInfoService.edit(yearInfo);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void getYear(){
        super.writeJson(yearInfoService.getYearInfo(yearInfo.getId()));
    }
}
