package hs.common.vo;

public class MstCodeVO extends BaseVO {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 4229154317814665409L;

    private int codeId = 0;

    private String codeName = null;

    private Integer codeValue = null;

    private int codeType = 0;

    private Integer sortNo = null;

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Integer getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(Integer codeValue) {
        this.codeValue = codeValue;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }

    public Integer getSortNo() {
        return sortNo;
    }

     public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}