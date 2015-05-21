package hs.action;

import hs.pageModel.DeductionFee;
import hs.pageModel.Json;
import hs.service.DeductionFeeServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "deductionFeeAction")
public class DeductionFeeAction extends BaseAction implements ModelDriven<DeductionFee> {

    private DeductionFee deductionFee = new DeductionFee();
    @Override
    public DeductionFee getModel() {
        return deductionFee;
    }

    private DeductionFeeServiceI deductionFeeService;

    @Autowired
    public void setdeductionFeeService(DeductionFeeServiceI deductionFeeService) {
        this.deductionFeeService = deductionFeeService;
    }

    public void combox() {
        super.writeJson(deductionFeeService.combox());
    }

    public void datagrid(){
        super.writeJson(deductionFeeService.datagrid(deductionFee));
    }

    public void add() {
        Json json = new Json();
        try {
            deductionFeeService.add(deductionFee);
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
            deductionFeeService.remove(deductionFee.getIds());
            json.setSuccess(true);
            json.setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);

    }
    public void edit(){
        Json json = new Json();
        try {
            deductionFeeService.edit(deductionFee);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void getdeductionFee(){
        super.writeJson(deductionFeeService.getDeductionFee(deductionFee.getId()));
    }
}
