package hs.common.vo;

import java.util.List;

public class ExaminationRoomRow extends BaseBean {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 7775577637473717378L;

    private List<ExaminationRoomColumn> columns;

	public List<ExaminationRoomColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ExaminationRoomColumn> columns) {
		this.columns = columns;
	}
}
