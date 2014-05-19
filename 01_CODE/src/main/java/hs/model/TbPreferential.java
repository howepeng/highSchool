package hs.model;

import java.math.BigDecimal;
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
 * TbPreferential entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_preferential", catalog = "highschool")
public class TbPreferential implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String preName;
    private BigDecimal preferentialFee;
    private Set<TbStudent> tbStudents = new HashSet<TbStudent>(0);

    // Constructors

    /** default constructor */
    public TbPreferential() {
    }

    /** minimal constructor */
    public TbPreferential(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbPreferential(String id, String preName, BigDecimal preferentialFee, Set<TbStudent> tbStudents) {
        this.id = id;
        this.preName = preName;
        this.preferentialFee = preferentialFee;
        this.tbStudents = tbStudents;
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

    @Column(name = "pre_name", length = 40)
    public String getPreName() {
        return this.preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    @Column(name = "preferential_fee", precision = 22, scale = 0)
    public BigDecimal getPreferentialFee() {
        return this.preferentialFee;
    }

    public void setPreferentialFee(BigDecimal preferentialFee) {
        this.preferentialFee = preferentialFee;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbPreferential")
    public Set<TbStudent> getTbStudents() {
        return this.tbStudents;
    }

    public void setTbStudents(Set<TbStudent> tbStudents) {
        this.tbStudents = tbStudents;
    }

}
