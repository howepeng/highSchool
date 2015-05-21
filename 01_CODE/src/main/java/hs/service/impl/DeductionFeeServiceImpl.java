package hs.service.impl;

import hs.dao.DeductionFeeDaoI;
import hs.dao.DictionaryDaoI;
import hs.dao.YearInfoDaoI;
import hs.model.TbDeductionFee;
import hs.model.TbDictionary;
import hs.model.TbYearInfo;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.DeductionFee;
import hs.service.DeductionFeeServiceI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deductionFeeService")
public class DeductionFeeServiceImpl implements DeductionFeeServiceI {

    private DeductionFeeDaoI deductionFeeDao;

    @Autowired
    public void setDeductionFeeDao(DeductionFeeDaoI deductionFeeDao) {
        this.deductionFeeDao = deductionFeeDao;
    }

    private DictionaryDaoI dictionaryDao;

    @Autowired
    public void setDictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    private YearInfoDaoI yearInfoDao;

    @Autowired
    public void setYearInfoDao(YearInfoDaoI yearInfoDao) {
        this.yearInfoDao = yearInfoDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        Combobox rH = new Combobox();
        rH.setId("");
        rH.setText("无");
        rl.add(rH);
        List<TbDeductionFee> l = deductionFeeDao.find("from TbDeductionFee");
        if (l != null && l.size() > 0) {
            for (TbDeductionFee t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getName() + ":" + t.getDeductionFee().toString());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(DeductionFee deductionFee) {
        DataGrid j = new DataGrid();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbDeductionFee t";
        if (deductionFee.getSort() != null) {
            if ("professionalName".equals(deductionFee.getSort())) {
                hql += " ORDER BY t.professionalId";
            } else if ("startDateShow".equals(deductionFee.getSort())) {
                hql += " ORDER BY t.startDate";
            } else if ("endDateShow".equals(deductionFee.getSort())) {
                hql += " ORDER BY t.endDate";
            } else if ("yearInfoName".equals(deductionFee.getSort())) {
                hql += " ORDER BY t.tbYearInfo.name";
            } else if ("timeTypeShow".equals(deductionFee.getSort())) {
                hql += " ORDER BY t.timeType";
            } else if ("statusShow".equals(deductionFee.getSort())) {
                hql += " ORDER BY t.status";
            } else {
                hql += " ORDER BY t." + deductionFee.getSort();
            }
            hql += " " + deductionFee.getOrder();
        }
        List<DeductionFee> finList = getFinList(deductionFee, params, hql);
        j.setRows(finList);
        j.setTotal(deductionFeeDao.count("SELECT count(*) " + hql, params));
        return j;
    }

    @Override
    public void add(DeductionFee deductionFee) {
        TbDeductionFee tbDeductionFee = new TbDeductionFee();
        BeanUtils.copyProperties(deductionFee, tbDeductionFee);
        tbDeductionFee.setId(UUID.randomUUID().toString());
        TbYearInfo tbYearInfo = yearInfoDao.getById(TbYearInfo.class, deductionFee.getYearInfoId());
        tbDeductionFee.setTbYearInfo(tbYearInfo);
        deductionFeeDao.save(tbDeductionFee);
    }

    @Override
    public void edit(DeductionFee deductionFee) {
        TbDeductionFee tbDeductionFee = deductionFeeDao.getById(TbDeductionFee.class, deductionFee.getId());
        BeanUtils.copyProperties(deductionFee, tbDeductionFee, new String[] { "id" });
        TbYearInfo tbYearInfo = yearInfoDao.getById(TbYearInfo.class, deductionFee.getYearInfoId());
        tbDeductionFee.setTbYearInfo(tbYearInfo);
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbDeductionFee r = deductionFeeDao.getById(TbDeductionFee.class, id.trim());
                if (r != null) {
                    deductionFeeDao.delete(r);
                }
            }
        }
    }

    @Override
    public DeductionFee getDeductionFee(String id) {
        DeductionFee deductionFee = new DeductionFee();
        TbDeductionFee tbDeductionFee = deductionFeeDao.getById(TbDeductionFee.class, id.trim());
        BeanUtils.copyProperties(tbDeductionFee, deductionFee, new String[] { "id" });
        return deductionFee;
    }

    private List<DeductionFee> getFinList(DeductionFee deductionFee,
            Map<String, Object> params, String hql) {
        SimpleDateFormat formatDate = new SimpleDateFormat("YYYY-MM-dd");
        List<DeductionFee> finList = new ArrayList<DeductionFee>();
        List<TbDeductionFee> tbDeductionFeeList = deductionFeeDao.find(hql, params, deductionFee.getPage(), deductionFee.getRows());
        if (tbDeductionFeeList != null && tbDeductionFeeList.size() > 0) {
            for (TbDeductionFee t : tbDeductionFeeList) {
                DeductionFee f = new DeductionFee();
                BeanUtils.copyProperties(t, f);
                TbDictionary r = dictionaryDao.getById(TbDictionary.class, f.getTimeType());
                f.setTimeTypeShow(r.getName());
                r = dictionaryDao.getById(TbDictionary.class, f.getStatus());
                f.setStatusShow(r.getName());
                f.setYearInfoName(t.getTbYearInfo().getName());
                f.setStartDateShow(formatDate.format(t.getStartDate()));
                f.setEndDateShow(formatDate.format(t.getEndDate()));
                f.setYearInfoId(t.getTbYearInfo().getId());
                finList.add(f);
            }
        }
        return finList;
    }
}
