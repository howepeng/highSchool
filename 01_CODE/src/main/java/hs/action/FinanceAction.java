package hs.action;

import hs.pageModel.Finance;
import hs.pageModel.Json;
import hs.service.FinanceServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "financeAction")
public class FinanceAction extends BaseAction implements ModelDriven<Finance> {

    private Finance finance  = new Finance();
    @Override
    public Finance getModel() {
        return finance;
    }

    private FinanceServiceI financeService;

    @Autowired
    public void setFinanceService(FinanceServiceI financeService) {
        this.financeService = financeService;
    }

    public void datagridBeforePay(){
        super.writeJson(financeService.datagridBeforePay(finance));
    }

    public void datagridAfterPay(){
        super.writeJson(financeService.datagridAfterPay(finance));
    }

    public void crashReceipt(){
        Json json = new Json();
        try {
            financeService.crashReceipt(finance);
            json.setSuccess(true);
            json.setMsg("收款成功");

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void remove(){
        Json json = new Json();
        try {
            String ret = financeService.deleteFinance(finance.getIds());
            if ("success".equals(ret)) {
                json.setSuccess(true);
                json.setMsg("删除成功");
            } else {
                json.setSuccess(false);
                json.setMsg("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void rollback(){
        Json json = new Json();
        try {
             String ret = financeService.rollbackFinance(finance.getIds());
             if ("success".equals(ret)) {
                 json.setSuccess(true);
                 json.setMsg("撤销成功");
             }else if ("noToday".equals(ret)) {
                     json.setSuccess(false);
                     json.setMsg("撤销失败!不是当天的数据不能撤销!");
             } else {
                 json.setSuccess(false);
                 json.setMsg("撤销失败");
             }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
}
