package hs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbClassType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_class_time", catalog = "highschool")
public class TbClassTime implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String startDicId;
    private String endDicId;

    // Constructors

    /** default constructor */
    public TbClassTime() {
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

    @Column(name = "startDicId", length = 36, nullable = false)
    public String getStartDicId() {
        return this.startDicId;
    }

    public void setStartDicId(String startDicId) {
        this.startDicId = startDicId;
    }

    @Column(name = "endDicId", length = 36, nullable = false)
    public String getEndDicId() {
        return this.endDicId;
    }

    public void setEndDicId(String endDicId) {
        this.endDicId = endDicId;
    }
}
