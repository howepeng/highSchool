package hs.action;

import hs.common.vo.SqlResultVO;
import hs.pageModel.GaokaoScore;
import hs.pageModel.Json;
import hs.pageModel.SessionInfo;
import hs.service.GaokaoScoreServiceI;
import hs.util.StringUtil;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "gaokaoScoreAction")
public class GaokaoScoreAction extends BaseAction implements ModelDriven<GaokaoScore> {

    GaokaoScore gaokaoScore = new GaokaoScore();

    @Override
    public GaokaoScore getModel() {
        return gaokaoScore;
    }

    private GaokaoScoreServiceI gaokaoScoreService;

    @Autowired
    public void setGaokaoScoreService(GaokaoScoreServiceI gaokaoScoreService) {
        this.gaokaoScoreService = gaokaoScoreService;
    }

    public void datagrid(){
        SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
        super.writeJson(gaokaoScoreService.datagrid(gaokaoScore, sessionInfo));
    }
    public void remove(){
      Json json = new Json();
      try {
          gaokaoScoreService.remove(gaokaoScore.getIds());
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
            String[] ret = gaokaoScoreService.uploadClassScore(gaokaoScore);
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
            // 专业和 班级名称
            if (StringUtil.isEmpty(gaokaoScore.getProfessionalId())
                     && StringUtil.isEmpty(gaokaoScore.getClassName())) {
                 json.setSuccess(false);
                 json.setMsg("无条件：专业和 班级名称都没有");
                 super.writeJson(json);
                 return;
            }
            SqlResultVO ret = gaokaoScoreService.averageClassType(gaokaoScore);
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
            // 班级名称
            if (StringUtil.isEmpty(gaokaoScore.getClassName())) {
                 json.setSuccess(false);
                 json.setMsg("无条件：班级名称都没有");
                 super.writeJson(json);
                 return;
            }
            SqlResultVO ret = gaokaoScoreService.averageClassName(gaokaoScore);
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
