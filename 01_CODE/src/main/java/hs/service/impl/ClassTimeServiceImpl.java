package hs.service.impl;

import hs.dao.ClassTimeDaoI;
import hs.dao.DictionaryDaoI;
import hs.model.TbClassTime;
import hs.model.TbDictionary;
import hs.pageModel.ClassTime;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.service.ClassTimeServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classTimeService")
public class ClassTimeServiceImpl implements ClassTimeServiceI {

    private ClassTimeDaoI classTimeDao;

    @Autowired
    public void setClassTimeDao(ClassTimeDaoI classTimeDao) {
        this.classTimeDao = classTimeDao;
    }

    private DictionaryDaoI dictionaryDao;

    @Autowired
    public void setictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbClassTime> l = classTimeDao.find("from TbClassTime");
        if (l != null && l.size() > 0) {
            for (TbClassTime t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getName());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(ClassTime classTime) {
        DataGrid j = new DataGrid();
        List<TbClassTime> classTimes = classTimeDao.find("FROM TbClassTime", classTime.getPage(), classTime.getRows());
        j.setRows(getClassTimeName(classTimes));
        j.setTotal(classTimeDao.count("SELECT count(*) FROM TbClassTime"));
        return j;
    }

    @Override
    public void add(ClassTime classTime) {
        TbClassTime tbClassTime = new TbClassTime();
        BeanUtils.copyProperties(classTime, tbClassTime);
        tbClassTime.setId(UUID.randomUUID().toString());
        classTimeDao.save(tbClassTime);
    }

    @Override
    public void edit(ClassTime classTime) {
        TbClassTime tbClassTime = classTimeDao.getById(TbClassTime.class, classTime.getId());
        BeanUtils.copyProperties(classTime, tbClassTime, new String[] { "id" });
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbClassTime r = classTimeDao.getById(TbClassTime.class, id.trim());
                if (r != null) {
                    classTimeDao.delete(r);
                }
            }
        }
    }

    @Override
    public ClassTime getClassTime(String id) {
        ClassTime classTime = new ClassTime();
        TbClassTime tbClassTime = classTimeDao.getById(TbClassTime.class, id.trim());
        BeanUtils.copyProperties(tbClassTime, classTime, new String[] { "id" });
        return classTime;
    }

    private List<ClassTime> getClassTimeName(List<TbClassTime> classTimes) {
        List<ClassTime> ret = new ArrayList<ClassTime>();
        if (classTimes != null && classTimes.size() > 0) {
            for (TbClassTime item : classTimes) {
                ClassTime n = new ClassTime();
                BeanUtils.copyProperties(item, n);
                List<TbDictionary> start = dictionaryDao.find("FROM TbDictionary WHERE id = '"+ item.getStartDicId() +"'");
                if (start != null && 1 == start.size()) {
                    n.setStartDicName(start.get(0).getName());
                }
                List<TbDictionary> end = dictionaryDao.find("FROM TbDictionary WHERE id = '"+ item.getEndDicId() +"'");
                if (end != null && 1 == end.size()) {
                    n.setEndDicName(end.get(0).getName());
                }
                ret.add(n);
            }
        }
        return ret;
    }
}
