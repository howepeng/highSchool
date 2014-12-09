package hs.service.impl;

import hs.common.dao.QueryDAO;
import hs.common.vo.SqlParameterVO;
import hs.dao.LogManagerDaoI;
import hs.model.TbClassTime;
import hs.model.TbLogInfo;
import hs.model.TbLogResult;
import hs.model.TbLogType;
import hs.model.TbStudent;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.LogInfo;
import hs.pageModel.ShowCalendarInfo;
import hs.service.LogManagerServiceI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logManagerService")
public class LogManagerServiceImpl implements LogManagerServiceI {

    private LogManagerDaoI logManagerDao;

    @Autowired
    public void setLogManagerDao(LogManagerDaoI logManagerDao) {
        this.logManagerDao = logManagerDao;
    }

    @Autowired
    protected QueryDAO queryDAO = null;

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbLogInfo> l = logManagerDao.find("from TbLogInfo");
        if (l != null && l.size() > 0) {
            for (TbLogInfo t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public DataGrid datagrid(LogInfo logInfo) {
        DataGrid j = new DataGrid();
        List<LogInfo> finList = new ArrayList<LogInfo>();
        List<TbLogInfo> logs = logManagerDao.find("FROM TbLogInfo", logInfo.getPage(), logInfo.getRows());
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatDate = new SimpleDateFormat("YYYY-MM-dd");
        if (logs != null && logs.size() > 0) {
            for (TbLogInfo t : logs) {
                LogInfo f = new LogInfo();
                BeanUtils.copyProperties(t, f);
                if(t.getTbClassTime() != null) {
                    f.setClassTimeId(t.getTbClassTime().getId());
                    f.setClassTimeName(t.getTbClassTime().getName());
                }
                if(t.getTbLogType() != null) {
                    f.setTypeId(t.getTbLogType().getId());
                    f.setTypeName(t.getTbLogType().getName());
                }
                if(t.getTbStudent() != null) {
                    f.setStudentId(t.getTbStudent().getId());
                    f.setStudentName(t.getTbStudent().getName());
                }
                if(t.getTbLogResult() != null) {
                    f.setResultId(t.getTbLogResult().getId());
                    f.setResultName(t.getTbLogResult().getName());
                }
                f.setShowDate(formatDate.format(f.getDate()));
                f.setShowStartTime(formatTime.format(f.getStartTime()));
                f.setShowEndTime(formatTime.format(f.getEndTime()));
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(logManagerDao.count("SELECT count(*) FROM TbLogInfo"));
        return j;
    }

    @Override
    public void add(LogInfo logInfo) {
        TbLogInfo tbLogInfo = new TbLogInfo();
        BeanUtils.copyProperties(logInfo, tbLogInfo);
        tbLogInfo.setId(UUID.randomUUID().toString());
        TbClassTime tbClassTime = new TbClassTime();
        tbClassTime.setId(logInfo.getClassTimeId());
        tbLogInfo.setTbClassTime(tbClassTime);
        TbLogResult tbLogResult = new TbLogResult();
        tbLogResult.setId(logInfo.getResultId());
        tbLogInfo.setTbLogResult(tbLogResult);
        TbLogType tbLogType = new TbLogType();
        tbLogType.setId(logInfo.getTypeId());
        tbLogInfo.setTbLogType(tbLogType);
        TbStudent tbStudent = new TbStudent();
        tbStudent.setId(logInfo.getStudentId());
        tbLogInfo.setTbStudent(tbStudent);
        logManagerDao.save(tbLogInfo);
    }

    @Override
    public void edit(LogInfo logInfo) {
        TbLogInfo tbLogInfo = logManagerDao.getById(TbLogInfo.class, logInfo.getId());
        BeanUtils.copyProperties(logInfo, tbLogInfo, new String[] { "id" });
        logInfo.setClassTimeId(tbLogInfo.getTbClassTime().getId());
        logInfo.setClassTimeName(tbLogInfo.getTbClassTime().getName());
        logInfo.setTypeId(tbLogInfo.getTbLogType().getId());
        logInfo.setTypeName(tbLogInfo.getTbLogType().getName());
        logInfo.setResultId(tbLogInfo.getTbLogResult().getId());
        logInfo.setResultName(tbLogInfo.getTbLogResult().getName());
        logInfo.setStudentId(tbLogInfo.getTbStudent().getId());
        logInfo.setStudentName(tbLogInfo.getTbStudent().getName());
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatDate = new SimpleDateFormat("YYYY-MM-dd");
        logInfo.setShowDate(formatDate.format(logInfo.getDate()));
        logInfo.setShowStartTime(formatTime.format(logInfo.getStartTime()));
        logInfo.setShowEndTime(formatTime.format(logInfo.getEndTime()));
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbLogInfo r = logManagerDao.getById(TbLogInfo.class, id.trim());
                if (r != null) {
                    logManagerDao.delete(r);
                }
            }
        }
    }

    @Override
    public LogInfo getLogInfo(String id) {
        LogInfo logInfo = new LogInfo();
        TbLogInfo tbLogInfo = logManagerDao.getById(TbLogInfo.class, id.trim());
        BeanUtils.copyProperties(tbLogInfo, logInfo);
        logInfo.setClassTimeId(tbLogInfo.getTbClassTime().getId());
        logInfo.setClassTimeName(tbLogInfo.getTbClassTime().getName());
        logInfo.setTypeId(tbLogInfo.getTbLogType().getId());
        logInfo.setTypeName(tbLogInfo.getTbLogType().getName());
        logInfo.setResultId(tbLogInfo.getTbLogResult().getId());
        logInfo.setResultName(tbLogInfo.getTbLogResult().getName());
        logInfo.setStudentId(tbLogInfo.getTbStudent().getId());
        logInfo.setStudentName(tbLogInfo.getTbStudent().getName());
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatDate = new SimpleDateFormat("YYYY-MM-dd");
        logInfo.setShowDate(formatDate.format(logInfo.getDate()));
        logInfo.setShowStartTime(formatTime.format(logInfo.getStartTime()));
        logInfo.setShowEndTime(formatTime.format(logInfo.getEndTime()));
        return logInfo;
    }

    @Override
    public List<ShowCalendarInfo> getShowCalendar(LogInfo logInfo) {
        SqlParameterVO param = new SqlParameterVO();
        param.putVarchar("start", logInfo.getStart());
        param.putVarchar("end", logInfo.getEnd());
        List<ShowCalendarInfo> ret = queryDAO.queryForList("com.getLogCalendar", param, ShowCalendarInfo.class);
        return ret;
    }
}
