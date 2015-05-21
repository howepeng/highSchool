package hs.action;

import hs.common.Property;
import hs.pageModel.AttenceInfo;
import hs.pageModel.Combobox;
import hs.service.ClassInfoServiceI;
import hs.service.ClassTimeServiceI;
import hs.service.LogManagerServiceI;
import hs.util.StringUtil;
import hs.vo.AttenceClass;
import hs.vo.AttenceClassColumn;
import hs.vo.AttenceClassRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.Configuration;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@Action(value = "downloadAttenceAction")
@Results({ @Result(name = "downloadAttenceInfo",
                   type = "stream",
                   params = { "contentType",
                            "application/octet-stream",
                            "inputName", "inputStream",
                            "contentDisposition",
                            "attachment;filename=\"${downloadFileName}\"",
                            "bufferSize", "4096" }) ,
           @Result(name = "noAttence", location = "/error/noAttence.jsp")})
public class DownloadAttenceAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String attachid;// 附件id
    private String filePath;
    private String fileName;// 初始的通过param指定的文件名属性
    private String date;
    private String classId;

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

    private LogManagerServiceI logManagerService;

    @Autowired
    public void setLogManagerService(LogManagerServiceI logManagerService) {
        this.logManagerService = logManagerService;
    }
    public String execute() throws Exception {
        if (StringUtil.isEmpty(this.date)) {
            return "noAttence";
        }
        if (StringUtil.isEmpty(this.classId)) {
            allClass();
        } else{
            oneClass();
        }

        return "downloadAttenceInfo";
    }

    public InputStream getInputStream() throws Exception {
        // root项目上传图片路径，UPLOAD_ROOT_PATH定义为常量，从配置文件里取值
        return new FileInputStream(new File(filePath));
    }

    public String getDownloadFileName() {
        return fileName;
    }

    public void setFileName(String fileName) throws UnsupportedEncodingException {
        String agent = ServletActionContext.getRequest().getHeader("User-agent");
        // 如果浏览器是IE浏览器，就得进行编码转换
        if (agent.contains("MSIE")) {
            this.fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            this.fileName = new String(fileName.getBytes(), "ISO-8859-1");
        }
    }

    /**读取Excel文件的内容
     * @param file  待读取的文件
     * @return
     */
    public void outputExcel(String fileTemplatePath, String destFileName, AttenceClass info){
        try {
            Map<String, Object> beans = new HashMap<String, Object>();
            beans.put("attenceClass", info);
            Configuration config = new Configuration();
            XLSTransformer transformer = new XLSTransformer(config);
            transformer.transformXLS(fileTemplatePath, beans, destFileName);
        } catch (ParsePropertyException e) {
            e.printStackTrace();
            throw e;
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getAttachid() {
        return attachid;
    }

    public void setAttachid(String attachid) {
        this.attachid = attachid;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }

    private void allClass() throws Exception  {

        AttenceClass info = new AttenceClass();
        info.setDate(this.date);
        List<Combobox> classTimes = classTimeService.combox();
        List<Combobox> classInfo = classInfoService.combox();
        List<Combobox> titles = new ArrayList<Combobox>();
        Combobox classTitle = new Combobox();
        classTitle.setId("0");
        classTitle.setText("班级");
        titles.add(classTitle);
        for (Combobox item : classTimes) {
            Combobox title = new Combobox();
            title.setId(item.getId());
            title.setText(item.getText());
            titles.add(title);
        }
        Combobox delayTitle = new Combobox();
        delayTitle.setId("99");
        delayTitle.setText("迟到总人次");
        titles.add(delayTitle);
        info.setTitles(titles);

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
        Date date = formatDate.parse(this.date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        int dayCount = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<AttenceClassRow> rows = new ArrayList<AttenceClassRow>();
        for (Combobox classItem : classInfo) {
            Map<String,Integer> studentCountTotal = new HashMap<String, Integer>();
            Map<String,Integer> realCountTotal = new HashMap<String, Integer>();
            Map<String,Integer> delayCountTotal = new HashMap<String, Integer>();
            int studentCount = classInfoService.getStudentCount(classItem.getId());
            for (int i=1; i <= dayCount; i++) {
                String day = this.date + "-" + i;
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
            AttenceClassRow row = new AttenceClassRow();
            List<AttenceClassColumn> columns = new ArrayList<AttenceClassColumn>();
            AttenceClassColumn column = new AttenceClassColumn();
            column.setText(classItem.getText());
            columns.add(column);
            DecimalFormat df1 = new DecimalFormat("0.00%");
            MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
            for (Combobox item : classTimes) {
                AttenceClassColumn attenceColumnItem = new AttenceClassColumn();
                if (studentCountTotal.containsKey(item.getId()) && realCountTotal.containsKey(item.getId())) {
                    BigDecimal a = new BigDecimal(studentCountTotal.get(item.getId()));
                    BigDecimal b = new BigDecimal(realCountTotal.get(item.getId()));
                    BigDecimal c = b.divide(a,mc);
                    attenceColumnItem.setText(df1.format(c));
                } else {
                    attenceColumnItem.setText("0%");
                }
                columns.add(attenceColumnItem);
            }
            AttenceClassColumn delayColumn = new AttenceClassColumn();
            delayColumn.setText(delayCountTotal.get(classItem.getId()) + "");
            columns.add(delayColumn);
            row.setColumns(columns);
            rows.add(row);
        }
        info.setRows(rows);

        String filePath=Property.getProperty("uploadPath")+"\\output\\考勤汇总_"+this.date+".xls";
        String fileTemplatePath=Property.getProperty("uploadPath")+"\\template\\attenceAllClass.xls";
        outputExcel(fileTemplatePath, filePath, info);
        setFileName("考勤汇总_"+this.date+".xls");
        setFilePath(filePath);
    }

    private void oneClass() throws Exception {
        AttenceClass info = new AttenceClass();
        info.setDate(this.date);
        info.setTitle("日期");
        List<Combobox> classTimes = classTimeService.combox();
        List<Combobox> titles = new ArrayList<Combobox>();
        for (Combobox item : classTimes) {
            Combobox title1 = new Combobox();
            title1.setId(item.getId() + "_1");
            title1.setText("应");
            titles.add(title1);
            Combobox title2 = new Combobox();
            title1.setId(item.getId() + "_2");
            title2.setText("实");
            titles.add(title2);
            Combobox title3 = new Combobox();
            title1.setId(item.getId() + "_3");
            title3.setText("迟");
            titles.add(title3);
        }
        info.setTitles(titles);
        info.setHeaders(classTimes);

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
        Date date = formatDate.parse(this.date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        int dayCount = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
        int studentCount = classInfoService.getStudentCount(this.classId);
        Map<String,Integer> studentCountTotal = new HashMap<String, Integer>();
        Map<String,Integer> realCountTotal = new HashMap<String, Integer>();
        List<AttenceClassRow> rows = new ArrayList<AttenceClassRow>();
        for (int i=1; i <= dayCount; i++) {
            AttenceClassRow row = new AttenceClassRow();
            List<AttenceClassColumn> columns = new ArrayList<AttenceClassColumn>();
            AttenceClassColumn column = new AttenceClassColumn();
            String day = this.date + "-" + i;
            column.setText(i+"");
            columns.add(column);
            for (Combobox item : classTimes) {
                AttenceClassColumn studentCountColumnItem = new AttenceClassColumn();
                studentCountColumnItem.setText(studentCount+"");
                columns.add(studentCountColumnItem);
                if (studentCountTotal.containsKey(item.getId())) {
                    studentCountTotal.put(item.getId(), studentCountTotal.get(item.getId()) + studentCount);
                } else {
                    studentCountTotal.put(item.getId(), studentCount);
                }
                AttenceInfo attenceS = new AttenceInfo();
                attenceS.setClassId(this.classId);
                attenceS.setClassTimeId(item.getId());
                attenceS.setDate(day);
                int realCount = studentCount - logManagerService.getStudentCountByAttence(attenceS);
                if (realCountTotal.containsKey(item.getId())) {
                    realCountTotal.put(item.getId(), realCountTotal.get(item.getId()) + realCount);
                } else {
                    realCountTotal.put(item.getId(), realCount);
                }
                AttenceClassColumn realCountColumnItem = new AttenceClassColumn();
                realCountColumnItem.setText(realCount+"");
                columns.add(realCountColumnItem);
                int delay = logManagerService.getStudentCountByDelay(attenceS);
                AttenceClassColumn delayCountColumnItem = new AttenceClassColumn();
                delayCountColumnItem.setText(delay+"");
                columns.add(delayCountColumnItem);
            }
            row.setColumns(columns);
            rows.add(row);
        }
        AttenceClassRow footerRow = new AttenceClassRow();
        List<AttenceClassColumn> footerColumn = new ArrayList<AttenceClassColumn>();
        AttenceClassColumn title = new AttenceClassColumn();
        title.setText("月出勤率");
        footerColumn.add(title);
        DecimalFormat df1 = new DecimalFormat("0.00%");
        MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
        for (Combobox item : classTimes) {
            if (studentCountTotal.containsKey(item.getId()) && realCountTotal.containsKey(item.getId())) {
                AttenceClassColumn studentCountColumn = new AttenceClassColumn();
                studentCountColumn.setText(studentCountTotal.get(item.getId())+"");
                footerColumn.add(studentCountColumn);
                AttenceClassColumn realCountColumn = new AttenceClassColumn();
                realCountColumn.setText(realCountTotal.get(item.getId())+"");
                footerColumn.add(realCountColumn);
                BigDecimal a = new BigDecimal(studentCountTotal.get(item.getId()));
                BigDecimal b = new BigDecimal(realCountTotal.get(item.getId()));
                BigDecimal c = b.divide(a,mc);
                AttenceClassColumn rateColumn = new AttenceClassColumn();
                rateColumn.setText(df1.format(c));
                footerColumn.add(rateColumn);
            } else {
                AttenceClassColumn studentCountColumn = new AttenceClassColumn();
                studentCountColumn.setText("0");
                footerColumn.add(studentCountColumn);
                AttenceClassColumn realCountColumn = new AttenceClassColumn();
                realCountColumn.setText("0");
                footerColumn.add(realCountColumn);
                AttenceClassColumn rateColumn = new AttenceClassColumn();
                rateColumn.setText("0%");
                footerColumn.add(rateColumn);
            }
        }
        footerRow.setColumns(footerColumn);
        rows.add(footerRow);
        info.setRows(rows);

        String filePath=Property.getProperty("uploadPath")+"\\output\\考勤_"+this.date+".xls";
        String fileTemplatePath=Property.getProperty("uploadPath")+"\\template\\attenceClass.xls";
        outputExcel(fileTemplatePath, filePath, info);
        setFileName("考勤_"+this.date+".xls");
        setFilePath(filePath);
    }
}
