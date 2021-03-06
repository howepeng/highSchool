package hs.service;

import java.util.List;

import hs.model.TbStudent;
import hs.model.TbUser;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;
import hs.pageModel.StudentInfoHistory;

public interface StudentServiceI {

    public DataGrid datagridBeforePay(Student student, SessionInfo sessionInfo);

    public DataGrid datagridAfterPay(Student student, SessionInfo sessionInfo);

    public void feePay(Student student, SessionInfo sessionInfo);

    public void arrearsPay(Student student, SessionInfo sessionInfo);

    public DataGrid datagridStudent(Student student, SessionInfo sessionInfo);

    public DataGrid datagridStudentInfoHistory(StudentInfoHistory studentInfoHistory);

    public TbStudent fileDownload(String attachid);

    public TbStudent getInfoFileDownload(String attachid);

    public String deleteStudent(String attachid, TbUser tbUser);

    public TbStudent idFileDownload(String attachid);

    public TbStudent reportFileDownload(String attachid);

    public DataGrid datagridReturnPay(Student student, SessionInfo sessionInfo);

    public void refunPay(Student student, SessionInfo sessionInfo);

    public List<Student> getStudentInfo(Student student, SessionInfo sessionInfo);

    public List<Combobox> combox(Student student, SessionInfo sessionInfo);

    public String updateYearInfo(Student student, SessionInfo sessionInfo);
}
