package hs.service.impl;

import hs.dao.FinanceDaoI;
import hs.dao.StudentDaoI;
import hs.model.TbFinance;
import hs.model.TbStudent;
import hs.pageModel.DataGrid;
import hs.pageModel.Finance;
import hs.pageModel.Finances;
import hs.service.FinanceServiceI;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("financeService")
public class FinanceServiceImpl implements FinanceServiceI {

    private FinanceDaoI financeDao;

    @Autowired
    public void setFinanceDao(FinanceDaoI financeDao) {
        this.financeDao = financeDao;
    }

    private StudentDaoI studentDao;

    @Autowired
    public void setStudentDao(StudentDaoI studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public DataGrid datagridBeforePay(Finance finance) {
        DataGrid j = new DataGrid();
        List<Finance> finList = new ArrayList<Finance>();
        List<TbFinance> tbFinList = financeDao.find("FROM TbFinance t WHERE t.payflg='0'", finance.getPage(), finance.getRows());
        if (tbFinList != null && tbFinList.size() > 0) {
            for (TbFinance t : tbFinList) {
                Finance f = new Finance();
                BeanUtils.copyProperties(t, f);
                f.setPayee(t.getTbUser().getName());
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(financeDao.count("SELECT count(*) FROM TbFinance t WHERE t.payflg='0'"));
        return j;
    }

    @Override
    public DataGrid datagridAfterPay(Finance finance) {
        DataGrid j = new DataGrid();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = this.addCondition(finance, params);
        List<Finance> finList = new ArrayList<Finance>();
        List<TbFinance> tbFinList = financeDao.find(hql, params, finance.getPage(), finance.getRows());
        if (tbFinList != null && tbFinList.size() > 0) {
            for (TbFinance t : tbFinList) {
                Finance f = new Finance();
                BeanUtils.copyProperties(t, f);
                f.setPayee(t.getTbUser().getName());
                if (t.getTbClassType() != null) {
                    f.setClassType(t.getTbClassType().getId());
                    f.setClassTypeName(t.getTbClassType().getClassType());
                }
                if(f.getStudyFee() == null) {
                    f.setStudyFee(new BigDecimal(0));
                 }
                 if(f.getStayFee() == null) {
                     f.setStayFee(new BigDecimal(0));
                 }
                 if(f.getSelfFee() == null) {
                     f.setSelfFee(new BigDecimal(0));
                 }
                 if(f.getScoreFee() == null) {
                     f.setScoreFee(new BigDecimal(0));
                 }
                 if(f.getSafetyFee() == null) {
                     f.setSafetyFee(new BigDecimal(0));
                 }
                 if(f.getSignFee() == null) {
                     f.setSignFee(new BigDecimal(0));
                 }
                 if(f.getPreferentialFee() == null) {
                     f.setPreferentialFee(new BigDecimal(0));
                 }
                 if(f.getCashFee() == null) {
                     f.setCashFee(new BigDecimal(0));
                 }
                 if(f.getTransferFee() == null) {
                     f.setTransferFee(new BigDecimal(0));
                 }
                 if(f.getArrearFee() == null) {
                     f.setArrearFee(new BigDecimal(0));
                 }
                 if(f.getRefundFee() == null) {
                     f.setRefundFee(new BigDecimal(0));
                 }
                 if(f.getPayAgainFee() == null) {
                     f.setPayAgainFee(new BigDecimal(0));
                 }
                 if(f.getCountPayFee() == null) {
                     f.setCountPayFee(new BigDecimal(0));
                 }
//                if (t.getStudentId() != null && !"".equals(t.getStudentId())) {
//                    TbStudent tbStudent = studentDao.getById(TbStudent.class, t.getStudentId());
//                    //f.setName(tbStudent.getName());
//                    if (tbStudent != null) {
//                        f.setIdNum(tbStudent.getIdNum());
//                    } else {
//                        continue;
//                    }
//                }
                finList.add(f);
            }
        }
        j.setRows(finList);
        j.setTotal(financeDao.count("SELECT count(*) " + hql, params));
        return j;
    }

    /**
     * 生成查询hql语句
     *
     * @param finance
     * @param params
     * @return
     */
    private String addCondition(Finance finance, Map<String, Object> params) {
        String hql = "FROM TbFinance t WHERE t.studentId <> null";
        if (finance.getPayee() != null && !finance.getPayee().trim().equals("")) {
            hql += " AND t.tbUser.name LIKE :name";
            params.put("name", "%%" + finance.getPayee() + "%%");
        }
        if (finance.getName() != null && !finance.getName().trim().equals("")) {
            hql += " AND t.name LIKE :stuName";
            params.put("stuName", "%%" + finance.getName() + "%%");
        }
        if (finance.getCreatedatetimeStart() != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String createdatetimeEnd =df.format(finance.getCreatedatetimeEnd()) + " 24:00:00";
            hql += " AND t.createdatetime>= :createdatetimeStart AND t.createdatetime<= '" + createdatetimeEnd +"'";
            params.put("createdatetimeStart", finance.getCreatedatetimeStart());
        }
        if (finance.getClassType() != null && !finance.getClassType().trim().equals("")) {
            hql += " AND t.tbClassType.id LIKE :classType";
            params.put("classType", "%%" + finance.getClassType() + "%%");
        }
        if (finance.getSort() != null) {
            hql += " ORDER BY " + finance.getSort() + " " + finance.getOrder();
        }
        return hql;
    }

    @Override
    public void crashReceipt(Finance finance) {
//		TbFinance tbFinance = financeDao.getById(TbFinance.class, finance.getId());
//		tbFinance.setPayflg("1");
//		tbFinance.setPaydatetime(new Date());

        if (finance.getCashRefundFee() != null) {
            finance.setRefundFee(finance.getRefundFee().add(finance.getCashRefundFee()));
        }
        if (finance.getTransferRefundFee() != null) {
            finance.setRefundFee(finance.getRefundFee().add(finance.getTransferRefundFee()));
        }

        TbFinance tbFinance = financeDao.getById(TbFinance.class, finance.getId());
        if (tbFinance.getStudentId() != null && !"".equals(tbFinance.getStudentId())) {
            TbStudent tbStudent = studentDao.getById(TbStudent.class, tbFinance.getStudentId());
            if(tbStudent != null) {
                if (finance.getArrearFee() != null) {
                    // 欠费  = 变更后-变更前+现在
                    BigDecimal arrearFee = finance.getArrearFee().subtract(tbFinance.getArrearFee()).add(tbStudent.getArrearFee());
                    tbStudent.setArrearFee(arrearFee);
                    if (arrearFee.compareTo(new BigDecimal(0))>0) {
                        tbStudent.setArrearflg("1");
                    } else {
                        tbStudent.setArrearflg("0");
                    }
                }

                if (finance.getRefundFee() != null) {
                    // 退款 = 变更后-变更前+现在
                    BigDecimal refundFee = finance.getRefundFee().subtract(tbFinance.getRefundFee()).add(tbStudent.getRefundFee());
                    tbStudent.setRefundFee(refundFee);
                }

                if (finance.getCountPayFee() != null) {
                    // 总计交付
                    BigDecimal countFee = finance.getCountPayFee().subtract(tbFinance.getCountPayFee()).add(tbStudent.getCountFee());
                    tbStudent.setCountFee(countFee);
                }
                studentDao.update(tbStudent);
            }
        }

        // 学费
        tbFinance.setStudyFee(finance.getStudyFee());
        // 住宿费
        tbFinance.setStayFee(finance.getStayFee());
        // 晚自习费
        tbFinance.setSelfFee(finance.getSelfFee());
        // 报名费
        tbFinance.setSignFee(finance.getSignFee());
        // 成绩单押金
        tbFinance.setScoreFee(finance.getScoreFee());
        // 保险费
        tbFinance.setSafetyFee(finance.getSafetyFee());
        // 水费
        tbFinance.setWaterFee(finance.getWaterFee());
        // 现金
        tbFinance.setCashFee(finance.getCashFee());
        // 转账
        tbFinance.setTransferFee(finance.getTransferFee());
        // 减免
        tbFinance.setPreferentialFee(finance.getPreferentialFee());
        // 欠费
        tbFinance.setArrearFee(finance.getArrearFee());
        // 退款
        tbFinance.setRefundFee(finance.getRefundFee());
        tbFinance.setCashRefundFee(finance.getCashRefundFee());
        tbFinance.setTransferRefundFee(finance.getTransferRefundFee());
        // 补交费
        tbFinance.setCashPayAgainFee(finance.getCashPayAgainFee());
        tbFinance.setTransferPayAgainFee(finance.getTransferPayAgainFee());
        if (finance.getCashPayAgainFee() != null) {
            finance.setPayAgainFee(finance.getPayAgainFee().add(finance.getCashPayAgainFee()));
        }
        if (finance.getTransferPayAgainFee() != null) {
            finance.setPayAgainFee(finance.getPayAgainFee().add(finance.getTransferPayAgainFee()));
        }
        tbFinance.setPayAgainFee(finance.getPayAgainFee());
        // 总计交付
        tbFinance.setCountPayFee(finance.getCountPayFee());
        tbFinance.setCreatedatetime(new Date());
        financeDao.update(tbFinance);


    }

    @Override
    public Map<String, List<Finances>> getReportData(String attachid) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = df.parse(attachid);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String createdatetimeStart =df.format(date) + " 00:00:00";
        String createdatetimeEnd =df.format(date) + " 24:00:00";
        String hql = "FROM TbFinance t WHERE t.createdatetime >= '"+createdatetimeStart+"' and t.createdatetime <= '"+createdatetimeEnd+"'";
        List<TbFinance> tbFinList = financeDao.find(hql);
        List<Finances> financesReport = new ArrayList<Finances>();
        Finances finances = new Finances();
        finances.setNum("1");
        List<Finance> finance = new ArrayList<Finance>();
        Map<String, List<Finances>> ret = null;
        if (tbFinList != null && tbFinList.size() > 0) {
            int count = tbFinList.size();
            for (int i=0;i<count;i++) {
                Finance f = new Finance();
                TbFinance t = tbFinList.get(i);
                BeanUtils.copyProperties(t, f);
                f.setPayee(t.getTbUser().getName());
                if (t.getTbClassType() != null) {
                    f.setClassType(t.getTbClassType().getId());
                    f.setClassTypeName(t.getTbClassType().getClassType());
                }
                if (t.getStudentId() != null && !"".equals(t.getStudentId())) {
                    TbStudent tbStudent = studentDao.getById(TbStudent.class, t.getStudentId());
                    if (tbStudent != null) {
                        f.setName(tbStudent.getName());
                        f.setIdNum(tbStudent.getIdNum());
                    } else {
                        f.setName(t.getName());
                        f.setIdNum(t.getIdNum());
                    }
                }
                BigDecimal countReport = new BigDecimal(0);
                // 学费
                if (f.getStudyFee() != null) {
                    countReport = countReport.add(f.getStudyFee());
                }
                // 住宿费
                if (f.getStayFee() != null) {
                    countReport = countReport.add(f.getStayFee());
                }
                // 晚自习费
                if (f.getSelfFee() != null) {
                    countReport = countReport.add(f.getSelfFee());
                }
                // 成绩单押金
                if (f.getScoreFee() != null) {
                    countReport = countReport.add(f.getScoreFee());
                }
                // 保险费
                if (f.getSafetyFee() != null) {
                    countReport = countReport.add(f.getSafetyFee());
                }
                // 水费
                if (f.getWaterFee() != null) {
                    countReport = countReport.add(f.getWaterFee());
                }
                // 报名费
                if (f.getSignFee() != null) {
                    countReport = countReport.add(f.getSignFee());
                }
                // 减免费用
                if (f.getPreferentialFee() != null) {
                    countReport = countReport.subtract(f.getPreferentialFee());
                }
                // 退款
                if (f.getRefundFee() != null) {
                    countReport = countReport.subtract(f.getRefundFee());
                }
                // 补交费
                if (f.getPayAgainFee() != null) {
                    countReport = countReport.add(f.getPayAgainFee());
                }
                f.setCountReport(countReport);
                f.setNum(i+1+"");
                finance.add(f);

                // 学费
                if (finances.getStudyFee() != null) {
                    if (f.getStudyFee() != null){
                        finances.setStudyFee(finances.getStudyFee().add(f.getStudyFee()));
                    }
                } else {
                    finances.setStudyFee(f.getStudyFee());
                }
                // 住宿费
                if (finances.getStayFee() !=null) {
                    if (f.getStayFee() != null){
                        finances.setStayFee(finances.getStayFee().add(f.getStayFee()));
                    }
                } else {
                    finances.setStayFee(f.getStayFee());
                }
                // 晚自习费
                if (finances.getSelfFee() !=null) {
                    if (f.getSelfFee() != null){
                        finances.setSelfFee(finances.getSelfFee().add(f.getSelfFee()));
                    }
                } else {
                    finances.setSelfFee(f.getSelfFee());
                }
                // 成绩单押金
                if (finances.getScoreFee() !=null) {
                    if (f.getScoreFee() != null){
                        finances.setScoreFee(finances.getScoreFee().add(f.getScoreFee()));
                    }
                } else {
                    finances.setScoreFee(f.getScoreFee());
                }
                // 保险费
                if (finances.getSafetyFee() !=null) {
                    if (f.getSafetyFee() != null){
                        finances.setSafetyFee(finances.getSafetyFee().add(f.getSafetyFee()));
                    }
                } else {
                    finances.setSafetyFee(f.getSafetyFee());
                }
                // 水费
                if (finances.getWaterFee() !=null) {
                    if (f.getWaterFee() != null){
                        finances.setWaterFee(finances.getWaterFee().add(f.getWaterFee()));
                    }
                } else {
                    finances.setWaterFee(f.getWaterFee());
                }
                // 报名费
                if (finances.getSignFee() !=null) {
                    if (f.getSignFee() != null){
                        finances.setWaterFee(finances.getSignFee().add(f.getSignFee()));
                    }
                } else {
                    finances.setSignFee(f.getSignFee());
                }
                // 减免费用
                if (finances.getPreferentialFee() !=null) {
                    if (f.getPreferentialFee() != null){
                        finances.setPreferentialFee(finances.getPreferentialFee().add(f.getPreferentialFee()));
                    }
                } else {
                    finances.setPreferentialFee(f.getPreferentialFee());
                }
                // 现金
                if (finances.getCashFee() !=null) {
                    if (f.getCashFee() != null){
                        finances.setCashFee(finances.getCashFee().add(f.getCashFee()));
                    }
                } else {
                    finances.setCashFee(f.getCashFee());
                }
                // 转账
                if (finances.getTransferFee() !=null) {
                    if (f.getTransferFee() != null){
                        finances.setTransferFee(finances.getTransferFee().add(f.getTransferFee()));
                    }
                } else {
                    finances.setTransferFee(f.getTransferFee());
                }
                // 退款
                if (finances.getRefundFee() !=null) {
                    if (f.getRefundFee() != null){
                        finances.setRefundFee(finances.getRefundFee().add(f.getRefundFee()));
                    }
                } else {
                    finances.setRefundFee(f.getRefundFee());
                }
                // 补交费
                if (finances.getPayAgainFee() !=null) {
                    if (f.getPayAgainFee() != null){
                        finances.setPayAgainFee(finances.getPayAgainFee().add(f.getPayAgainFee()));
                    }
                } else {
                    finances.setPayAgainFee(f.getPayAgainFee());
                }
                // 合计
                if (finances.getCountReport() !=null) {
                    if (f.getCountReport() != null){
                        finances.setCountReport(finances.getCountReport().add(f.getCountReport()));
                    }
                } else {
                    finances.setCountReport(f.getCountReport());
                }

                if ((i+1) % 20 ==0
                        || i ==(count-1)) {
                    finances.setFinance(finance);
                    financesReport.add(finances);
                    finance = new ArrayList<Finance>();
                    finances = new Finances();
                    finances.setNum(financesReport.size() + 1+"");
                }
            }
            ret = new HashMap<String, List<Finances>>();
            ret.put("financesReport", financesReport);
            return ret;
        }
        return null;
    }

    @Override
    public String deleteFinance(String attachid) {
        TbFinance tbFinance = financeDao.getById(TbFinance.class,
                attachid.trim());

        try {
            financeDao.delete(tbFinance);
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }

}
