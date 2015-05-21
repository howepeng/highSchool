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
        String hql = "FROM TbFinance t WHERE t.payflg='0'";
        if (finance.getSort() != null) {
            hql += " ORDER BY " + finance.getSort() + " " + finance.getOrder();
        }
        List<Finance> finList = new ArrayList<Finance>();
        List<TbFinance> tbFinList = financeDao.find(hql, finance.getPage(), finance.getRows());
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
                 if(f.getDeductionFee() == null) {
                     f.setDeductionFee(new BigDecimal(0));
                 }
                 if(f.getCashFee() == null) {
                     f.setCashFee(new BigDecimal(0));
                 }
                 if(f.getBankFee() == null) {
                     f.setBankFee(new BigDecimal(0));
                 }
                 if(f.getLakalaFee() == null) {
                     f.setLakalaFee(new BigDecimal(0));
                 }
                 if(f.getAliFee() == null) {
                     f.setAliFee(new BigDecimal(0));
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
                 if("0".equals(f.getCancelflg())) {
                     f.setCancelflg("未撤销");
                 } else {
                     f.setCancelflg("已撤销");
                 }
                 f.setPayFinishdatetime(t.getTbStudent().getPayFinishdatetime());
                 if ("0".equals(t.getTbStudent().getSex())) {
                     f.setSexContent("男");
                 } else if ("1".equals(t.getTbStudent().getSex())) {
                     f.setSexContent("女");
                 }
                 if (t.getTbStudent().getTbClassInfo() != null) {
                     f.setClassName(t.getTbStudent().getTbClassInfo().getName());
                 } else {
                     f.setClassName("");
                 }
                 f.setPayFinishFee(t.getTbStudent().getCountFee());
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
        String hql = "FROM TbFinance t WHERE t.tbStudent.id <> null";
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
        if (finance.getCrashHistoryType() != null && !finance.getCrashHistoryType().trim().equals("")) {
            hql += " AND t.crashHistoryType LIKE :crashHistoryType";
            params.put("crashHistoryType", "%%" + finance.getCrashHistoryType() + "%%");
        }
        if (finance.getCancelflg() != null && !finance.getCancelflg().trim().equals("")) {
            String cancelflg;
            if ("未撤销".equals(finance.getCancelflg())) {
                cancelflg = "0";
            } else {
                cancelflg = "1";
            }
            hql += " AND t.cancelflg = '" + cancelflg +"'";
        }
        if (finance.getSort() != null) {
            if("sexContent".equals(finance.getSort())) {
                hql += " ORDER BY t.tbStudent.sex " + finance.getOrder();
            } else if("classTypeName".equals(finance.getSort())) {
                hql += " ORDER BY t.tbClassType.classType " + finance.getOrder();
            } else if("className".equals(finance.getSort())) {
                hql += " ORDER BY t.tbStudent.tbClassInfo.name " + finance.getOrder();
            } else {
                hql += " ORDER BY " + finance.getSort() + " " + finance.getOrder();
            }
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
        if (finance.getBankRefundFee() != null) {
            finance.setRefundFee(finance.getRefundFee().add(finance.getBankRefundFee()));
        }
        if (finance.getLakalaRefundFee() != null) {
            finance.setRefundFee(finance.getRefundFee().add(finance.getLakalaRefundFee()));
        }
        if (finance.getAliRefundFee() != null) {
            finance.setRefundFee(finance.getRefundFee().add(finance.getAliRefundFee()));
        }

        TbFinance tbFinance = financeDao.getById(TbFinance.class, finance.getId());
        if (tbFinance.getTbStudent().getId() != null && !"".equals(tbFinance.getTbStudent().getId())) {
            TbStudent tbStudent = studentDao.getById(TbStudent.class, tbFinance.getTbStudent().getId());
            if(tbStudent != null) {
                if (finance.getArrearFee() != null) {
                    // 欠费  = 变更后-变更前+现在
                    BigDecimal arrearFee = finance.getArrearFee().subtract(tbFinance.getArrearFee()).add(tbStudent.getArrearFee());
                    tbStudent.setArrearFee(arrearFee);
                    if (arrearFee.compareTo(new BigDecimal(0))>0) {
                        tbStudent.setArrearflg("1");
                    } else {
                        tbStudent.setArrearflg("0");
                        tbStudent.setPayFinishdatetime(new Date());
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
        // 银行转账
        tbFinance.setBankFee(finance.getBankFee());
        // 拉卡拉pos机转账
        tbFinance.setLakalaFee(finance.getLakalaFee());
         // 支付宝转账
        tbFinance.setAliFee(finance.getAliFee());
        // 减免
        tbFinance.setPreferentialFee(finance.getPreferentialFee());
        // 扣款
        tbFinance.setDeductionFee(finance.getDeductionFee());
        // 欠费
        tbFinance.setArrearFee(finance.getArrearFee());
        // 退款
        tbFinance.setRefundFee(finance.getRefundFee());
        tbFinance.setCashRefundFee(finance.getCashRefundFee());
        tbFinance.setBankRefundFee(finance.getBankRefundFee());
        tbFinance.setLakalaRefundFee(finance.getLakalaRefundFee());
        tbFinance.setAliRefundFee(finance.getAliRefundFee());
        // 补交费
        tbFinance.setCashPayAgainFee(finance.getCashPayAgainFee());
        tbFinance.setBankPayAgainFee(finance.getBankPayAgainFee());
        tbFinance.setLakalaPayAgainFee(finance.getLakalaPayAgainFee());
        tbFinance.setAliPayAgainFee(finance.getAliPayAgainFee());
        if (finance.getCashPayAgainFee() != null) {
            finance.setPayAgainFee(finance.getPayAgainFee().add(finance.getCashPayAgainFee()));
        }
        if (finance.getBankPayAgainFee() != null) {
            finance.setPayAgainFee(finance.getPayAgainFee().add(finance.getBankPayAgainFee()));
        }
        if (finance.getLakalaPayAgainFee() != null) {
            finance.setPayAgainFee(finance.getPayAgainFee().add(finance.getLakalaPayAgainFee()));
        }
        if (finance.getAliPayAgainFee() != null) {
            finance.setPayAgainFee(finance.getPayAgainFee().add(finance.getAliPayAgainFee()));
        }
        tbFinance.setPayAgainFee(finance.getPayAgainFee());
        // 总计交付
        tbFinance.setCountPayFee(finance.getCountPayFee());
        tbFinance.setCreatedatetime(new Date());
        financeDao.update(tbFinance);
    }

    @Override
    public Map<String, List<Finances>> getReportData(String attachid) {
        List<Finance> oneDay = getOnedayData(attachid);
        List<Finances> financesReport = new ArrayList<Finances>();
        List<Finance> finance = new ArrayList<Finance>();
        Map<String, List<Finances>> ret = null;
        // 计算这一天的历史信息依据20条为单位做合计
        Finances finances = new Finances();
        finances.setNum("1");
        if (oneDay != null && oneDay.size() > 0) {
            int count = oneDay.size();
            for (int i=0;i<count;i++) {
                Finance f = oneDay.get(i);
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
                // 扣款
                if (f.getDeductionFee() != null) {
                    countReport = countReport.subtract(f.getDeductionFee());
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
                        finances.setSignFee(finances.getSignFee().add(f.getSignFee()));
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
                // 按时扣费
                if (finances.getDeductionFee() !=null) {
                    if (f.getDeductionFee() != null){
                        finances.setDeductionFee(finances.getDeductionFee().add(f.getDeductionFee()));
                    }
                } else {
                    finances.setDeductionFee(f.getDeductionFee());
                }
                // 现金
                if (finances.getCashFee() !=null) {
                    if (f.getCashFee() != null){
                        finances.setCashFee(finances.getCashFee().add(f.getCashFee()));
                    }
                } else {
                    finances.setCashFee(f.getCashFee());
                }
                // 银行转账
                if (finances.getBankFee() !=null) {
                    if (f.getBankFee() != null){
                        finances.setBankFee(finances.getBankFee().add(f.getBankFee()));
                    }
                } else {
                    finances.setBankFee(f.getBankFee());
                }
                // 拉卡拉pos机转账
                if (finances.getLakalaFee() !=null) {
                    if (f.getLakalaFee() != null){
                        finances.setLakalaFee(finances.getLakalaFee().add(f.getLakalaFee()));
                    }
                } else {
                    finances.setLakalaFee(f.getLakalaFee());
                }
                // 支付宝转账
                if (finances.getAliFee() !=null) {
                    if (f.getAliFee() != null){
                        finances.setAliFee(finances.getAliFee().add(f.getAliFee()));
                    }
                } else {
                    finances.setAliFee(f.getAliFee());
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

    private List<Finance> getOnedayData(String attachid) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = df.parse(attachid);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String createdatetimeStart =df.format(date) + " 00:00:00";
        String createdatetimeEnd =df.format(date) + " 24:00:00";
        //取得期间内没有撤销的记录
        String hql = "FROM TbFinance t WHERE t.createdatetime >= '"+createdatetimeStart+"' and t.createdatetime <= '"+createdatetimeEnd+"' and t.cancelflg = '0' order by t.createdatetime desc";
        List<TbFinance> tbFinList = financeDao.find(hql);
        Map<String, List<Finance>> oneStudent = new HashMap<String, List<Finance>>();
        // 把一个学生的历史都收集起来
        if (tbFinList != null && tbFinList.size() > 0) {
            int count = tbFinList.size();
            for (int i=0;i<count;i++) {
                Finance f = new Finance();
                TbFinance t = tbFinList.get(i);
                String stuId = t.getTbStudent().getId();
                BeanUtils.copyProperties(t, f);
                f.setPayee(t.getTbUser().getName());
                if (t.getTbClassType() != null) {
                    f.setClassType(t.getTbClassType().getId());
                    f.setClassTypeName(t.getTbClassType().getClassType());
                }
                if (stuId != null && !"".equals(stuId)) {
                    TbStudent tbStudent = t.getTbStudent();
                    if (tbStudent != null) {
                        f.setName(tbStudent.getName());
                        f.setIdNum(tbStudent.getIdNum());
                        f.setPayFinishdatetime(tbStudent.getPayFinishdatetime());
                        if ("0".equals(tbStudent.getSex())) {
                            f.setSexContent("男");
                        } else if ("1".equals(tbStudent.getSex())) {
                            f.setSexContent("女");
                        }
                        if (tbStudent.getTbClassInfo() != null) {
                            f.setClassName(tbStudent.getTbClassInfo().getName());
                        } else {
                            f.setClassName("");
                        }
                        f.setPayFinishFee(tbStudent.getCountFee());
                    } else {
                        f.setName(t.getName());
                        f.setIdNum(t.getIdNum());
                    }
                }
                if(oneStudent.containsKey(stuId)) {
                    List<Finance> stuList = oneStudent.get(stuId);
                    stuList.add(f);
                } else {
                    List<Finance> stuList = new ArrayList<Finance>();
                    stuList.add(f);
                    oneStudent.put(stuId, stuList);
                }
            }
        }
        List<Finance> oneDay = new ArrayList<Finance>();
        Object s[] = oneStudent.keySet().toArray();
        int sutdentCount = oneStudent.size();
        for(int i = 0; i < sutdentCount; i++) {
            List<Finance> finances = oneStudent.get(s[i]);
            Finance oneStudentFinance = new Finance();
            int financesCount = finances.size();
            for(int j = 0; j < financesCount; j++) {
                Finance f = finances.get(j);
                oneStudentFinance.setName(f.getName());
                oneStudentFinance.setIdNum(f.getIdNum());
                oneStudentFinance.setClassTypeName(f.getClassTypeName());
                oneStudentFinance.setSexContent(f.getSexContent());
                oneStudentFinance.setClassName(f.getClassName());
                oneStudentFinance.setPayFinishdatetime(f.getPayFinishdatetime());
                oneStudentFinance.setPayFinishFee(f.getPayFinishFee());
                BigDecimal countReport = new BigDecimal(0);
                // 学费
                if (f.getStudyFee() != null) {
                    countReport = countReport.add(f.getStudyFee());
                    oneStudentFinance.setStudyFee(oneStudentFinance.getStudyFee().add(f.getStudyFee()));
                }
                // 住宿费
                if (f.getStayFee() != null) {
                    countReport = countReport.add(f.getStayFee());
                    oneStudentFinance.setStayFee(oneStudentFinance.getStayFee().add(f.getStayFee()));
                }
                // 晚自习费
                if (f.getSelfFee() != null) {
                    countReport = countReport.add(f.getSelfFee());
                    oneStudentFinance.setSelfFee(oneStudentFinance.getSelfFee().add(f.getSelfFee()));
                }
                // 成绩单押金
                if (f.getScoreFee() != null) {
                    countReport = countReport.add(f.getScoreFee());
                    oneStudentFinance.setScoreFee(oneStudentFinance.getScoreFee().add(f.getScoreFee()));
                }
                // 保险费
                if (f.getSafetyFee() != null) {
                    countReport = countReport.add(f.getSafetyFee());
                    oneStudentFinance.setSafetyFee(oneStudentFinance.getSafetyFee().add(f.getSafetyFee()));
                }
                // 水费
                if (f.getWaterFee() != null) {
                    countReport = countReport.add(f.getWaterFee());
                    oneStudentFinance.setWaterFee(oneStudentFinance.getWaterFee().add(f.getWaterFee()));
                }
                // 报名费
                if (f.getSignFee() != null) {
                    countReport = countReport.add(f.getSignFee());
                    oneStudentFinance.setSignFee(oneStudentFinance.getSignFee().add(f.getSignFee()));
                }
                // 减免费用
                if (f.getPreferentialFee() != null) {
                    countReport = countReport.subtract(f.getPreferentialFee());
                    oneStudentFinance.setPreferentialFee(oneStudentFinance.getPreferentialFee().add(f.getPreferentialFee()));
                }
                // 按时扣费
                if (f.getDeductionFee() != null) {
                    countReport = countReport.subtract(f.getDeductionFee());
                    oneStudentFinance.setDeductionFee(oneStudentFinance.getDeductionFee().add(f.getDeductionFee()));
                }
                // 退款
                if (f.getRefundFee() != null) {
                    countReport = countReport.subtract(f.getRefundFee());
                    oneStudentFinance.setRefundFee(oneStudentFinance.getRefundFee().add(f.getRefundFee()));
                }
                // 补交费
                if (f.getPayAgainFee() != null) {
                    countReport = countReport.add(f.getPayAgainFee());
                    oneStudentFinance.setPayAgainFee(oneStudentFinance.getPayAgainFee().add(f.getPayAgainFee()));
                }
                f.setCountReport(countReport);
                oneStudentFinance.setCountReport(oneStudentFinance.getCountReport().add(f.getCountReport()));

                // 现金
                if (oneStudentFinance.getCashFee() !=null) {
                    if (f.getCashFee() != null){
                        oneStudentFinance.setCashFee(oneStudentFinance.getCashFee().add(f.getCashFee()));
                    }
                } else {
                    oneStudentFinance.setCashFee(f.getCashFee());
                }
                // 银行转账
                if (oneStudentFinance.getBankFee() !=null) {
                    if (f.getBankFee() != null){
                        oneStudentFinance.setBankFee(oneStudentFinance.getBankFee().add(f.getBankFee()));
                    }
                } else {
                    oneStudentFinance.setBankFee(f.getBankFee());
                }
                // 拉卡拉pos机转账
                if (oneStudentFinance.getLakalaFee() !=null) {
                    if (f.getLakalaFee() != null){
                        oneStudentFinance.setLakalaFee(oneStudentFinance.getLakalaFee().add(f.getLakalaFee()));
                    }
                } else {
                    oneStudentFinance.setLakalaFee(f.getLakalaFee());
                }
                // 支付宝转账
                if (oneStudentFinance.getAliFee() !=null) {
                    if (f.getAliFee() != null){
                        oneStudentFinance.setAliFee(oneStudentFinance.getAliFee().add(f.getAliFee()));
                    }
                } else {
                    oneStudentFinance.setAliFee(f.getAliFee());
                }
            }
            oneDay.add(oneStudentFinance);
        }
        return oneDay;
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

    @Override
    public String rollbackFinance(String attachid) {
        try {
            TbFinance tbFinance = financeDao.getById(TbFinance.class, attachid.trim());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String createdate = df.format(tbFinance.getCreatedatetime());
            String today = df.format(new Date());
            if(df.parse(createdate).compareTo(df.parse(today)) != 0){
                return "noToday";
            }
            TbStudent tbStudent = studentDao.getById(TbStudent.class, tbFinance.getTbStudent().getId());
            if("报名费".equals(tbFinance.getCrashHistoryType())) {
                if ("1".equals(tbStudent.getSignUpMoneyFlg())) {
                    tbStudent.setSignFee(tbStudent.getSignFee().subtract(tbFinance.getSignFee()));
                    tbStudent.setCountFee(tbStudent.getCountFee().subtract(tbFinance.getCountPayFee()));
                    tbStudent.setSignUpMoneyFlg("0");
                    tbStudent.setBankSignUpMoneyFlg("0");
                    tbStudent.setLakalaSignUpMoneyFlg("0");
                    tbStudent.setAliSignUpMoneyFlg("0");
                }
            } else if ("缴费".equals(tbFinance.getCrashHistoryType())) {
                if ("1".equals(tbStudent.getSignUpMoneyFlg())) {
                    // 学费
                    if(tbStudent.getStudyFee() != null) {
                        tbStudent.setStudyFee(tbStudent.getStudyFee().subtract(tbFinance.getStudyFee()));
                    }
                    // 住宿费
                    if(tbStudent.getStayFee() != null) {
                        tbStudent.setStayFee(tbStudent.getStayFee().subtract(tbFinance.getStayFee()));
                    }
                    // 晚自习费
                    if(tbStudent.getSelfFee() != null) {
                        tbStudent.setSelfFee(tbStudent.getSelfFee().subtract(tbFinance.getSelfFee()));
                    }
                    // 报名费
                    if ("0".equals(tbStudent.getSignUpMoneyFlg())) {
                        if(tbStudent.getSignFee() != null) {
                            tbStudent.setSignFee(tbStudent.getSignFee().subtract(tbFinance.getSignFee()));
                        }
                    }
                    // 成绩单押金
                    if(tbStudent.getScoreFee() != null) {
                        tbStudent.setScoreFee(tbStudent.getScoreFee().subtract(tbFinance.getScoreFee()));
                    }
                    // 保险费
                    if(tbStudent.getSafetyFee() != null) {
                        tbStudent.setSafetyFee(tbStudent.getSafetyFee().subtract(tbFinance.getSafetyFee()));
                    }
                    // 水费
                    if(tbStudent.getWaterFee() != null) {
                        tbStudent.setWaterFee(tbStudent.getWaterFee().subtract(tbFinance.getWaterFee()));
                    }
                    // 现金
                    // 银行转账
                    // 拉卡拉pos机转账
                    // 支付宝转账
                    // 减免
                    if(tbStudent.getPreferentialFee() != null) {
                        tbStudent.setPreferentialFee(tbStudent.getPreferentialFee().subtract(tbFinance.getPreferentialFee()));
                    }
                    // 欠费 = 原来的欠费+撤销的全部金额
                    if (tbStudent.getArrearFee() != null) {
                        tbStudent.setArrearFee(tbStudent.getArrearFee().add(tbFinance.getCountPayFee()));
                    }
                    // 总计交付
                    if (tbStudent.getCountFee() != null) {
                        tbStudent.setCountFee(tbStudent.getCountFee().subtract(tbFinance.getCountPayFee()));
                    }
                    // 撤销状态
                    tbFinance.setCancelflg("0");
                    tbStudent.setIsPaymentFlg("0");
                }
            } else if ("补交费".equals(tbFinance.getCrashHistoryType())) {
                // 合计 = 合计-撤销的全部金额
                if (tbStudent.getCountFee() != null) {
                    tbStudent.setCountFee(tbStudent.getCountFee().subtract(tbFinance.getCountPayFee()));
                }
                // 欠费 = 原来的欠费 + 撤销的全部金额
                if (tbStudent.getArrearFee() != null) {
                    tbStudent.setArrearFee(tbStudent.getArrearFee().add(tbFinance.getCountPayFee()));
                }
                if (tbStudent.getArrearFee().compareTo(new BigDecimal(0))>0) {
                    tbStudent.setArrearflg("1");
                } else {
                    tbStudent.setArrearflg("0");
                    tbStudent.setPayFinishdatetime(new Date());
                }
                // 现金
                // 银行转账
                // 拉卡拉pos机转账
                // 支付宝转账
                // 现金补交
                // 银行转账补交
                // 拉卡拉pos机转账补交
                // 支付宝转账补交
                // 合计交付
            } else if ("退费".equals(tbFinance.getCrashHistoryType())) {
                // 合计 = 合计+撤销的全部金额(合计金额是负的)
                if (tbStudent.getCountFee() != null) {
                    tbStudent.setCountFee(tbStudent.getCountFee().subtract(tbFinance.getCountPayFee()));
                }
                // 退款 = 原来的退款-撤销
                if (tbStudent.getRefundFee() != null) {
                    tbStudent.setRefundFee(tbStudent.getRefundFee().subtract(tbFinance.getRefundFee()));
                }
                // 欠费
                // 现金
                // 银行转账转账
                // 拉卡拉pos机转账转账
                // 支付宝转账转账
                // 现金退款
                // 银行退款
                // 拉卡拉pos机退款
                // 支付宝退款
            } else {
                return "fail";
            }
            tbFinance.setCancelflg("1");
            financeDao.update(tbFinance);
            studentDao.update(tbStudent);
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }
}
