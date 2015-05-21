package hs.service.impl;

import hs.dao.PreferentialDaoI;
import hs.model.TbPreferential;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.Preferential;
import hs.service.PreferentialServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("preferentialService")
public class PreferentialServiceImpl implements PreferentialServiceI {

    private PreferentialDaoI preferentialDao;

    @Autowired
    public void setPreferentialDao(PreferentialDaoI preferentialDao) {
        this.preferentialDao = preferentialDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        Combobox rH = new Combobox();
        rH.setId("");
        rH.setText("无");
        rl.add(rH);
        List<TbPreferential> l = preferentialDao.find("from TbPreferential");
        if (l != null && l.size() > 0) {
            for (TbPreferential t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getPreName() + ":" + t.getPreferentialFee().toString());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(Preferential preferential) {
        DataGrid j = new DataGrid();
        String hql = "FROM TbPreferential";
        if (preferential.getSort() != null) {
            hql += " ORDER BY " + preferential.getSort() + " " + preferential.getOrder();
        }
        j.setRows(preferentialDao.find(hql, preferential.getPage(), preferential.getRows()));
        j.setTotal(preferentialDao.count("SELECT count(*) FROM TbPreferential"));
        return j;
    }

    @Override
    public void add(Preferential preferential) {
        TbPreferential tbPreferential = new TbPreferential();
        BeanUtils.copyProperties(preferential, tbPreferential);
        tbPreferential.setId(UUID.randomUUID().toString());
        preferentialDao.save(tbPreferential);
    }

    @Override
    public void edit(Preferential preferential) {
        TbPreferential bPrteferential = preferentialDao.getById(TbPreferential.class, preferential.getId());
        BeanUtils.copyProperties(preferential, bPrteferential, new String[] { "id" });
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbPreferential r = preferentialDao.getById(TbPreferential.class, id.trim());
                if (r != null) {
                    preferentialDao.delete(r);
                }
            }
        }
    }

    @Override
    public Preferential getPreferential(String id) {
        Preferential preferential = new Preferential();
        TbPreferential tbPreferential = preferentialDao.getById(TbPreferential.class, id.trim());
        BeanUtils.copyProperties(tbPreferential, preferential, new String[] { "id" });
        return preferential;
    }
}
