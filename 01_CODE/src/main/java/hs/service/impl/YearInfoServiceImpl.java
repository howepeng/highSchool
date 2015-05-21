package hs.service.impl;

import hs.dao.DictionaryDaoI;
import hs.dao.YearInfoDaoI;
import hs.model.TbDictionary;
import hs.model.TbYearInfo;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.YearInfo;
import hs.service.YearInfoServiceI;
import hs.util.HSConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("yearInfoService")
public class YearInfoServiceImpl implements YearInfoServiceI {

    private YearInfoDaoI yearInfoDao;

    @Autowired
    public void setYearInfoDao(YearInfoDaoI yearInfoDao) {
        this.yearInfoDao = yearInfoDao;
    }

    private DictionaryDaoI dictionaryDao;

    @Autowired
    public void setictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbYearInfo> l = yearInfoDao.find("from TbYearInfo");
        if (l != null && l.size() > 0) {
            for (TbYearInfo t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getName());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(YearInfo yearInfo) {
        DataGrid j = new DataGrid();
        String hql = "FROM TbYearInfo t";
        if (yearInfo.getSort() != null) {
            if ("showIsDefault".equals(yearInfo.getSort())) {
                hql += " ORDER BY t.isDefault";
            } else {
                hql += " ORDER BY t." + yearInfo.getSort();
            }
            hql += " " + yearInfo.getOrder();
        }
        List<TbYearInfo> years = yearInfoDao.find(hql, yearInfo.getPage(), yearInfo.getRows());
        j.setRows(getYearInfo(years));
        j.setTotal(yearInfoDao.count("SELECT count(*) FROM TbYearInfo"));
        return j;
    }

    @Override
    public void add(YearInfo YearInfo) {
        TbYearInfo TbYearInfo = new TbYearInfo();
        BeanUtils.copyProperties(YearInfo, TbYearInfo);
        TbYearInfo.setId(UUID.randomUUID().toString());
        yearInfoDao.save(TbYearInfo);
    }

    @Override
    public void edit(YearInfo YearInfo) {
        TbYearInfo TbYearInfo = yearInfoDao.getById(TbYearInfo.class, YearInfo.getId());
        BeanUtils.copyProperties(YearInfo, TbYearInfo, new String[] { "id" });
    }

    @Override
    public boolean remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbYearInfo r = yearInfoDao.getById(TbYearInfo.class, id.trim());
                if (r != null) {
                    if (HSConstants.DIC_IS_DEFAULT.equals(r.getIsDefault() + "")) {
                        return false;
                    } else {
                        yearInfoDao.delete(r);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public YearInfo getYearInfo(String id) {
        YearInfo yearInfo = new YearInfo();
        TbYearInfo tbYearInfo = yearInfoDao.getById(TbYearInfo.class, id.trim());
        BeanUtils.copyProperties(tbYearInfo, yearInfo);
        return yearInfo;
    }


    private List<YearInfo> getYearInfo(List<TbYearInfo> years) {
        List<YearInfo> ret = new ArrayList<YearInfo>();
        if (years != null && years.size() > 0) {
            for (TbYearInfo item : years) {
                YearInfo n = new YearInfo();
                BeanUtils.copyProperties(item, n);
                List<TbDictionary> isDefault = dictionaryDao.find("FROM TbDictionary WHERE id = '"+ item.getIsDefault() +"'");
                if (isDefault != null && 1 ==isDefault.size()) {
                    n.setShowIsDefault(isDefault.get(0).getName());
                }
                ret.add(n);
            }
        }
        return ret;
    }

    @Override
    public int setNotDefaultYear(YearInfo yearInfo) {
        String hql = "update TbYearInfo set isDefault = '105002' where isDefault = '105001'";
        return yearInfoDao.excuteHQL(hql);
    }

    @Override
    public Long getDefaultYearCount() {
        return yearInfoDao.count("SELECT count(*) FROM TbYearInfo t where t.isDefault='105001'");
    }

    @Override
    public TbYearInfo getDefaultYear() {
        String hql = "FROM TbYearInfo t where t.isDefault='105001'";
        List<TbYearInfo> years = yearInfoDao.find(hql);
        if (years != null && 1 == years.size()) {
            return years.get(0);
        }
        return null;
    }
}
