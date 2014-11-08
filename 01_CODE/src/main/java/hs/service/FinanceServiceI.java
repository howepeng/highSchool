package hs.service;

import java.util.List;
import java.util.Map;

import hs.pageModel.DataGrid;
import hs.pageModel.Finance;
import hs.pageModel.Finances;

public interface FinanceServiceI {

    public DataGrid datagridBeforePay(Finance finance);

    public DataGrid datagridAfterPay(Finance finance);

    public void crashReceipt(Finance finance);

    public Map<String, List<Finances>> getReportData(String attachid);

    public String deleteFinance(String attachid);

    public String rollbackFinance(String attachid);

}
