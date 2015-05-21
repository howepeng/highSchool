package hs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * tb_month_info entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_month_info", catalog = "highschool2015")
public class TbMonthInfo implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String value;

    // Constructors

    /** default constructor */
    public TbMonthInfo() {
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

    @Column(name = "name", length = 50, nullable = false)
    public String getName() {
        return this.name;
    }

    @Column(name = "value", length = 5,nullable = false)
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
