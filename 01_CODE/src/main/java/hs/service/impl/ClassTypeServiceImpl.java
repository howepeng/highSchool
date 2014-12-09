package hs.service.impl;

import hs.dao.ClassTypeDaoI;
import hs.model.TbClassType;
import hs.pageModel.ClassType;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.service.ClassTypeServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classTypeService")
public class ClassTypeServiceImpl implements ClassTypeServiceI {

    private ClassTypeDaoI classTypeDao;

    @Autowired
    public void setClassTypeDao(ClassTypeDaoI classTypeDao) {
        this.classTypeDao = classTypeDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbClassType> l = classTypeDao.find("from TbClassType");
        if (l != null && l.size() > 0) {
            for (TbClassType t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getClassType());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(ClassType classType) {
        DataGrid j = new DataGrid();
        j.setRows(classTypeDao.find("FROM TbClassType", classType.getPage(), classType.getRows()));
        j.setTotal(classTypeDao.count("SELECT count(*) FROM TbClassType"));
        return j;
    }

    @Override
    public void add(ClassType classType) {
        TbClassType tbClassType = new TbClassType();
        BeanUtils.copyProperties(classType, tbClassType);
        tbClassType.setId(UUID.randomUUID().toString());
        tbClassType.setCountFee(tbClassType.getScoreFee().add(tbClassType.getSelfFee()).add(tbClassType.getSignFee()).add(tbClassType.getStayFee()).add(tbClassType.getStudyFee().add(tbClassType.getWaterFee())).add(tbClassType.getSafetyFee()));
        classTypeDao.save(tbClassType);
    }

    @Override
    public void edit(ClassType classType) {
        TbClassType tbClassType = classTypeDao.getById(TbClassType.class, classType.getId());
        BeanUtils.copyProperties(classType, tbClassType, new String[] { "id" });
        tbClassType.setCountFee(tbClassType.getScoreFee().add(tbClassType.getSelfFee()).add(tbClassType.getSignFee()).add(tbClassType.getStayFee()).add(tbClassType.getStudyFee().add(tbClassType.getWaterFee())).add(tbClassType.getSafetyFee()));
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbClassType r = classTypeDao.getById(TbClassType.class, id.trim());
                if (r != null) {
                    classTypeDao.delete(r);
                }
            }
        }
    }

    @Override
    public ClassType getClassType(String id) {
        ClassType classType = new ClassType();
        TbClassType tbClassType = classTypeDao.getById(TbClassType.class, id.trim());
        BeanUtils.copyProperties(tbClassType, classType);
        return classType;
    }
}
