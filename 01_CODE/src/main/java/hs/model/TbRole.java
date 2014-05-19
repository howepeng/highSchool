package hs.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_role", catalog = "highschool")
public class TbRole implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String detail;
    private String name;
    private Set<TbRoleMenu> tbRoleMenus = new HashSet<TbRoleMenu>(0);
    private Set<TbUserRole> tbUserRoles = new HashSet<TbUserRole>(0);

    // Constructors

    /** default constructor */
    public TbRole() {
    }

    /** minimal constructor */
    public TbRole(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbRole(String id, String detail, String name, Set<TbRoleMenu> tbRoleMenus, Set<TbUserRole> tbUserRoles) {
        this.id = id;
        this.detail = detail;
        this.name = name;
        this.tbRoleMenus = tbRoleMenus;
        this.tbUserRoles = tbUserRoles;
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

    @Column(name = "detail", length = 100)
    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column(name = "name", length = 20)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbRole")
    public Set<TbRoleMenu> getTbRoleMenus() {
        return this.tbRoleMenus;
    }

    public void setTbRoleMenus(Set<TbRoleMenu> tbRoleMenus) {
        this.tbRoleMenus = tbRoleMenus;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbRole")
    public Set<TbUserRole> getTbUserRoles() {
        return this.tbUserRoles;
    }

    public void setTbUserRoles(Set<TbUserRole> tbUserRoles) {
        this.tbUserRoles = tbUserRoles;
    }

}
