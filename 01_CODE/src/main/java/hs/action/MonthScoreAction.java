package hs.action;

import hs.common.vo.SqlResultVO;
import hs.pageModel.Json;
import hs.pageModel.MonthScore;
import hs.pageModel.SessionInfo;
import hs.service.MonthScoreServiceI;
import hs.util.StringUtil;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "monthScoreAction")
public class MonthScoreAction extends BaseAction implements ModelDriven<MonthScore> {

    MonthScore monthScore = new MonthScore();

    @Override
    public MonthScore getModel() {
        return monthScore;
    }

    private MonthScoreServiceI monthScoreService;

    @Autowired
    public void setMonthScoreService(MonthScoreServiceI monthScoreService) {
        this.monthScoreService = monthScoreService;
    }

    public void datagrid(){
        SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
        super.writeJson(monthScoreService.datagrid(monthScore, sessionInfo));
    }
    public void remove(){
      Json json = new Json();
      try {
          monthScoreService.remove(monthScore.getIds());
          json.setSuccess(true);
          json.setMsg("删除成功");
      } catch (DataIntegrityViolationException d){
          d.printStackTrace();
          json.setMsg("有使用该班级的数据存在，请先删除使用该班级的数据！");
      } catch (Exception e) {
          e.printStackTrace();
          json.setMsg(e.getMessage());
      }
      super.writeJson(json);

    }
    public void uploadClassScore() {
        Json json = new Json();
        String text = "上传失败";
        try {
            String[] ret = monthScoreService.uploadClassScore(monthScore);
            if (ret != null) {
                json.setReturnObject(ret);
                json.setSuccess(true);
                text = "上传成功";
            } else {
                json.setReturnObject(ret);
                json.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        } finally {
            json.setMsg(text);
        }
        super.writeJson(json);
    }

    // 求平均值
    public void averageClassType() {
        Json json = new Json();
        try {
            // 月考信息
            if (StringUtil.isEmpty(monthScore.getMonthTimeId())) {
                 json.setSuccess(false);
                 json.setMsg("无条件：月考信息");
                 super.writeJson(json);
                 return;
            }
            // 专业和 班级名称
            if (StringUtil.isEmpty(monthScore.getProfessionalId())
                     && StringUtil.isEmpty(monthScore.getClassName())) {
                 json.setSuccess(false);
                 json.setMsg("无条件：专业和 班级名称都没有");
                 super.writeJson(json);
                 return;
            }
            SqlResultVO ret = monthScoreService.averageClassType(monthScore);
            if (ret != null) {
                json.setReturnObject(ret);
                json.setSuccess(true);
                json.setMsg("平均值计算完了");
            } else {
                json.setSuccess(false);
                json.setMsg("计算失败！");
            }
        } catch (DataIntegrityViolationException d){
            d.printStackTrace();
            json.setMsg("计算失败！");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    // 求平均值
    public void averageClassName() {
        Json json = new Json();
        try {
            // 月考信息
            if (StringUtil.isEmpty(monthScore.getMonthTimeId())) {
                 json.setSuccess(false);
                 json.setMsg("无条件：月考信息");
                 super.writeJson(json);
                 return;
            }
            // 班级名称
            if (StringUtil.isEmpty(monthScore.getClassName())) {
                 json.setSuccess(false);
                 json.setMsg("无条件： 班级名称都没有");
                 super.writeJson(json);
                 return;
            }
            SqlResultVO ret = monthScoreService.averageClassName(monthScore);
            if (ret != null) {
                json.setReturnObject(ret);
                json.setSuccess(true);
                json.setMsg("平均值计算完了");
            } else {
                json.setSuccess(false);
                json.setMsg("计算失败！");
            }
        } catch (DataIntegrityViolationException d){
            d.printStackTrace();
            json.setMsg("计算失败！");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
}
