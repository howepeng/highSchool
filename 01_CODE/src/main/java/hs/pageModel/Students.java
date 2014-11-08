package hs.pageModel;

import java.util.List;

public class Students {

    private List<Student> studentList;
    private int count;
    public List<Student> getStudentList() {
        return studentList;
    }
    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
    public int getCount() {
        if(this.studentList!=null) {
            count = this.studentList.size();
            return count;
        }
        return 0;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
