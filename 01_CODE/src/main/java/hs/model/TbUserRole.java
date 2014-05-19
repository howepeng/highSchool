package hs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_user_role", catalog = "highschool")
public class TbUserRole implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private TbRole tbRole;
    private TbUser tbUser;

    // Constructors

    /** default constructor */
    public TbUserRole() {
    }

    /** minimal constructor */
    public TbUserRole(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbUserRole(String id, TbRole tbRole, TbUser tbUser) {
        this.id = id;
        this.tbRole = tbRole;
        this.tbUser = tbUser;
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
    @JoinColumn(name = "rid")
    public TbRole getTbRole() {
        return this.tbRole;
    }

    public void setTbRole(TbRole tbRole) {
        this.tbRole = tbRole;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    public TbUser getTbUser() {
        return this.tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }

}
