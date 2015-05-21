package hs.service.impl;

import hs.dao.MonthInfoDaoI;
import hs.model.TbMonthInfo;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.MonthInfo;
import hs.service.MonthInfoServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("monthInfoService")
public class MonthInfoServiceImpl implements MonthInfoServiceI {

    private MonthInfoDaoI monthInfoDao;

    @Autowired
    public void setMonthInfoDao(MonthInfoDaoI monthInfoDao) {
        this.monthInfoDao = monthInfoDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbMonthInfo> l = monthInfoDao.find("from TbMonthInfo t order by t.value");
        if (l != null && l.size() > 0) {
            for (TbMonthInfo t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getName());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(MonthInfo monthInfo) {
        DataGrid j = new DataGrid();
        String hql = "FROM TbMonthInfo t";
        if (monthInfo.getSort() != null) {
            hql += " ORDER BY " + monthInfo.getSort() + " " + monthInfo.getOrder();
        }
        j.setRows(monthInfoDao.find(hql, monthInfo.getPage(), monthInfo.getRows()));
        j.setTotal(monthInfoDao.count("SELECT count(*) FROM TbMonthInfo"));
        return j;
    }

    @Override
    public void add(MonthInfo monthInfo) {
        TbMonthInfo tbMonthInfo = new TbMonthInfo();
        BeanUtils.copyProperties(monthInfo, tbMonthInfo);
        tbMonthInfo.setId(UUID.randomUUID().toString());
        monthInfoDao.save(tbMonthInfo);
    }

    @Override
    public void edit(MonthInfo monthInfo) {
        TbMonthInfo tbMonthInfo = monthInfoDao.getById(TbMonthInfo.class, monthInfo.getId().trim());
        BeanUtils.copyProperties(monthInfo, tbMonthInfo, new String[] { "id" });
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbMonthInfo r = monthInfoDao.getById(TbMonthInfo.class, id.trim());
                if (r != null) {
                    monthInfoDao.delete(r);
                }
            }
        }
    }

    @Override
    public MonthInfo getMonthInfo(String id) {
        MonthInfo monthInfo = new MonthInfo();
        TbMonthInfo tbMonthInfo = monthInfoDao.getById(TbMonthInfo.class, id.trim());
        BeanUtils.copyProperties(tbMonthInfo, monthInfo);
        return monthInfo;
    }
}
