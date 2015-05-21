package hs.action;

import hs.common.vo.ChartData;
import hs.common.vo.SqlParameterVO;
import hs.common.vo.SqlResultVO;
import hs.common.vo.YData;
import hs.pageModel.Combobox;
import hs.pageModel.Json;
import hs.pageModel.ScoreCount;
import hs.pageModel.SessionInfo;
import hs.service.MonthInfoServiceI;
import hs.service.ScoreCountServiceI;
import hs.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "scoreCountAction")
public class ScoreCountAction extends BaseAction implements ModelDriven<ScoreCount> {

    ScoreCount scoreCount = new ScoreCount();

    @Override
    public ScoreCount getModel() {
        return scoreCount;
    }

    private ScoreCountServiceI scoreCountService;

    @Autowired
    public void setScoreCountService(ScoreCountServiceI scoreCountService) {
        this.scoreCountService = scoreCountService;
    }

    private MonthInfoServiceI monthInfoService;

    @Autowired
    public void setMonthInfoService(MonthInfoServiceI monthInfoService) {
        this.monthInfoService = monthInfoService;
    }

    public void datagrid(){
        SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
        super.writeJson(scoreCountService.datagrid(scoreCount, sessionInfo));
    }

    public void remove(){
      Json json = new Json();
      try {
          scoreCountService.remove(scoreCount.getIds());
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
    public void calculate(){
        Json json = new Json();
        try {
            scoreCountService.calculate(scoreCount);
            json.setSuccess(true);
            json.setMsg("月考成绩计算成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void showChartLine(){
        Json json = new Json();
        try {
            List<String> grpLst = new ArrayList<String>();
            List<YData> dataLst = new ArrayList<YData>();
            SqlParameterVO paramS = new SqlParameterVO();
            if (scoreCount.getStudentIds() != null
                    && scoreCount.getStudentIds().length > 0) {
                paramS.put("studentIds", Arrays.asList(scoreCount.getStudentIds()));
            }
            paramS.putVarchar("classId", scoreCount.getClassId());
            // 取得学生信息
            List<SqlResultVO> studentList = scoreCountService.getStudentListForFractionLine(paramS);
            if  (studentList == null || studentList.size() == 0) {
                json.setMsg("没有找到学生信息！");
                super.writeJson(json);
                return;
            }
            // 取得月考基础信息当x轴
            List<Combobox> monthInfoList = monthInfoService.combox();
            if  (monthInfoList == null || monthInfoList.size() == 0) {
                json.setMsg("没有找到月考基础信息！");
                super.writeJson(json);
                return;
            }
            int count = monthInfoList.size();
            // 取得每一个学生的月考成绩
            for (SqlResultVO item : studentList) {
                YData n = new YData();
                // 学生姓名
                n.setName((String)item.getString("name"));
                // 取得每一的月考
                List<Integer> data = new ArrayList<Integer>();
                for (int i = 0; i<count;i++) {
                    if (grpLst.size() < count) {
                        Combobox c = monthInfoList.get(i);
                        grpLst.add(c.getText());
                    }
                    SqlParameterVO param = new SqlParameterVO();
                    param.putVarchar("studentId", (String)item.getString("studentId"));
                    SqlResultVO ret = null;
                    // 班级分数
                    if ("107002".equals(scoreCount.getModeId())) {
                        if (i-1 < 0) {
                            param.putVarchar("monthTime1", monthInfoList.get(i).getId());
                            param.putVarchar("monthTime2", monthInfoList.get(i).getId());
                        } else {
                            param.putVarchar("monthTime1", monthInfoList.get(i-1).getId());
                            param.putVarchar("monthTime2", monthInfoList.get(i).getId());
                        }
                        param.putVarchar("subjectId", scoreCount.getSubjectId());
                        param.putVarchar("modeName", "class_rank");
                        ret = scoreCountService.getScoreCountForFractionLine(param);
                    // 年级排名
                    } else if ("107003".equals(scoreCount.getModeId())) {
                        if (i-1 < 0) {
                            param.putVarchar("monthTime1", monthInfoList.get(i).getId());
                            param.putVarchar("monthTime2", monthInfoList.get(i).getId());
                        } else {
                            param.putVarchar("monthTime1", monthInfoList.get(i-1).getId());
                            param.putVarchar("monthTime2", monthInfoList.get(i).getId());
                        }
                        param.putVarchar("subjectId", scoreCount.getSubjectId());
                        param.putVarchar("modeName", "grade_rank");
                        ret = scoreCountService.getScoreCountForFractionLine(param);
                    // 成绩
                    } else if ("107001".equals(scoreCount.getModeId())) {
                        param.putVarchar("modeName", StringUtil.getSubjectCulomn(scoreCount.getSubjectId()));
                        param.putVarchar("monthTimeId", monthInfoList.get(i).getId());
                        ret = scoreCountService.getFractionForFractionLine(param);
                    }
                    if (ret == null || ret.getString("value") == null ) {
                        data.add(null);
                    } else {
                        data.add((Integer.valueOf((String)ret.getString("value"))));
                    }
                }
                n.setData(data);
                dataLst.add(n);
            }

            ChartData ret = new ChartData();
            ret.setDataLst(dataLst);
            ret.setGrpLst(grpLst);
            json.setReturnObject(ret);
            json.setSuccess(true);
            json.setMsg("月考成绩计算成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
}

