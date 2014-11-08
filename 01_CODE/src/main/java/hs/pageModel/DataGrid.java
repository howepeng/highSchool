package hs.pageModel;

import java.util.ArrayList;
import java.util.List;

public class DataGrid {

    private Long total = 0L;
    @SuppressWarnings("rawtypes")
    private List rows = new ArrayList();

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @SuppressWarnings("rawtypes")
    public List getRows() {
        return rows;
    }

    @SuppressWarnings("rawtypes")
    public void setRows(List rows) {
        this.rows = rows;
    }

}
