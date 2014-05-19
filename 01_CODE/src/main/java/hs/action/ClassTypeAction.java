package hs.action;

import hs.pageModel.ClassType;
import hs.pageModel.Json;
import hs.service.ClassTypeServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "classTypeAction")
public class ClassTypeAction extends BaseAction implements ModelDriven<ClassType> {

    private ClassType classType = new ClassType();

    @Override
    public ClassType getModel() {
        return classType;
    }

    private ClassTypeServiceI classTypeService;

    @Autowired
    public void setClassTypeService(ClassTypeServiceI classTypeService) {
        this.classTypeService = classTypeService;
    }

    public void combox() {
        super.writeJson(classTypeService.combox());
    }

    public void datagrid(){
        super.writeJson(classTypeService.datagrid(classType));
    }

    public void add() {
        Json json = new Json();
        try {
            classTypeService.add(classType);
            json.setSuccess(true);
            json.setMsg("添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void remove(){
        Json json = new Json();
        try {
            classTypeService.remove(classType.getIds());
            json.setSuccess(true);
            json.setMsg("删除成功");
        } catch (DataIntegrityViolationException d){
            d.printStackTrace();
            json.setMsg("有使用该班级类型的学生存在，请先删除使用该班级类型的学生！");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);

    }
    public void edit(){
        Json json = new Json();
        try {
            classTypeService.edit(classType);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
    public void getClassType(){
        super.writeJson(classTypeService.getClassType(classType.getId()));
    }
}
