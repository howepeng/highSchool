package hs.service;

import hs.model.TbStudent;
import hs.pageModel.SessionInfo;
import hs.pageModel.Student;

public interface StuSignupServiceI {

    public String signup(Student student, SessionInfo sessionInfo) throws Exception;

    public void upload(Student student, TbStudent tbStu);

    public Long getStudentCount();

    public String[] uploadPhoto(Student student, String path);

}
