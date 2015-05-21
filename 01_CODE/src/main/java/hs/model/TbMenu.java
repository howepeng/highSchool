package hs.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_menu", catalog = "highschool2015")
public class TbMenu implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private TbMenu tbMenu;
    private String text;
    private String iconCls;
    private BigDecimal seq;
    private String url;
    private Set<TbRoleMenu> tbRoleMenus = new HashSet<TbRoleMenu>(0);
    private Set<TbMenu> tbMenus = new HashSet<TbMenu>(0);

    // Constructors

    /** default constructor */
    public TbMenu() {
    }

    /** minimal constructor */
    public TbMenu(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbMenu(String id, TbMenu tbMenu, String text, String iconCls, BigDecimal seq, String url, Set<TbRoleMenu> tbRoleMenus, Set<TbMenu> tbMenus) {
        this.id = id;
        this.tbMenu = tbMenu;
        this.text = text;
        this.iconCls = iconCls;
        this.seq = seq;
        this.url = url;
        this.tbRoleMenus = tbRoleMenus;
        this.tbMenus = tbMenus;
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
    @JoinColumn(name = "pid")
    public TbMenu getTbMenu() {
        return this.tbMenu;
    }

    public void setTbMenu(TbMenu tbMenu) {
        this.tbMenu = tbMenu;
    }

    @Column(name = "text", length = 100)
    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "icon_cls", length = 20)
    public String getIconCls() {
        return this.iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    @Column(name = "seq", precision = 22, scale = 0)
    public BigDecimal getSeq() {
        return this.seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    @Column(name = "url", length = 100)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbMenu")
    public Set<TbRoleMenu> getTbRoleMenus() {
        return this.tbRoleMenus;
    }

    public void setTbRoleMenus(Set<TbRoleMenu> tbRoleMenus) {
        this.tbRoleMenus = tbRoleMenus;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbMenu")
    public Set<TbMenu> getTbMenus() {
        return this.tbMenus;
    }

    public void setTbMenus(Set<TbMenu> tbMenus) {
        this.tbMenus = tbMenus;
    }

}
