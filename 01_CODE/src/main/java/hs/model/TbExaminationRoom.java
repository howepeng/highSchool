package hs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbClassType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_examination_room", catalog = "highschool2015")
public class TbExaminationRoom implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String professionalId;
    private String dispatchType;
    private String row;
    private String column;
    private String name;
    private String rangeOrder;
    private String monthTimeId1;
    private String monthTimeId2;
    private String fileName;
    private TbYearInfo tbYearInfo;
    // Constructors

    /** default constructor */
    public TbExaminationRoom() {
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

    @Column(name = "professional_id", length = 6)
    public String getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }

    @Column(name = "dispatch_type", length = 6)
    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    @Column(name = "row_count", length = 6)
    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    @Column(name = "column_count", length = 6)
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Column(name = "name", length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "range_order", length = 6)
    public String getRangeOrder() {
        return rangeOrder;
    }

    public void setRangeOrder(String rangeOrder) {
        this.rangeOrder = rangeOrder;
    }

    @Column(name = "monthTime_id1", length = 36)
    public String getMonthTimeId1() {
        return monthTimeId1;
    }

    public void setMonthTimeId1(String monthTimeId1) {
        this.monthTimeId1 = monthTimeId1;
    }

    @Column(name = "monthTime_id2", length = 36)
    public String getMonthTimeId2() {
        return monthTimeId2;
    }

    public void setMonthTimeId2(String monthTimeId2) {
        this.monthTimeId2 = monthTimeId2;
    }

    @Column(name = "file_name", length = 100)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id")
    public TbYearInfo getTbYearInfo() {
        return tbYearInfo;
    }

    public void setTbYearInfo(TbYearInfo tbYearInfo) {
        this.tbYearInfo = tbYearInfo;
    }
}
