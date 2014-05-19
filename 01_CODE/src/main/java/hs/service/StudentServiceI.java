package hs.service;

import hs.model.TbStudent;
import hs.pageModel.DataGrid;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;

public interface StudentServiceI {

    public DataGrid datagridBeforePay(Student student);

    public DataGrid datagridAfterPay(Student student);

    public void feePay(Student student, SessionInfo sessionInfo);

    public void arrearsPay(Student student, SessionInfo sessionInfo);

    public DataGrid datagridStudent(Student student);

    public TbStudent fileDownload(String attachid);

    public TbStudent getInfoFileDownload(String attachid);

    public String deleteStudent(String attachid);

    public TbStudent idFileDownload(String attachid);

    public TbStudent reportFileDownload(String attachid);

    public DataGrid datagridReturnPay(Student student);

    public void refunPay(Student student, SessionInfo sessionInfo);

}
