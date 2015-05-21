package hs.common.vo;

import java.util.List;

public class ExaminationRoomItem {
    private String sheetName;
    private List<ExaminationRoomRow> rows;
    public String getSheetName() {
        return sheetName;
    }
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
    public List<ExaminationRoomRow> getRows() {
        return rows;
    }
    public void setRows(List<ExaminationRoomRow> rows) {
        this.rows = rows;
    }
}
