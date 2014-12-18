package hs.action;

import hs.pageModel.ClassInfo;
import hs.pageModel.Json;
import hs.service.ClassInfoServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "classInfoAction")
public class ClassInfoAction extends BaseAction implements ModelDriven<ClassInfo> {

    ClassInfo classInfo = new ClassInfo();

    @Override
    public ClassInfo getModel() {
        return classInfo;
    }

    private ClassInfoServiceI classInfoService;

    @Autowired
    public void setClassInfoService(ClassInfoServiceI classInfoService) {
        this.classInfoService = classInfoService;
    }

    public void combox() {
        super.writeJson(classInfoService.combox());
    }

    public void datagrid(){
        super.writeJson(classInfoService.datagrid(classInfo));
    }

    public void datagridClassInfo(){
        super.writeJson(classInfoService.datagridClassInfo(classInfo));
    }

    public void add() {
        Json json = new Json();
        try {
            classInfoService.add(classInfo);
            json.setSuccess(true);
            json.setMsg("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }

    public void edit(){
        Json json = new Json();
        try {
            classInfoService.edit(classInfo);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
      public void remove(){
      Json json = new Json();
      try {
          classInfoService.remove(classInfo.getIds());
          json.setSuccess(true);
          json.setMsg("删除成功");
      } catch (DataIntegrityViolationException d){
          d.printStackTrace();
          json.setMsg("有使用该班级的数据存在，请先删除使用该班级的数据！");
      } catch (Exception e) {
          e.printStackTrace();
          json.setMsg(e.getMessage());
      }
      super.writeJson(json);

    }

    public void getClassTime(){
        super.writeJson(classInfoService.getClassInfo(classInfo.getId()));
    }
}
