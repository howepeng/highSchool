package hs.action;

import hs.pageModel.Dictionary;
import hs.pageModel.Json;
import hs.service.DictionaryServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "dictionaryAction")
public class DictionaryAction extends BaseAction implements ModelDriven<Dictionary> {

    private Dictionary dictionary = new Dictionary();

    @Override
    public Dictionary getModel() {
        return dictionary;
    }

    private DictionaryServiceI dictionaryervice;

    @Autowired
    public void setDictionaryervice(DictionaryServiceI dictionaryervice) {
        this.dictionaryervice = dictionaryervice;
    }

    public void combox() {
        super.writeJson(dictionaryervice.combox(dictionary));
    }

    public void datagrid(){
        super.writeJson(dictionaryervice.datagrid(dictionary));
    }

    public void add() {
        Json json = new Json();
        try {
            dictionaryervice.add(dictionary);
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
            dictionaryervice.remove(dictionary.getIds());
            json.setSuccess(true);
            json.setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);

    }
    public void edit(){
        Json json = new Json();
        try {
            dictionaryervice.edit(dictionary);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        super.writeJson(json);
    }
}
