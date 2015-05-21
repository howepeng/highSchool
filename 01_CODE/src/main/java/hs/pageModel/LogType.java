package hs.pageModel;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class LogType implements Serializable {

     /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private int score;
    private int count;
    private String typeId;
    private String typeName;
    private String modeId;
    private String modeName;
    private String remark;
    private int attence;
    private String attenceText;
    private int page;
    private int rows;
    private String sort;
    private String order;
    private String ids;//删除用 id字符串（用逗号分隔）
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPage() {
        return page;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getTypeId() {
        return typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getModeId() {
        return modeId;
    }
    public void setModeId(String modeId) {
        this.modeId = modeId;
    }
    public String getModeName() {
        return modeName;
    }
    public void setModeName(String modeName) {
        this.modeName = modeName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public String getIds() {
        return ids;
    }
    public void setIds(String ids) {
        this.ids = ids;
    }
    public int getAttence() {
        return attence;
    }
    public void setAttence(int attence) {
        this.attence = attence;
    }
    public String getAttenceText() {
        return attenceText;
    }
    public void setAttenceText(String attenceText) {
        this.attenceText = attenceText;
    }
}
