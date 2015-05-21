package hs.action;

import hs.common.Property;
import hs.model.TbStudent;
import hs.pageModel.Finances;
import hs.service.FinanceServiceI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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
@Action(value = "downloadcrashHistoryAction")
@Results({
        @Result(name = "downloadcrashHistory", type = "stream", params = {
                "contentType", "application/octet-stream", "inputName",
                "inputStream", "contentDisposition",
                "attachment;filename=\"${downloadFileName}\"", "bufferSize",
                "4096" }),
        @Result(name = "nocrashHistory", location = "/error/noCrashHistory.jsp") })
public class DownloadcrashHistoryAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String attachid;// 附件id
    private String filePath;
    private String fileName;// 初始的通过param指定的文件名属性

    private FinanceServiceI financeService;

    @Autowired
    public void setFinanceService(FinanceServiceI financeService) {
        this.financeService = financeService;
    }

    public String execute() throws Exception {
        Map<String, List<Finances>> beans = financeService
                .getReportData(attachid);
        if (beans != null) {
            String destFileName = Property.getProperty("uploadPath")+"\\output\\"
                    + attachid + ".xls";
            String templateFileName = Property.getProperty("uploadPath")+"\\template\\report_template.xls";

            XLSTransformer transformer = new XLSTransformer();
            transformer.transformXLS(templateFileName, beans, destFileName);

            setFileName(attachid + ".xls");
            setFilePath(destFileName);
            return "downloadcrashHistory";
        } else {
            return "nocrashHistory";
        }
    }

    public InputStream getInputStream() throws Exception {
        // root项目上传图片路径，UPLOAD_ROOT_PATH定义为常量，从配置文件里取值
        return new FileInputStream(new File(filePath));
    }

    public String getDownloadFileName() {
        return fileName;
    }

    public void setFileName(String fileName)
            throws UnsupportedEncodingException {
        String agent = ServletActionContext.getRequest()
                .getHeader("User-agent");
        // 如果浏览器是IE浏览器，就得进行编码转换
        if (agent.contains("MSIE")) {
            this.fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            this.fileName = new String(fileName.getBytes(), "ISO-8859-1");
        }
    }

    /**
     * 读取Excel文件的内容
     *
     * @param file
     *            待读取的文件
     * @return
     */
    public void outputExcel(String fileTemplatePath, String destFileName,
            TbStudent student) {
        try {
            Collection<TbStudent> staff = new HashSet<TbStudent>();
            staff.add(student);
            Map<String, Collection<TbStudent>> beans = new HashMap<String, Collection<TbStudent>>();
            beans.put("student", staff);
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
}
