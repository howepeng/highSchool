package hs.model;

import java.util.Date;
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
 * TbUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_user", catalog = "highschool")
public class TbUser implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String name;
    private String password;
    private Date createdatetime;
    private Date modifydatetime;
    private Set<TbUserRole> tbUserRoles = new HashSet<TbUserRole>(0);

    // Constructors

    /** default constructor */
    public TbUser() {
    }

    /** minimal constructor */
    public TbUser(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbUser(String id, String username, String name, String password, Date createdatetime, Date modifydatetime, Set<TbUserRole> tbUserRoles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.createdatetime = createdatetime;
        this.modifydatetime = modifydatetime;
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

    @Column(name = "username", length = 100)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password", length = 100)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "createdatetime", length = 19)
    public Date getCreatedatetime() {
        return this.createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    @Column(name = "modifydatetime", length = 19)
    public Date getModifydatetime() {
        return this.modifydatetime;
    }

    public void setModifydatetime(Date modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbUser")
    public Set<TbUserRole> getTbUserRoles() {
        return this.tbUserRoles;
    }

    public void setTbUserRoles(Set<TbUserRole> tbUserRoles) {
        this.tbUserRoles = tbUserRoles;
    }

}
