package hs.action;

import hs.common.Property;
import hs.common.vo.ExaminationRoomItem;
import hs.dao.ExaminationRoomDaoI;
import hs.model.TbExaminationRoom;
import hs.pageModel.ExaminationRoom;
import hs.pageModel.SessionInfo;
import hs.service.ExaminationRoomServiceI;
import hs.util.StringUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@Action(value = "downloadExaminationRoomAction")
@Results({ @Result(name = "downloadExaminationRoom",
                   type = "stream",
                   params = { "contentType",
                                "application/octet-stream",
                                "inputName",
                                "inputStream",
                                "contentDisposition",
                                "attachment;filename=\"${downloadFileName}\"",
                                "bufferSize",
                                "4096" }),
           @Result(name = "noExaminationRoom", location = "/error/noExaminationRoom.jsp")})
public class DownloadExaminationRoomAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String attachid;// 附件id
    private String filePath;
    private String fileName;// 初始的通过param指定的文件名属性
    private String professionalId;
    private String dispatchType;
    private String row;
    private String column;
    private String name;
    private String rangeOrder;
    private String monthTimeId1;
    private String monthTimeId2;
    private String isNew = "1";

    private ExaminationRoomServiceI examinationRoomService;

    @Autowired
    public void setExaminationRoomService(ExaminationRoomServiceI examinationRoomService) {
        this.examinationRoomService = examinationRoomService;

    }

    private ExaminationRoomDaoI examinationRoomDao;

    @Autowired
    public void setExaminationRoomDao(ExaminationRoomDaoI examinationRoomDao) {
        this.examinationRoomDao = examinationRoomDao;
    }
    public String execute() throws Exception {
        try {
            if ("0".equals(isNew)) {
                 TbExaminationRoom nItem = examinationRoomDao.getById(TbExaminationRoom.class, attachid);
                 String filePath=Property.getProperty("uploadPath")+"\\output\\"+nItem.getFileName()+".xls";
                 setFileName(nItem.getFileName()+".xls");
                 setFilePath(filePath);
                 return "downloadExaminationRoom";
            } else {
                ExaminationRoom input = new ExaminationRoom();
                input.setId(attachid);
                input.setProfessionalId(professionalId);
                input.setDispatchType(dispatchType);
                input.setRow(row);
                input.setColumn(column);
                input.setName(name);
                input.setRangeOrder(rangeOrder);
                input.setMonthTimeId1(monthTimeId1);
                input.setMonthTimeId2(monthTimeId2);
                SimpleDateFormat formatDate = new SimpleDateFormat("YYYY-MM-dd");
                String fileName = name + formatDate.format(new Date());
                input.setFileName(fileName);
                SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
                List<ExaminationRoomItem> examinationRoomList = null;
                if (StringUtil.isNotEmpty(attachid)) {
                    examinationRoomList = examinationRoomService.dispatch(input, sessionInfo, true);
                } else{
                    examinationRoomList = examinationRoomService.dispatch(input, sessionInfo, false);
                }
                if (examinationRoomList == null || examinationRoomList.size() <=0) {
                    return "noExaminationRoom";
                }
                String filePath=Property.getProperty("uploadPath")+"\\output\\"+fileName+".xls";
                String fileTemplatePath=Property.getProperty("uploadPath")+"\\template\\examinationRoom.xls";
                outputExcel(fileTemplatePath, filePath, examinationRoomList);
                setFileName(fileName+".xls");
                setFilePath(filePath);
                return "downloadExaminationRoom";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "noExaminationRoom";
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
    @SuppressWarnings("rawtypes")
    public void outputExcel(String fileTemplatePath, String destFileName, List<ExaminationRoomItem> beans){
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(fileTemplatePath));
            XLSTransformer transformer = new XLSTransformer();
            List<String> sheetNames = new ArrayList<String>();
            for (ExaminationRoomItem item : beans) {
                sheetNames.add( item.getSheetName());
            }
            //调用引擎生成excel报表
            Workbook workbook = transformer.transformMultipleSheetsList(is, beans, sheetNames, "examinationRoom", new HashMap(), 0);
            workbook.write(new FileOutputStream(destFileName));
            is.close();
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

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws UnsupportedEncodingException {
        String agent = ServletActionContext.getRequest().getHeader("User-agent");
        // 如果浏览器是IE浏览器，就得进行编码转换
        if (agent.contains("MSIE")) {
            this.name = URLEncoder.encode(name, "UTF-8");
        } else {
            this.name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        }
    }

    public String getMonthTimeId1() {
        return monthTimeId1;
    }

    public void setMonthTimeId1(String monthTimeId1) {
        this.monthTimeId1 = monthTimeId1;
    }

    public String getMonthTimeId2() {
        return monthTimeId2;
    }

    public void setMonthTimeId2(String monthTimeId2) {
        this.monthTimeId2 = monthTimeId2;
    }

    public String getRangeOrder() {
        return rangeOrder;
    }

    public void setRangeOrder(String rangeOrder) {
        this.rangeOrder = rangeOrder;
    }

    public String getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }
    public String getIsNew() {
        return isNew;
    }
    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }
}
