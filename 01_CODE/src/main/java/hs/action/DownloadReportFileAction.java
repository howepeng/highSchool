package hs.action;

import hs.model.TbReportFile;
import hs.model.TbStudent;
import hs.service.StudentServiceI;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@Action(value = "downloadReportFileAction")
@Results({ @Result(name = "downloadReport", type = "stream", params = { "contentType", "application/octet-stream", "inputName", "inputStream", "contentDisposition", "attachment;filename=\"${downloadFileName}\"", "bufferSize", "4096" })
         ,@Result(name = "nofile", location = "/error/noIDFile.jsp")})
public class DownloadReportFileAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String attachid;// 附件id
    private String filePath;
    private String fileName;// 初始的通过param指定的文件名属性


    private StudentServiceI studentService;

    @Autowired
    public void setStudentService(StudentServiceI studentService) {
        this.studentService = studentService;
    }
    public String execute() throws Exception {
        TbStudent s = studentService.reportFileDownload(attachid);
        TbReportFile file = s.getTbReportFile();
        if (file == null) {
            return "nofile";
        }
        setFileName(s.getName() + "_" + file.getFileName());
        setFilePath(file.getFilePath());
        return "downloadReport";
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

}
