package hs.service.impl;

import hs.dao.LogResultDaoI;
import hs.model.TbLogResult;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.LogResult;
import hs.service.LogResultServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logResultService")
public class LogResultServiceImpl implements LogResultServiceI {

    private LogResultDaoI logResultDao;

    @Autowired
    public void setLogResultDao(LogResultDaoI logResultDao) {
        this.logResultDao = logResultDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbLogResult> l = logResultDao.find("from TbLogResult");
        if (l != null && l.size() > 0) {
            for (TbLogResult t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getName());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(LogResult logResult) {
        DataGrid j = new DataGrid();
        j.setRows(logResultDao.find("FROM TbLogResult", logResult.getPage(), logResult.getRows()));
        j.setTotal(logResultDao.count("SELECT count(*) FROM TbLogResult"));
        return j;
    }

    @Override
    public void add(LogResult logResult) {
        TbLogResult TbLogResult = new TbLogResult();
        BeanUtils.copyProperties(logResult, TbLogResult);
        TbLogResult.setId(UUID.randomUUID().toString());
        logResultDao.save(TbLogResult);
    }

    @Override
    public void edit(LogResult logResult) {
        TbLogResult TbLogResult = logResultDao.getById(TbLogResult.class, logResult.getId());
        BeanUtils.copyProperties(logResult, TbLogResult, new String[] { "id" });
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbLogResult r = logResultDao.getById(TbLogResult.class, id.trim());
                if (r != null) {
                    logResultDao.delete(r);
                }
            }
        }
    }

    @Override
    public LogResult getLogResult(String id) {
        LogResult logResult = new LogResult();
        TbLogResult TbLogResult = logResultDao.getById(TbLogResult.class, id.trim());
        BeanUtils.copyProperties(TbLogResult, logResult);
        return logResult;
    }
}
