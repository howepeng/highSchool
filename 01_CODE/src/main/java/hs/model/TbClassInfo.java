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
 * TbClassType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_class_info", catalog = "highschool2015")
public class TbClassInfo implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private TbClassType tbClassType;
    private TbYearInfo tbYearInfo;
    private String classMode;
    private TbUser tbUser;
    private Date createdatetime;
    // Constructors

    /** default constructor */
    public TbClassInfo() {
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

    @Column(name = "name", length = 40, nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "createdatetime", length = 19)
    public Date getCreatedatetime() {
        return this.createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payee")
    public TbUser getTbUser() {
        return this.tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_type")
    public TbClassType getTbClassType() {
        return this.tbClassType;
    }

    public void setTbClassType(TbClassType tbClassType) {
        this.tbClassType = tbClassType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id")
    public TbYearInfo getTbYearInfo() {
        return tbYearInfo;
    }

    public void setTbYearInfo(TbYearInfo tbYearInfo) {
        this.tbYearInfo = tbYearInfo;
    }

    @Column(name = "class_mode", nullable = false, length = 36)
    public String getClassMode() {
        return classMode;
    }

    public void setClassMode(String classMode) {
        this.classMode = classMode;
    }
}
