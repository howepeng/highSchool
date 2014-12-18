package hs.action;

import hs.pageModel.ClassDivide;
import hs.pageModel.Json;
import hs.service.ClassDivideServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "classDivideAction")
public class ClassDivideAction extends BaseAction implements ModelDriven<ClassDivide> {

    private ClassDivide classDivide = new ClassDivide();

    @Override
    public ClassDivide getModel() {
        return classDivide;
    }

    private ClassDivideServiceI ClassDivideService;

    @Autowired
    public void setClassDivideService(ClassDivideServiceI ClassDivideService) {
        this.ClassDivideService = ClassDivideService;
    }

  public void divide() {
  Json json = new Json();
  try {
      String msg=ClassDivideService.divide(classDivide);
      json.setSuccess(true);
      json.setMsg(msg);

  } catch (Exception e) {
      e.printStackTrace();
      json.setMsg(e.getMessage());
  }
  super.writeJson(json);
}

}
