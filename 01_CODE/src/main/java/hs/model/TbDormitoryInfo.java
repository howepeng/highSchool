package hs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbDormitoryInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_dormitory_info", catalog = "highschool2015")
public class TbDormitoryInfo implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private int peopleCount;
    private int dormitoryCount;

    // Constructors

    /** default constructor */
    public TbDormitoryInfo() {
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

    @Column(name = "people_count", nullable = false)
    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    @Column(name = "dormitory_count", nullable = false)
    public int getDormitoryCount() {
        return dormitoryCount;
    }

    public void setDormitoryCount(int dormitoryCount) {
        this.dormitoryCount = dormitoryCount;
    }
}
