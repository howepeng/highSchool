package hs.action;

import hs.pageModel.ExaminationRoom;
import hs.pageModel.Json;
import hs.pageModel.SessionInfo;
import hs.service.ExaminationRoomServiceI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "examinationRoomAction")
public class ExaminationRoomAction extends BaseAction implements ModelDriven<ExaminationRoom> {

    ExaminationRoom examinationRoom = new ExaminationRoom();

    @Override
    public ExaminationRoom getModel() {
        return examinationRoom;
    }

    private ExaminationRoomServiceI examinationRoomService;

    @Autowired
    public void setExaminationRoomService(ExaminationRoomServiceI examinationRoomService) {
        this.examinationRoomService = examinationRoomService;
    }

    public void datagrid(){
        SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
        super.writeJson(examinationRoomService.datagrid(examinationRoom, sessionInfo));
    }

    public void remove(){
      Json json = new Json();
      try {
          examinationRoomService.remove(examinationRoom.getIds());
          json.setSuccess(true);
          json.setMsg("删除成功");
      } catch (Exception e) {
          e.printStackTrace();
          json.setMsg(e.getMessage());
      }
      super.writeJson(json);
    }
}
