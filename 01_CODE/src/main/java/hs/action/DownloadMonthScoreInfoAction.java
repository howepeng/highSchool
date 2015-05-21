package hs.action;

import hs.common.Property;
import hs.pageModel.MonthScore;
import hs.pageModel.SessionInfo;
import hs.service.MonthScoreServiceI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
@Action(value = "downloadMonthScoreInfoAction")
@Results({ @Result(name = "downloadMonthScoreInfo",
                   type = "stream",
                   params = { "contentType",
                                "application/octet-stream",
                                "inputName",
                                "inputStream",
                                "contentDisposition",
                                "attachment;filename=\"${downloadFileName}\"",
                                "bufferSize",
                                "4096" }),
           @Result(name = "noMonthScore", location = "/error/noMonthScore.jsp")})
public class DownloadMonthScoreInfoAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String attachid;// 附件id
    private String filePath;
    private String fileName;// 初始的通过param指定的文件名属性
    private String monthTimeId;
    private String professionalId;
    private String className;
    private String subjectId;
    private String fractionCountStart;
    private String fractionCountEnd;
    private String objectId;
    private String averageId;

    private MonthScoreServiceI monthScoreService;

    @Autowired
    public void setMonthScoreService(MonthScoreServiceI monthScoreService) {
        this.monthScoreService = monthScoreService;
    }


    public String execute() throws Exception {
        MonthScore monthScore = new MonthScore();
        monthScore.setMonthTimeId(monthTimeId);
        monthScore.setProfessionalId(professionalId);
        monthScore.setClassName(className);
        monthScore.setSubjectId(subjectId);
        monthScore.setFractionCountStart(new BigDecimal(fractionCountStart));
        monthScore.setFractionCountEnd(new BigDecimal(fractionCountEnd));
        monthScore.setObjectId(objectId);
        monthScore.setAverageId(averageId);
        SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
        List<MonthScore> monthScoreList = monthScoreService.getMonthScoreList(monthScore, sessionInfo);
        if (monthScoreList == null || monthScoreList.size() <= 0) {
            return "noMonthScore";
        }
        String fileName = "月考信息";
        Map<String, List<MonthScore>> beans = new HashMap<String, List<MonthScore>>();
        beans.put("monthScores", monthScoreList);
        String filePath=Property.getProperty("uploadPath")+"\\output\\"+fileName+".xls";
        String fileTemplatePath=Property.getProperty("uploadPath")+"\\template\\monthScore.xls";
        outputExcel(fileTemplatePath, filePath, beans);
        setFileName(fileName+".xls");
        setFilePath(filePath);
        return "downloadMonthScoreInfo";
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
    public void outputExcel(String fileTemplatePath, String destFileName, Map<String, List<MonthScore>> beans){
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

    public String getMonthTimeId() {
        return monthTimeId;
    }


    public void setMonthTimeId(String monthTimeId) {
        this.monthTimeId = monthTimeId;
    }


    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    public String getSubjectId() {
        return subjectId;
    }


    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }


    public String getObjectId() {
        return objectId;
    }


    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


    public String getAverageId() {
        return averageId;
    }


    public void setAverageId(String averageId) {
        this.averageId = averageId;
    }


    public String getFractionCountStart() {
        return fractionCountStart;
    }

    public void setFractionCountStart(String fractionCountStart) {
        this.fractionCountStart = fractionCountStart;
    }

    public String getFractionCountEnd() {
        return fractionCountEnd;
    }

    public void setFractionCountEnd(String fractionCountEnd) {
        this.fractionCountEnd = fractionCountEnd;
    }


    public String getProfessionalId() {
        return professionalId;
    }


    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }
}
