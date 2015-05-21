package hs.common.vo;

import java.util.List;

public class ChartData {
    private List<String> grpLst;
    private List<YData> dataLst;
    /**
     * @return the grpLst
     */
    public List<String> getGrpLst() {
        return grpLst;
    }
    /**
     * @param grpLst the grpLst to set
     */
    public void setGrpLst(List<String> grpLst) {
        this.grpLst = grpLst;
    }
    /**
     * @return the dataLst
     */
    public List<YData> getDataLst() {
        return dataLst;
    }
    /**
     * @param dataLst the dataLst to set
     */
    public void setDataLst(List<YData> dataLst) {
        this.dataLst = dataLst;
    }
}
