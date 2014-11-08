package hs.action;

import hs.model.TbStudent;
import hs.pageModel.Student;
import hs.pageModel.Students;
import hs.service.StudentServiceI;
import hs.util.HSConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@Action(value = "downloadAllStudentInfoAction")
@Results({ @Result(name = "downloadAllStudentInfo",
                   type = "stream",
                   params = { "contentType",
                                "application/octet-stream",
                                "inputName",
                                "inputStream",
                                "contentDisposition",
                                "attachment;filename=\"${downloadFileName}\"",
                                "bufferSize",
                                "4096" }),
           @Result(name = "noStudentInfo", location = "/error/noStuentInfo.jsp")})
public class DownloadAllStudentInfoAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String attachid;// 附件id
    private String filePath;
    private String fileName;// 初始的通过param指定的文件名属性
    private String name;
    private String classType;
    private Timestamp createdatetimeStart;
    private Timestamp createdatetimeEnd;
    private String fractionCountStart;
    private String fractionCountEnd;
    private StudentServiceI studentService;

    @Autowired
    public void setStudentService(StudentServiceI studentService) {
        this.studentService = studentService;
    }

    public String execute() throws Exception {
        Student studentS = new Student();
        studentS.setName(new String(this.name.getBytes("ISO-8859-1"), "UTF-8"));
        studentS.setClassType(this.classType);
        studentS.setCreatedatetimeStart(createdatetimeStart);
        studentS.setCreatedatetimeEnd(createdatetimeEnd);
        studentS.fractionCountStart = this.fractionCountStart;
        studentS.fractionCountEnd = this.fractionCountEnd;
        List<TbStudent> tbStudentList = studentService.getStudentInfo(studentS);
        String fileName = "全部学生";
        List<Student> studentList = new ArrayList<Student>();
        int index = 0;
        List<Students> studentsList = new ArrayList<Students>();
        Students students = new Students();
        if (tbStudentList != null) {
            int count = tbStudentList.size();
            if(count == 0){
                return "noStudentInfo";
            }
            for (TbStudent item : tbStudentList){
                if ("91".equals(item.getWlqf())) {
                    item.setWlqfContent("文科");
                } else if ("95".equals(item.getWlqf())) {
                    item.setWlqfContent("理科");
                } else if ("93".equals(item.getWlqf())) {
                    item.setWlqfContent("艺术文科");
                } else if ("97".equals(item.getWlqf())) {
                    item.setWlqfContent("艺术理科");
                } else if ("94".equals(item.getWlqf())) {
                    item.setWlqfContent("体育文科");
                } else if ("98".equals(item.getWlqf())) {
                    item.setWlqfContent("体育理科");
                }
                if ("0".equals(item.getStudentType())) {
                    item.setStuTypeContent("复读");
                } else if ("1".equals(item.getStudentType())) {
                    item.setStuTypeContent("应届");
                } else if ("2".equals(item.getStudentType())) {
                    item.setStuTypeContent("往届");
                }

                if ("0".equals(item.getSex())) {
                    item.setSexContent("男");
                } else if ("1".equals(item.getSex())) {
                    item.setSexContent("女");
                }

                if ("0".equals(item.getSignedFlg())) {
                    item.setSignedContent("未签约");
                } else if ("1".equals(item.getSignedFlg())) {
                    item.setSignedContent("已签约");
                }

                if ("1".equals(item.getStayFlg())) {
                    item.setStayContent("住宿");
                } else if ("0".equals(item.getStayFlg())) {
                    item.setStayContent("走读");
                }

                if ("1".equals(item.getSelfstudyNightflg())) {
                    item.setSelfstudyNightContent("晚自习");
                }

                if ("1".equals(item.getSelfstudyNoonflg())) {
                    item.setSelfstudyNoonContent("午  休 ");
                }
                if ("1".equals(item.getSignUpMoneyFlg())) {
                    item.setSignUpMoneyContent("报名费已交 ");
                } else{
                    item.setSignUpMoneyContent("报名费未交 ");
                }

                if ("1".equals(item.getSecureFlg())) {
                    item.setSecureContent("需要保险 ");
                } else{
                    item.setSecureContent("不需要保险 ");
                }
                if (item.getTbClassType() != null) {
                    item.setClassTypeName(item.getTbClassType().getClassType());
                    if(this.classType != null
                            && !"".equals(this.classType)
                            && "全部学生".equals(fileName)) {
                        fileName = item.getClassTypeName();
                    }
                }
                Student newItem = new Student();
                BeanUtils.copyProperties(item, newItem);
                // 合计
                newItem.index = (index+1)+"";
                studentList.add(newItem);
                if ((index+1) % 50 ==0
                        || index ==(count-1)) {
                    students.setStudentList(studentList);
                    studentsList.add(students);
                    studentList = new ArrayList<Student>();
                    students = new Students();
                }
                index++;
            }
        } else {
            return "noStudentInfo";
        }

        Map<String, List<Students>> beans = new HashMap<String, List<Students>>();
        beans.put("students", studentsList);

        String filePath=HSConstants.ROOT_PATH+HSConstants.FILE_PATH+"\\output\\"+fileName+".xls";
        String fileTemplatePath=HSConstants.ROOT_PATH+HSConstants.FILE_PATH+"\\template\\allStudent.xls";
        outputExcel(fileTemplatePath, filePath, beans);

        setFileName(fileName+".xls");
        setFilePath(filePath);
        return "downloadAllStudentInfo";
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
    public void outputExcel(String fileTemplatePath, String destFileName, Map<String, List<Students>> beans){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Timestamp getCreatedatetimeStart() {
        return createdatetimeStart;
    }

    public void setCreatedatetimeStart(Timestamp createdatetimeStart) {
        this.createdatetimeStart = createdatetimeStart;
    }

    public Timestamp getCreatedatetimeEnd() {
        return createdatetimeEnd;
    }

    public void setCreatedatetimeEnd(Timestamp createdatetimeEnd) {
        this.createdatetimeEnd = createdatetimeEnd;
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
}
