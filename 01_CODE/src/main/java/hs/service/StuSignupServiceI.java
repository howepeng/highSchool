package hs.service;

import hs.pageModel.SessionInfo;
import hs.pageModel.Student;

public interface StuSignupServiceI {

    public String signup(Student student, SessionInfo sessionInfo);

    public void upload(Student student);

    public Long getStudentCount();

    public String[] uploadPhoto(Student student, String path);

}
