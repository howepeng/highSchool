package hs.service.impl;

import hs.dao.DictionaryDaoI;
import hs.dao.LogTypeDaoI;
import hs.model.TbDictionary;
import hs.model.TbLogType;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.LogType;
import hs.service.LogTypeServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logTypeService")
public class LogTypeServiceImpl implements LogTypeServiceI {

    private LogTypeDaoI logTypeDao;

    @Autowired
    public void setLogTypeDao(LogTypeDaoI logTypeDao) {
        this.logTypeDao = logTypeDao;
    }

    private DictionaryDaoI dictionaryDao;

    @Autowired
    public void setictionaryDao(DictionaryDaoI dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbLogType> l = logTypeDao.find("from TbLogType");
        if (l != null && l.size() > 0) {
            for (TbLogType t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getName());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(LogType logType) {
        DataGrid j = new DataGrid();
        List<TbLogType> logTypes = logTypeDao.find("FROM TbLogType", logType.getPage(), logType.getRows());
        j.setRows(getLogTypeName(logTypes));
        j.setTotal(logTypeDao.count("SELECT count(*) FROM TbLogType"));
        return j;
    }

    @Override
    public void add(LogType logType) {
        TbLogType tbLogType = new TbLogType();
        BeanUtils.copyProperties(logType, tbLogType);
        tbLogType.setId(UUID.randomUUID().toString());
        logTypeDao.save(tbLogType);
    }

    @Override
    public void edit(LogType logType) {
        TbLogType tbLogType = logTypeDao.getById(TbLogType.class, logType.getId());
        BeanUtils.copyProperties(logType, tbLogType, new String[] { "id" });
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbLogType r = logTypeDao.getById(TbLogType.class, id.trim());
                if (r != null) {
                    logTypeDao.delete(r);
                }
            }
        }
    }

    @Override
    public LogType getLogType(String id) {
        LogType logType = new LogType();
        TbLogType tbLogType = logTypeDao.getById(TbLogType.class, id.trim());
        BeanUtils.copyProperties(tbLogType, logType, new String[] { "id" });
        return logType;
    }

    private List<LogType> getLogTypeName(List<TbLogType> logTypes) {
        List<LogType> ret = new ArrayList<LogType>();
        if (logTypes != null && logTypes.size() > 0) {
            for (TbLogType item : logTypes) {
                LogType n = new LogType();
                BeanUtils.copyProperties(item, n);
                List<TbDictionary> start = dictionaryDao.find("FROM TbDictionary WHERE id = '"+ item.getTypeId() +"'");
                if (start != null && 1 == start.size()) {
                    n.setTypeName(start.get(0).getName());
                }
                List<TbDictionary> end = dictionaryDao.find("FROM TbDictionary WHERE id = '"+ item.getModeId() +"'");
                if (end != null && 1 == end.size()) {
                    n.setModeName(end.get(0).getName());
                }
                ret.add(n);
            }
        }
        return ret;
    }
}
