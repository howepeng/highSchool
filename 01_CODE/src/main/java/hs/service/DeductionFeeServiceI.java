package hs.service;

import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.DeductionFee;

import java.util.List;

public interface DeductionFeeServiceI {

    public List<Combobox> combox();

    public DataGrid datagrid(DeductionFee deductionFee);

    public void add(DeductionFee deductionFee);

    public void remove(String ids);

    public void edit(DeductionFee deductionFee);

    public DeductionFee getDeductionFee(String id);
}
