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
 * TbFile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_file", catalog = "highschool")
public class TbFile implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String fileName;
    private String fileRealname;
    private String filePath;
    private Set<TbStudent> tbStudents = new HashSet<TbStudent>(0);

    // Constructors

    /** default constructor */
    public TbFile() {
    }

    /** minimal constructor */
    public TbFile(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbFile(String id, String fileName, String fileRealname, String filePath, Set<TbStudent> tbStudents) {
        this.id = id;
        this.fileName = fileName;
        this.fileRealname = fileRealname;
        this.filePath = filePath;
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

    @Column(name = "file_name", length = 100)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "file_realname", length = 100)
    public String getFileRealname() {
        return this.fileRealname;
    }

    public void setFileRealname(String fileRealname) {
        this.fileRealname = fileRealname;
    }

    @Column(name = "file_path", length = 100)
    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbFile")
    public Set<TbStudent> getTbStudents() {
        return this.tbStudents;
    }

    public void setTbStudents(Set<TbStudent> tbStudents) {
        this.tbStudents = tbStudents;
    }

}
