package hs.action;

import hs.common.Property;
import hs.model.TbStudent;
import hs.pageModel.Student;
import hs.service.StudentServiceI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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
@Action(value = "downloadStudentInfoAction")
@Results({ @Result(name = "downloadStudentInfo", type = "stream", params = { "contentType", "application/octet-stream", "inputName", "inputStream", "contentDisposition", "attachment;filename=\"${downloadFileName}\"", "bufferSize", "4096" }) })
public class DownloadStudentInfoAction extends ActionSupport {
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

        TbStudent student = studentService.getInfoFileDownload(attachid);
        Student st = new Student();
        BeanUtils.copyProperties(student, st);
        if ("91".equals(student.getWlqf())) {
            st.setWlqfContent("文科");
        } else if ("95".equals(student.getWlqf())) {
            st.setWlqfContent("理科");
        } else if ("93".equals(student.getWlqf())) {
            st.setWlqfContent("艺术文科");
        } else if ("97".equals(student.getWlqf())) {
            st.setWlqfContent("艺术理科");
        } else if ("94".equals(student.getWlqf())) {
            st.setWlqfContent("体育文科");
        } else if ("98".equals(student.getWlqf())) {
            st.setWlqfContent("体育理科");
        }
        if ("0".equals(student.getStudentType())) {
            st.setStuTypeContent("复读");
        } else if ("1".equals(student.getStudentType())) {
            st.setStuTypeContent("应届");
        } else if ("2".equals(student.getStudentType())) {
            st.setStuTypeContent("往届");
        }

        if ("0".equals(student.getSex())) {
            st.setSexContent("男");
        } else if ("1".equals(student.getSex())) {
            st.setSexContent("女");
        }

        if ("0".equals(student.getSignedFlg())) {
            st.setSignedContent("未签约");
        } else if ("1".equals(student.getSignedFlg())) {
            st.setSignedContent("已签约");
        }

        if ("1".equals(student.getStayFlg())) {
            st.setStayContent("住宿");
        } else if ("0".equals(student.getStayFlg())) {
            st.setStayContent("走读");
        }

        if ("1".equals(student.getSelfstudyNightflg())) {
            st.setSelfstudyNightContent("晚自习");
        }

        if ("1".equals(student.getSelfstudyNoonflg())) {
            st.setSelfstudyNoonContent("午  休 ");
        }
        if ("1".equals(student.getSignUpMoneyFlg())) {
            st.setSignUpMoneyContent("报名费已交 ");
        } else{
            st.setSignUpMoneyContent("报名费未交 ");
        }

        if ("1".equals(student.getSecureFlg())) {
            st.setSecureContent("需要保险 ");
        } else{
            st.setSecureContent("不需要保险 ");
        }

        if(student.getTbClassType() != null){
            st.setClassTypeName(student.getTbClassType().getClassType());
        }
        if(student.getTbClassInfo() != null){
            st.setClassName(student.getTbClassInfo().getName());
        }
        if(student.getTbDormitoryInfo() != null){
            st.setDormitoryName(student.getTbDormitoryInfo().getName());
        }
        String filePath=Property.getProperty("uploadPath")+"\\output\\"+student.getName()+".xls";
        String fileTemplatePath=Property.getProperty("uploadPath")+"\\template\\template.xls";
        outputExcel(fileTemplatePath, filePath, st);

        setFileName(student.getName()+".xls");
        setFilePath(filePath);
        return "downloadStudentInfo";
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
    public void outputExcel(String fileTemplatePath, String destFileName, Student student){
        try {
            Collection<Student> staff = new HashSet<Student>();
            staff.add(student);
            Map<String, Collection<Student>> beans = new HashMap<String, Collection<Student>>();
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
//    List<int[]> dic = new ArrayList<int[]>();
//    // 考生号
//    // AAAA1
//    // j:0k:2
//    dic.add(new int[] {0,2});
//    // 姓名
//    // BBBB1
//    // j:0k:4
//    dic.add(new int[] {0,4});
//    // 科目
//    // CCCC1
//    // j:0k:6
//    dic.add(new int[] {0,6});
//    // 民族
//    // AAAA2
//    // j:1k:2
//    dic.add(new int[] {1,2});
//    // 性别
//    // BBBB2
//    // j:1k:4
//    dic.add(new int[] {1,4});
//    // 签约情况
//    // CCCC2
//    // j:1k:6
//    dic.add(new int[] {1,6});
//    // 身份证号
//    // AAAA3
//    // j:2k:2
//    dic.add(new int[] {2,2});
//    // 本人电话
//    // CCCC3
//    // j:2k:6
//    dic.add(new int[] {2,6});
//    // 家庭住址
//    // AAAA4
//    // j:3k:2
//    dic.add(new int[] {3,2});
//    // 家庭电话
//    // CCCC4
//    // j:3k:6
//    dic.add(new int[] {3,6});
//    // 父亲姓名
//    // AAAA5
//    // j:4k:2
//    dic.add(new int[] {4,2});
//    // 电话
//    // BBBB3
//    // j:4k:4
//    dic.add(new int[] {4,4});
//    // 工作单位
//    // CCCC5
//    // j:4k:6
//    dic.add(new int[] {4,6});
//    // 母亲姓名
//    // AAAA6
//    // j:5k:2
//    dic.add(new int[] {5,2});
//    // 电话
//    // BBBB4
//    // j:5k:4
//    dic.add(new int[] {5,4});
//    // 工作单位
//    // CCCC6
//    // j:5k:6
//    dic.add(new int[] {5,6});
//    // 毕业学校
//    // AAAA7
//    // j:6k:2
//    dic.add(new int[] {6,2});
//    // 语文
//    // EEEE1
//    // j:10k:1
//    dic.add(new int[] {10,1});
//    // 数学
//    // EEEE2
//    // j:10k:2
//    dic.add(new int[] {10,2});
//    // 外语
//    // EEEE3
//    // j:10k:3
//    dic.add(new int[] {10,3});
//    // 历史/物理
//    // EEEE4
//    // j:10k:4
//    dic.add(new int[] {10,4});
//    // 地理/化学
//    // EEEE5
//    // j:10k:5
//    dic.add(new int[] {10,5});
//    // 政治/生物
//    // EEEE6
//    // j:10k:6
//    dic.add(new int[] {10,6});
//    // 综合总分
//    // EEEE7
//    // j:10k:7
//    dic.add(new int[] {10,7});
//    // 总分
//    // EEEE8
//    // j:10k:8
//    dic.add(new int[] {10,8});
//    // 班级
//    // AAAA8
//    // j:11k:2
//    dic.add(new int[] {11,2});
//    // 住宿/走读
//    // BBBB5
//    // j:11k:5
//    dic.add(new int[] {11,5});
//    // 晚自习
//    // j:11k:7
//    dic.add(new int[] {11,7});
//    // 午 休
//    // j:11k:8
//    dic.add(new int[] {11,8});
//    // 学号
//    // AAAA9
//    // j:12k:2
//    dic.add(new int[] {12,2});
//    // 报名意向
//    // BBBB6
//    // j:12k:5
//    dic.add(new int[] {12,5});
//    // 备注
//    // FFFF1
//    // j:14k:1
//    dic.add(new int[] {14,1});
}
