package hs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbRoleMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_role_menu", catalog = "highschool")
public class TbRoleMenu implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private TbRole tbRole;
    private TbMenu tbMenu;

    // Constructors

    /** default constructor */
    public TbRoleMenu() {
    }

    /** minimal constructor */
    public TbRoleMenu(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbRoleMenu(String id, TbRole tbRole, TbMenu tbMenu) {
        this.id = id;
        this.tbRole = tbRole;
        this.tbMenu = tbMenu;
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
    @JoinColumn(name = "mid")
    public TbMenu getTbMenu() {
        return this.tbMenu;
    }

    public void setTbMenu(TbMenu tbMenu) {
        this.tbMenu = tbMenu;
    }

}
