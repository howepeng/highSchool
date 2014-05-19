package hs.action;

import hs.pageModel.Json;
import hs.pageModel.Preferential;
import hs.service.PreferentialServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "preferentialAction")
public class PreferentialAction extends BaseAction implements ModelDriven<Preferential> {

    private Preferential preferential = new Preferential();
    @Override
    public Preferential getModel() {
        return preferential;
    }

    private PreferentialServiceI preferentialService;

    @Autowired
    public void setPreferentialService(PreferentialServiceI preferentialService) {
        this.preferentialService = preferentialService;
    }

    public void combox() {
        super.writeJson(preferentialService.combox());
    }

    public void datagrid(){
        super.writeJson(preferentialService.datagrid(preferential));
    }

    public void add() {
        Json json = new Json();
        try {
            preferentialService.add(preferential);
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
            preferentialService.remove(preferential.getIds());
            json.setSuccess(true);
            json.setMsg("删除成功");
        } catch (DataIntegrityViolationException d){
            d.printStackTrace();
            json.setMsg("有使用该优惠类型的学生存在，请先删除使用该优惠类型的学生！");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);

    }
    public void edit(){
        Json json = new Json();
        try {
            preferentialService.edit(preferential);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void getPreferential(){
        super.writeJson(preferentialService.getPreferential(preferential.getId()));
    }
}
