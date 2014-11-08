package hs.action;

import hs.pageModel.StudentInfoHistory;
import hs.service.StudentServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "studentInfoHistory")
public class StudentInfoHistoryAction extends BaseAction implements ModelDriven<StudentInfoHistory> {

    private StudentInfoHistory studentInfoHistory = new StudentInfoHistory();

    @Override
    public StudentInfoHistory getModel() {
        return studentInfoHistory;
    }

    private StudentServiceI studentService;

    @Autowired
    public void setStudentService(StudentServiceI studentService) {
        this.studentService = studentService;
    }

    /**
     * 学生历史信息
     */
    public void datagridStudentInfoHistory(){
        super.writeJson(studentService.datagridStudentInfoHistory(studentInfoHistory));
    }

}
