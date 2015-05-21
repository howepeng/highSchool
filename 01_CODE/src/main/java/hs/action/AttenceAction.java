package hs.action;

import hs.pageModel.AttenceInfo;
import hs.pageModel.Combobox;
import hs.service.ClassInfoServiceI;
import hs.service.ClassTimeServiceI;
import hs.service.LogManagerServiceI;
import hs.util.StringUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "attenceAction", results = { @Result(name = "attence", location = "/jsp/comn/attence.jsp") })
public class AttenceAction extends BaseAction implements ModelDriven<AttenceInfo> {

    AttenceInfo attenceInfo = new AttenceInfo();

    private ClassTimeServiceI classTimeService;

    @Autowired
    public void setClassTimeService(ClassTimeServiceI classTimeService) {
        this.classTimeService = classTimeService;
    }

    private ClassInfoServiceI classInfoService;

    @Autowired
    public void setClassInfoService(ClassInfoServiceI classInfoService) {
        this.classInfoService = classInfoService;
    }
    @Override
    public AttenceInfo getModel() {
        return attenceInfo;
    }

    private LogManagerServiceI logManagerService;

    @Autowired
    public void setLogManagerService(LogManagerServiceI logManagerService) {
        this.logManagerService = logManagerService;
    }

    public void datagrid(){
        JSONObject ret = new JSONObject();
        try {
            if (StringUtil.isEmpty(attenceInfo.getDate())) {
                return;
            }
            if (StringUtil.isNotEmpty(attenceInfo.getClassId())) {
                ret = attenceOneClass();
            } else {
                ret = attenceAllClass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.writeJson(ret);
    }

    private JSONObject attenceOneClass() throws ParseException {
        JSONObject ret = new JSONObject();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
        Date date = formatDate.parse(attenceInfo.getDate());
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        int dayCount = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
        ret.put("total", dayCount);
        JSONObject[] rows = new JSONObject[dayCount];
        List<Combobox> classTimes = classTimeService.combox();
        int studentCount = classInfoService.getStudentCount(attenceInfo.getClassId());
        Map<String,Integer> studentCountTotal = new HashMap<String, Integer>();
        Map<String,Integer> realCountTotal = new HashMap<String, Integer>();
        for (int i=1; i <= dayCount; i++) {
            JSONObject row = new JSONObject();
            String day = attenceInfo.getDate() + "-" + i;
            row.put("date", i);
            for (Combobox item : classTimes) {
                row.put(item.getId() + "_1", studentCount);
                if (studentCountTotal.containsKey(item.getId())) {
                    studentCountTotal.put(item.getId(), studentCountTotal.get(item.getId()) + studentCount);
                } else {
                    studentCountTotal.put(item.getId(), studentCount);
                }
                AttenceInfo attenceS = new AttenceInfo();
                attenceS.setClassId(attenceInfo.getClassId());
                attenceS.setClassTimeId(item.getId());
                attenceS.setDate(day);
                int realCount = studentCount - logManagerService.getStudentCountByAttence(attenceS);
                if (realCountTotal.containsKey(item.getId())) {
                    realCountTotal.put(item.getId(), realCountTotal.get(item.getId()) + realCount);
                } else {
                    realCountTotal.put(item.getId(), realCount);
                }
                row.put(item.getId() + "_2", realCount);
                int delay = logManagerService.getStudentCountByDelay(attenceS);
                row.put(item.getId() + "_3", delay);
            }
            rows[i-1] = row;
        }
        ret.put("rows", rows);
        JSONObject footerRow = new JSONObject();
        footerRow.put("date", "月出勤率");
        DecimalFormat df1 = new DecimalFormat("0.00%");
        MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
        for (Combobox item : classTimes) {
            if (studentCountTotal.containsKey(item.getId()) && realCountTotal.containsKey(item.getId())) {
                footerRow.put(item.getId() + "_1", studentCountTotal.get(item.getId()));
                footerRow.put(item.getId() + "_2", realCountTotal.get(item.getId()));
                BigDecimal a = new BigDecimal(studentCountTotal.get(item.getId()));
                BigDecimal b = new BigDecimal(realCountTotal.get(item.getId()));
                BigDecimal c = b.divide(a,mc);
                footerRow.put(item.getId() + "_3", df1.format(c));
            } else {
                footerRow.put(item.getId() + "_1", 0);
                footerRow.put(item.getId() + "_2", 0);
                footerRow.put(item.getId() + "_3", "0%");
            }
        }
        JSONObject[] footer = new JSONObject[1];
        footer[0] = footerRow;
        ret.put("footer", footer);
        return ret;
    }

    private JSONObject attenceAllClass() throws ParseException {
        JSONObject ret = new JSONObject();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
        Date date = formatDate.parse(attenceInfo.getDate());
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        int dayCount = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<Combobox> classInfo = classInfoService.combox();
        ret.put("total", classInfo.size());
        JSONObject[] rows = new JSONObject[classInfo.size()];
        int rowindex = 0;
        for (Combobox classItem : classInfo) {
            List<Combobox> classTimes = classTimeService.combox();
            int studentCount = classInfoService.getStudentCount(classItem.getId());
            Map<String,Integer> studentCountTotal = new HashMap<String, Integer>();
            Map<String,Integer> realCountTotal = new HashMap<String, Integer>();
            Map<String,Integer> delayCountTotal = new HashMap<String, Integer>();
            JSONObject row = new JSONObject();
            row.put("className", classItem.getText());
            for (int i=1; i <= dayCount; i++) {
                String day = attenceInfo.getDate() + "-" + i;
                for (Combobox item : classTimes) {
                    if (studentCountTotal.containsKey(item.getId())) {
                        studentCountTotal.put(item.getId(), studentCountTotal.get(item.getId()) + studentCount);
                    } else {
                        studentCountTotal.put(item.getId(), studentCount);
                    }
                    AttenceInfo attenceS = new AttenceInfo();
                    attenceS.setClassId(classItem.getId());
                    attenceS.setClassTimeId(item.getId());
                    attenceS.setDate(day);
                    int realCount = studentCount - logManagerService.getStudentCountByAttence(attenceS);
                    if (realCountTotal.containsKey(item.getId())) {
                        realCountTotal.put(item.getId(), realCountTotal.get(item.getId()) + realCount);
                    } else {
                        realCountTotal.put(item.getId(), realCount);
                    }
                    int delay = logManagerService.getStudentCountByDelay(attenceS);
                    if (delayCountTotal.containsKey(classItem.getId())) {
                        delayCountTotal.put(classItem.getId(), delayCountTotal.get(classItem.getId()) + delay);
                    } else {
                        delayCountTotal.put(classItem.getId(), delay);
                    }
                }
            }
            DecimalFormat df1 = new DecimalFormat("0.00%");
            MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
            for (Combobox item : classTimes) {
                if (studentCountTotal.containsKey(item.getId()) && realCountTotal.containsKey(item.getId())) {
                    BigDecimal a = new BigDecimal(studentCountTotal.get(item.getId()));
                    BigDecimal b = new BigDecimal(realCountTotal.get(item.getId()));
                    BigDecimal c = b.divide(a,mc);
                    row.put(item.getId(), df1.format(c));
                } else {
                    row.put(item.getId(), "0%");
                }
            }
            row.put("delay", delayCountTotal.get(classItem.getId()));
            rows[rowindex] = row;
            rowindex++;
        }
        ret.put("rows", rows);
        return ret;
    }
}
