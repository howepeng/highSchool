package hs.action;

import hs.common.Property;
import hs.pageModel.ScoreCount;
import hs.pageModel.SessionInfo;
import hs.service.ScoreCountServiceI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
@Action(value = "downloadScoreCountInfoAction")
@Results({ @Result(name = "downloadScoreCountInfo",
                   type = "stream",
                   params = { "contentType",
                                "application/octet-stream",
                                "inputName",
                                "inputStream",
                                "contentDisposition",
                                "attachment;filename=\"${downloadFileName}\"",
                                "bufferSize",
                                "4096" }),
           @Result(name = "noMonthScore", location = "/error/noScoreCount.jsp")})
public class DownloadScoreCountInfoAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String attachid;// 附件id
    private String filePath;
    private String fileName;// 初始的通过param指定的文件名属性
    private String monthTime1;
    private String monthTime2;
    private String professionalId;
    public String getProfessionalId() {
        return professionalId;
    }


    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }


    private String classInfoId;
    private String subjectId;

    private ScoreCountServiceI scoreCountService;

    @Autowired
    public void setScoreCountService(ScoreCountServiceI scoreCountService) {
        this.scoreCountService = scoreCountService;
    }


    public String execute() throws Exception {
        ScoreCount scoreCount = new ScoreCount();
        scoreCount.setMonthTime1(monthTime1);
        scoreCount.setMonthTime2(monthTime2);
        scoreCount.setProfessionalId(professionalId);
        scoreCount.setClassInfoId(classInfoId);
        scoreCount.setSubjectId(subjectId);
        SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
        List<ScoreCount> scoreCountList = scoreCountService.getScoreCountList(scoreCount, sessionInfo);
        String fileName = "月考排名信息";
        Map<String, List<ScoreCount>> beans = new HashMap<String, List<ScoreCount>>();
        beans.put("scoreCounts", scoreCountList);
        String filePath=Property.getProperty("uploadPath")+"\\output\\"+fileName+".xls";
        String fileTemplatePath=Property.getProperty("uploadPath")+"\\template\\scoreCount.xls";
        outputExcel(fileTemplatePath, filePath, beans);
        setFileName(fileName+".xls");
        setFilePath(filePath);
        return "downloadScoreCountInfo";
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
    public void outputExcel(String fileTemplatePath, String destFileName, Map<String, List<ScoreCount>> beans){
        try {
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getMonthTime1() {
        return monthTime1;
    }

    public void setMonthTime1(String monthTime1) {
        this.monthTime1 = monthTime1;
    }

    public String getMonthTime2() {
        return monthTime2;
    }

    public void setMonthTime2(String monthTime2) {
        this.monthTime2 = monthTime2;
    }


    public String getClassInfoId() {
        return classInfoId;
    }


    public void setClassInfoId(String classInfoId) {
        this.classInfoId = classInfoId;
    }
}
