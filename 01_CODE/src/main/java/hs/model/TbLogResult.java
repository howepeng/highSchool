package hs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbClassType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_log_result", catalog = "highschool2015")
public class TbLogResult implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;

    // Constructors

    /** default constructor */
    public TbLogResult() {
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

    @Column(name = "name", length = 100, nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
