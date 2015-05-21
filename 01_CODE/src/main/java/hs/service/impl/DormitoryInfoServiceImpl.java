package hs.service.impl;

import hs.dao.DormitoryInfoDaoI;
import hs.model.TbDormitoryInfo;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.DormitoryInfo;
import hs.service.DormitoryInfoServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dormitoryInfoService")
public class DormitoryInfoServiceImpl implements DormitoryInfoServiceI {

    private DormitoryInfoDaoI dormitoryInfoDao;

    @Autowired
    public void setDormitoryDao(DormitoryInfoDaoI dormitoryInfoDao) {
        this.dormitoryInfoDao = dormitoryInfoDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbDormitoryInfo> l = dormitoryInfoDao.find("from TbDormitoryInfo");
        if (l != null && l.size() > 0) {
            for (TbDormitoryInfo t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getName());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(DormitoryInfo dormitoryInfo) {
        DataGrid j = new DataGrid();
        String hql = "FROM TbDormitoryInfo";
        if (dormitoryInfo.getSort() != null) {
            hql += " ORDER BY " + dormitoryInfo.getSort() + " " + dormitoryInfo.getOrder();
        }
        List<TbDormitoryInfo> dormitorys = dormitoryInfoDao.find(hql, dormitoryInfo.getPage(), dormitoryInfo.getRows());
        j.setRows(getDormitoryInfo(dormitorys));
        j.setTotal(dormitoryInfoDao.count("SELECT count(*) FROM TbDormitoryInfo"));
        return j;
    }

    @Override
    public void add(DormitoryInfo dormitoryInfo) {
        TbDormitoryInfo tbDormitoryInfo = new TbDormitoryInfo();
        BeanUtils.copyProperties(dormitoryInfo, tbDormitoryInfo);
        tbDormitoryInfo.setId(UUID.randomUUID().toString());
        dormitoryInfoDao.save(tbDormitoryInfo);
    }

    @Override
    public void edit(DormitoryInfo dormitoryInfo) {
        TbDormitoryInfo tbDormitoryInfo = dormitoryInfoDao.getById(TbDormitoryInfo.class, dormitoryInfo.getId());
        BeanUtils.copyProperties(dormitoryInfo, tbDormitoryInfo, new String[] { "id" });
    }

    @Override
    public boolean remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbDormitoryInfo r = dormitoryInfoDao.getById(TbDormitoryInfo.class, id.trim());
                dormitoryInfoDao.delete(r);
            }
        }
        return true;
    }

    @Override
    public DormitoryInfo getDormitoryInfo(String id) {
        DormitoryInfo dormitoryInfo = new DormitoryInfo();
        TbDormitoryInfo tbDormitoryInfo = dormitoryInfoDao.getById(TbDormitoryInfo.class, id.trim());
        BeanUtils.copyProperties(tbDormitoryInfo, dormitoryInfo);
        return dormitoryInfo;
    }

    private List<DormitoryInfo> getDormitoryInfo(List<TbDormitoryInfo> dormitorys) {
        List<DormitoryInfo> ret = new ArrayList<DormitoryInfo>();
        if (dormitorys != null && dormitorys.size() > 0) {
            for (TbDormitoryInfo item : dormitorys) {
                DormitoryInfo n = new DormitoryInfo();
                BeanUtils.copyProperties(item, n);
                ret.add(n);
            }
        }
        return ret;
    }
}
