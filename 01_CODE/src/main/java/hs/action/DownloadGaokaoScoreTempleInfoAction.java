package hs.action;

import hs.common.Property;
import hs.pageModel.GaokaoScoreStudent;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;
import hs.service.GaokaoScoreServiceI;

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
@Action(value = "downloadGaokaoScoreTempleInfoAction")
@Results({ @Result(name = "downloadGaokaoScoreTempleInfo", type = "stream", params = { "contentType", "application/octet-stream", "inputName", "inputStream", "contentDisposition", "attachment;filename=\"${downloadFileName}\"", "bufferSize", "4096" }),
           @Result(name = "noStudentInfo", location = "/error/noStuentInfo.jsp")
         })
public class DownloadGaokaoScoreTempleInfoAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String classId;
    private String className;
    private String filePath;
    private String fileName;// 初始的通过param指定的文件名属性

    private GaokaoScoreServiceI gaokaoScoreService;

    @Autowired
    public void setGaokaoScoreService(GaokaoScoreServiceI gaokaoScoreService) {
        this.gaokaoScoreService = gaokaoScoreService;
    }

    public String execute() throws Exception {

        Student studentS = new Student();
        studentS.setClassId(this.classId);
        SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
        List<GaokaoScoreStudent> studentList = gaokaoScoreService.getStudentInfo(studentS, sessionInfo);
        String fileName = null;
        if (studentList != null) {
            int count = studentList.size();
            if(count == 0){
                return "noStudentInfo";
            }
            fileName = studentList.get(0).getClassName();
        } else {
            return "noStudentInfo";
        }
        fileName = fileName + "高考成绩录入";
        Map<String, List<GaokaoScoreStudent>> beans = new HashMap<String, List<GaokaoScoreStudent>>();
        beans.put("gaokaoScores", studentList);

        String filePath=Property.getProperty("uploadPath")+"\\output\\"+fileName+".xls";
        String fileTemplatePath=Property.getProperty("uploadPath")+"\\template\\gaokaoClassScore.xls";
        outputExcel(fileTemplatePath, filePath, beans);

        setFileName(fileName+".xls");
        setFilePath(filePath);
        return "downloadGaokaoScoreTempleInfo";
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
    public void outputExcel(String fileTemplatePath, String destFileName, Map<String, List<GaokaoScoreStudent>> beans){
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
}
