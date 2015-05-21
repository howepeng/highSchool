package hs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbStudent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_student_info_history", catalog = "highschool2015")
public class TbStudentInfoHistory implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // Fields
    private String id;
    private String studentId;
    private String num;
    private String idNum;
    private String classTypeId;
    private String classTypeName;
    private TbUser tbUser;
    private String name;
    private String updateContent;
    private String updateType;
    private Date createdatetime;
    private Date updatedatetime;

    // Constructors
    /** default constructor */
    public TbStudentInfoHistory() {
    }

    /** minimal constructor */
    public TbStudentInfoHistory(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbStudentInfoHistory(String id,String studentId, String num, String idNum, String classTypeId,String classTypeName, String name, TbUser tbUser,String updateContent,Date createdatetime, Date updatedatetime, String updateType) {
        this.id = id;
        this.studentId = studentId;
        this.num = num;
        this.idNum = idNum;
        this.classTypeId = classTypeId;
        this.classTypeName = classTypeName;
        this.name = name;
        this.tbUser = tbUser;
        this.updateContent = updateContent;
        this.createdatetime = createdatetime;
        this.updatedatetime = updatedatetime;
        this.updateType = updateType;
    }

    // Property accessors
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payee")
    public TbUser getTbUser() {
        return this.tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }

    @Column(name = "name", length = 10)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "id_num", length = 30)
    public String getIdNum() {
        return this.idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Column(name = "createdatetime", length = 19)
    public Date getCreatedatetime() {
        return this.createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    @Column(name = "updatedatetime", length = 19)
    public Date getUpdatedatetime() {
        return this.updatedatetime;
    }

    public void setUpdatedatetime(Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }

    @Column(name = "num", length = 14)
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Column(name = "class_type_id", length = 36)
    public String getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(String classTypeId) {
        this.classTypeId = classTypeId;
    }

    @Column(name = "class_type_name", length = 40)
    public String getClassTypeName() {
        return classTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }

    @Column(name = "update_content", length = 2000)
    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    @Column(name = "update_type", length = 40)
    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    @Column(name = "student_id", length = 36)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
