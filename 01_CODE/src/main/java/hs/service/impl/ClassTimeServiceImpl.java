package hs.service.impl;

import hs.dao.ClassTimeDaoI;
import hs.model.TbClassTime;
import hs.pageModel.ClassTime;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.service.ClassTimeServiceI;

import java.text.SimpleDateFormat;
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

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbClassTime> l = classTimeDao.find("from TbClassTime order by startTime");
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
        String hql = "FROM TbClassTime t";
        if (classTime.getSort() != null) {
            if ("showStartTime".equals(classTime.getSort())) {
                hql += " ORDER BY t.startTime";
            } else if ("showEndTime".equals(classTime.getSort())) {
                hql += " ORDER BY t.endTime";
            } else {
                hql += " ORDER BY t." + classTime.getSort();
            }
            hql += " " + classTime.getOrder();
        }
        List<TbClassTime> classTimes = classTimeDao.find(hql, classTime.getPage(), classTime.getRows());
        List<ClassTime> times = new ArrayList<ClassTime>();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        if (classTimes != null) {
            for (TbClassTime item : classTimes) {
                ClassTime n = new ClassTime();
                BeanUtils.copyProperties(item, n);
                n.setShowStartTime(format.format(n.getStartTime()));
                n.setShowEndTime(format.format(n.getEndTime()));
                times.add(n);
            }
            j.setRows(times);
            j.setTotal(classTimeDao.count("SELECT count(*) FROM TbClassTime"));
        }
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
        BeanUtils.copyProperties(tbClassTime, classTime);
        return classTime;
    }
}
