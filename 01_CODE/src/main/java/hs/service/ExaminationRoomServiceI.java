package hs.service;

import hs.common.vo.ExaminationRoomItem;
import hs.pageModel.DataGrid;
import hs.pageModel.ExaminationRoom;
import hs.pageModel.SessionInfo;

import java.util.List;

public interface ExaminationRoomServiceI {

    public DataGrid datagrid(ExaminationRoom examinationRoom, SessionInfo sessionInfo);
    public void remove(String ids);
    public List<ExaminationRoomItem> dispatch(ExaminationRoom examinationRoom, SessionInfo sessionInfo, boolean isUpdate);

}
