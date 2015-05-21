package hs.action;

import hs.common.Property;
import hs.pageModel.GaokaoScore;
import hs.pageModel.SessionInfo;
import hs.service.GaokaoScoreServiceI;

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
@Action(value = "downloadGaokaoScoreInfoAction")
@Results({ @Result(name = "downloadGaokaoScoreInfo",
                   type = "stream",
                   params = { "contentType",
                                "application/octet-stream",
                                "inputName",
                                "inputStream",
                                "contentDisposition",
                                "attachment;filename=\"${downloadFileName}\"",
                                "bufferSize",
                                "4096" }),
           @Result(name = "noGaokaoScore", location = "/error/noGaokaoScore.jsp")})
public class DownloadGaokaoScoreInfoAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String attachid;// 附件id
    private String filePath;
    private String fileName;// 初始的通过param指定的文件名属性
    private String professionalId;
    private String className;
    private String subjectId;
    private String fractionCountStart;
    private String fractionCountEnd;
    private String objectId;
    private String averageId;

    private GaokaoScoreServiceI gaokaoScoreService;

    @Autowired
    public void setGaokaoScoreService(GaokaoScoreServiceI gaokaoScoreService) {
        this.gaokaoScoreService = gaokaoScoreService;
    }

    public String execute() throws Exception {
        GaokaoScore gaokaoScore = new GaokaoScore();
        gaokaoScore.setProfessionalId(professionalId);
        gaokaoScore.setClassName(className);
        gaokaoScore.setSubjectId(subjectId);
        gaokaoScore.setFractionCountStart(new BigDecimal(fractionCountStart));
        gaokaoScore.setFractionCountEnd(new BigDecimal(fractionCountEnd));
        gaokaoScore.setObjectId(objectId);
        gaokaoScore.setAverageId(averageId);
        SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
        List<GaokaoScore> gaokaoScoreList = gaokaoScoreService.getGaokaoScoreList(gaokaoScore, sessionInfo);
        if (gaokaoScoreList == null || gaokaoScoreList.size() <= 0) {
            return "noGaokaoScore";
        }
        String fileName = "高考信息";
        Map<String, List<GaokaoScore>> beans = new HashMap<String, List<GaokaoScore>>();
        beans.put("gaokaoScores", gaokaoScoreList);
        String filePath=Property.getProperty("uploadPath")+"\\output\\"+fileName+".xls";
        String fileTemplatePath=Property.getProperty("uploadPath")+"\\template\\gaokaoScore.xls";
        outputExcel(fileTemplatePath, filePath, beans);
        setFileName(fileName+".xls");
        setFilePath(filePath);
        return "downloadGaokaoScoreInfo";
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
    public void outputExcel(String fileTemplatePath, String destFileName, Map<String, List<GaokaoScore>> beans){
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
