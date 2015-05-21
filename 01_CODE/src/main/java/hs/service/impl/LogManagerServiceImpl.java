package hs.service.impl;

import hs.common.dao.QueryDAO;
import hs.common.vo.SqlParameterVO;
import hs.common.vo.SqlResultVO;
import hs.dao.ClassInfoDaoI;
import hs.dao.ClassTimeDaoI;
import hs.dao.LogManagerDaoI;
import hs.dao.LogResultDaoI;
import hs.dao.LogTypeDaoI;
import hs.dao.StudentDaoI;
import hs.model.TbClassInfo;
import hs.model.TbClassTime;
import hs.model.TbLogInfo;
import hs.model.TbLogResult;
import hs.model.TbLogType;
import hs.model.TbStudent;
import hs.pageModel.AttenceInfo;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.LogInfo;
import hs.pageModel.SessionInfo;
import hs.pageModel.ShowCalendarInfo;
import hs.service.LogManagerServiceI;
import hs.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private ClassTimeDaoI classTimeDao;

    @Autowired
    public void setClassTimeDao(ClassTimeDaoI classTimeDao) {
        this.classTimeDao = classTimeDao;
    }

    private LogResultDaoI logResultDao;

    @Autowired
    public void setLogResultDao(LogResultDaoI logResultDao) {
        this.logResultDao = logResultDao;
    }

    private LogTypeDaoI logTypeDao;

    @Autowired
    public void setLogTypeDao(LogTypeDaoI logTypeDao) {
        this.logTypeDao = logTypeDao;
    }

    private StudentDaoI studentDao;

    @Autowired
    public void setStudentDao(StudentDaoI studentDao) {
        this.studentDao = studentDao;
    }
    @Autowired
    protected QueryDAO queryDAO = null;

    private ClassInfoDaoI classInfoDao;

    @Autowired
    public void setClassInfoDao(ClassInfoDaoI classInfoDao) {
        this.classInfoDao = classInfoDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbLogInfo> l = logManagerDao.find("from TbLogInfo t Where t.isDelete = 0 ");
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
    public DataGrid datagrid(LogInfo logInfo, SessionInfo sessionInfo) {
        DataGrid j = new DataGrid();
        List<LogInfo> finList = new ArrayList<LogInfo>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM TbLogInfo t Where t.isDelete = 0 ";
        hql += this.addConditionAND(logInfo, params, sessionInfo);
        List<TbLogInfo> logs = logManagerDao.find(hql, params, logInfo.getPage(), logInfo.getRows());
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
                    if(t.getTbStudent().getTbClassInfo() != null) {
                        f.setClassId(t.getTbStudent().getTbClassInfo().getId());
                        f.setClassName(t.getTbStudent().getTbClassInfo().getName());
                    }
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
        j.setTotal(logManagerDao.count("SELECT count(*) " + hql, params));
        return j;
    }

    @Override
    public int add(LogInfo logInfo) {
        TbLogInfo tbLogInfo = new TbLogInfo();
        BeanUtils.copyProperties(logInfo, tbLogInfo);
        tbLogInfo.setId(UUID.randomUUID().toString());
        TbClassTime tbClassTime = new TbClassTime();
        tbClassTime.setId(logInfo.getClassTimeId());
        tbLogInfo.setTbClassTime(tbClassTime);
        TbLogResult tbLogResult = logResultDao.getById(TbLogResult.class, logInfo.getResultId());
        tbLogInfo.setTbLogResult(tbLogResult);
        TbLogType tbLogType = logTypeDao.getById(TbLogType.class, logInfo.getTypeId());
        tbLogInfo.setTbLogType(tbLogType);
        TbStudent tbStudent = studentDao.getById(TbStudent.class, logInfo.getStudentId());
        tbLogInfo.setTbStudent(tbStudent);
        int score = tbStudent.getScore();
        // 已确认
        if ("54549307-afa4-4d03-8660-54aa30297d13".equals(tbLogResult.getId())) {
            score = tbStudent.getScore() - tbLogType.getScore();
            tbLogInfo.setRemark(tbLogInfo.getRemark() + " 扣除积分" + tbLogType.getScore());
            tbLogInfo.setScore(-1 * tbLogType.getScore());
            tbStudent.setScore(tbStudent.getScore() - tbLogType.getScore());
        // 待确认
        } else if ("c6133865-a9a5-4d86-ba25-6895613d2b53".equals(tbLogResult.getId())) {
            tbLogInfo.setRemark(tbLogInfo.getRemark() + " 确认后扣除积分" + tbLogType.getScore());
            tbLogInfo.setScore(0);
        // 小计
        } else if ("48f883d5-cb83-4cfa-a743-671b89be5d77".equals(tbLogResult.getId())) {
            int count = tbLogType.getCount();
            int subtotal = getSubTotal(logInfo);
            int a = subtotal % count;
            if ((a + 1) == count) {
                score = tbStudent.getScore() - tbLogType.getScore();
                tbLogInfo.setRemark(tbLogInfo.getRemark() + " 已累计" + count +"次,扣除积分" + tbLogType.getScore());
                tbLogInfo.setScore(-1 * tbLogType.getScore());
                tbStudent.setScore(score);
            } else {
                tbLogInfo.setRemark(tbLogInfo.getRemark() + " 累计" + count + "次后扣除积分" + tbLogType.getScore()
                        + " 已小计" + (a+1) + "次");
                tbLogInfo.setScore(0);
            }
        }
        tbLogInfo.setIsDelete(0);
        logManagerDao.save(tbLogInfo);
        if (score <= 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public int edit(LogInfo logInfo) {
        TbLogInfo tbLogInfo = logManagerDao.getById(TbLogInfo.class, logInfo.getId());
        BeanUtils.copyProperties(logInfo, tbLogInfo, new String[] { "id" });
        TbClassTime tbClassTime = classTimeDao.getById(TbClassTime.class, logInfo.getClassTimeId());
        tbLogInfo.setTbClassTime(tbClassTime);
        TbLogResult tbLogResult = logResultDao.getById(TbLogResult.class, logInfo.getResultId());
        tbLogInfo.setTbLogResult(tbLogResult);
        TbLogType tbLogType = logTypeDao.getById(TbLogType.class, logInfo.getTypeId());
        tbLogInfo.setTbLogType(tbLogType);
        TbStudent tbStudent = studentDao.getById(TbStudent.class, logInfo.getStudentId());
        tbLogInfo.setTbStudent(tbStudent);
        int score = tbStudent.getScore();
        int oldSubscore = tbLogInfo.getScore();
        if (oldSubscore != 0) {
            tbStudent.setScore(tbStudent.getScore() - oldSubscore);
            tbLogInfo.setRemark(tbLogInfo.getRemark() + " 回退积分" + oldSubscore);
        }
        // 已确认
        if ("54549307-afa4-4d03-8660-54aa30297d13".equals(tbLogResult.getId())) {
            score = tbStudent.getScore() - tbLogType.getScore();
            tbLogInfo.setRemark(tbLogInfo.getRemark() + " 扣除积分" + tbLogType.getScore());
            tbLogInfo.setScore(-1 * tbLogType.getScore());
            tbStudent.setScore(tbStudent.getScore() - tbLogType.getScore());
        // 待确认
        } else if ("c6133865-a9a5-4d86-ba25-6895613d2b53".equals(tbLogResult.getId())) {
            tbLogInfo.setRemark(tbLogInfo.getRemark() + " 确认后扣除积分" + tbLogType.getScore());
            tbLogInfo.setScore(0);
        // 小计
        } else if ("48f883d5-cb83-4cfa-a743-671b89be5d77".equals(tbLogResult.getId())) {
            int count = tbLogType.getCount();
            int subtotal = getSubTotal(logInfo);
            int a = subtotal % count;
            if ((a + 1) == count) {
                score = tbStudent.getScore() - tbLogType.getScore();
                tbLogInfo.setRemark(tbLogInfo.getRemark() + " 已累计" + count +"次,扣除积分" + tbLogType.getScore());
                tbLogInfo.setScore(-1 * tbLogType.getScore());
                tbStudent.setScore(score);
            } else {
                tbLogInfo.setRemark(tbLogInfo.getRemark() + " 累计" + count + "次后扣除积分" + tbLogType.getScore()
                        + " 已小计" + (a+1) + "次");
                tbLogInfo.setScore(0);
            }
        }
        if (score <= 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public void remove(String ids) {
        if (ids != null) {
            for (String id : ids.split(",")) {
                TbLogInfo r = logManagerDao.getById(TbLogInfo.class, id.trim());
                if (r != null) {
                    TbStudent tbStudent = studentDao.getById(TbStudent.class, r.getTbStudent().getId());
                    int oldSubscore = r.getScore();
                    if (oldSubscore != 0) {
                        tbStudent.setScore(tbStudent.getScore() - oldSubscore);
                        r.setRemark(r.getRemark() + " 回退积分" + oldSubscore);
                    }
                    r.setIsDelete(1);
                }
            }
        }
    }

    @Override
    public LogInfo getLogInfo(String id) {
        LogInfo logInfo = new LogInfo();
        TbLogInfo tbLogInfo = logManagerDao.getById(TbLogInfo.class, id.trim());
        if (tbLogInfo.getIsDelete() == 0) {
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
        }
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

    private String addConditionAND(LogInfo logInfo, Map<String, Object> params,
            SessionInfo sessionInfo) {
        String hql = " AND t.tbStudent.tbYearInfo.isDefault = '105001'";
        if (!StringUtil.isEmpty(logInfo.getStudentId())) {
            hql += " AND t.tbStudent.id = :studentId";
            params.put("studentId", logInfo.getStudentId());
        }
        if (!StringUtil.isEmpty(logInfo.getStudentId())) {
            hql += " AND t.tbStudent.id = :studentId";
            params.put("studentId", logInfo.getStudentId());
        }

        if (!StringUtil.isEmpty(logInfo.getClassId())) {
            hql += " AND t.tbStudent.tbClassInfo.id = :classId";
            params.put("classId", logInfo.getClassId());
        }

        if (sessionInfo != null && sessionInfo.isOnlyDirector()) {
            String classId = getClassIdByUserId(sessionInfo);
            hql += " AND t.tbStudent.tbClassInfo.id in (" + classId + ")";
        }

        if (!StringUtil.isEmpty(logInfo.getClassTimeId())) {
            hql += " AND t.tbClassTime.id = :classTimeId";
            params.put("classTimeId", logInfo.getClassTimeId());
        }

        if (logInfo.getDate() != null) {
            hql += " AND t.date = :date";
            params.put("date", logInfo.getDate());
        }

        if (logInfo.getStartTime() != null && logInfo.getEndTime() != null) {
            hql += " AND t.tbClassTime.startTime >= :startTime AND t.tbClassTime.endTime<= :endTime";
            params.put("startTime", logInfo.getStartTime());
            params.put("endTime", logInfo.getEndTime());
        }

        if (!StringUtil.isEmpty(logInfo.getResultId())) {
            hql += " AND t.tbLogResult.id = :resultId";
            params.put("resultId", logInfo.getResultId());
        }

        if (!StringUtil.isEmpty(logInfo.getTypeId())) {
            hql += " AND t.tbLogType.id = :typeId";
            params.put("typeId", logInfo.getTypeId());
        }

        if (logInfo.getSort() != null) {
            if ("typeName".equals(logInfo.getSort())) {
                hql += " ORDER BY t.tbLogType.name " + logInfo.getOrder();
            } else if ("className".equals(logInfo.getSort())) {
                hql += " ORDER BY t.tbStudent.tbClassInfo.name " + logInfo.getOrder();
            } else if ("studentName".equals(logInfo.getSort())) {
                hql += " ORDER BY t.tbStudent.name " + logInfo.getOrder();
            } else if ("classTimeName".equals(logInfo.getSort())) {
                hql += " ORDER BY t.tbClassTime.name " + logInfo.getOrder();
            } else if ("showDate".equals(logInfo.getSort())) {
                hql += " ORDER BY t.date " + logInfo.getOrder();
            } else if ("showStartTime".equals(logInfo.getSort())) {
                hql += " ORDER BY t.startTime " + logInfo.getOrder();
            } else if ("showEndTime".equals(logInfo.getSort())) {
                hql += " ORDER BY t.endTime " + logInfo.getOrder();
            } else if ("resultName".equals(logInfo.getSort())) {
                hql += " ORDER BY t.tbLogResult.name " + logInfo.getOrder();
            } else {
                hql += " ORDER BY " + logInfo.getSort() + " " + logInfo.getOrder();
            }

        }
        return hql;
    }

    private String getClassIdByUserId(SessionInfo sessionInfo) {
        String ret = "";
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from TbClassInfo t  where 1=1 ";
        hql += " AND t.tbUser.id = :userId";
        params.put("userId", sessionInfo.getId());
        hql += " order by t.createdatetime desc";
        List<TbClassInfo> l = classInfoDao.find(hql, params);
        if (l != null && l.size() > 0) {
            for (TbClassInfo t : l) {
                ret = "'" + t.getId() + "',";
            }
        }
        if (ret != null && 1 < ret.length()) {
            ret = ret.substring(0, ret.length() - 1);
        }
        return ret;
    }

    private int getSubTotal(LogInfo logInfo) {
        SqlParameterVO param = new SqlParameterVO();
        param.putVarchar("logTypeId", logInfo.getTypeId());
        SqlResultVO  ret = queryDAO.queryForObject("com.getLogSubtotal", param);
        int subtotal = ret.getInteger("subtotal");
        return subtotal;
    }

    @Override
    public int getStudentCountByAttence(AttenceInfo attenceInfo) {
        SqlParameterVO param = new SqlParameterVO();
        param.putVarchar("classId", attenceInfo.getClassId());
        param.putVarchar("date", attenceInfo.getDate());
        param.putVarchar("classTimeId", attenceInfo.getClassTimeId());
        SqlResultVO  ret = queryDAO.queryForObject("com.getStudentCountByAttence", param);
        int subtotal = ret.getInteger("sCount");
        return subtotal;
    }

    @Override
    public int getStudentCountByDelay(AttenceInfo attenceInfo) {
        SqlParameterVO param = new SqlParameterVO();
        param.putVarchar("classId", attenceInfo.getClassId());
        param.putVarchar("date", attenceInfo.getDate());
        param.putVarchar("classTimeId", attenceInfo.getClassTimeId());
        SqlResultVO  ret = queryDAO.queryForObject("com.getStudentCountByDelay", param);
        int subtotal = ret.getInteger("sCount");
        return subtotal;
    }
}
