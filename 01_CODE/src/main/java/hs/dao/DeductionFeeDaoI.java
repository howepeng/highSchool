package hs.dao;

import hs.model.TbDeductionFee;

import java.util.List;

public interface DeductionFeeDaoI extends BaseDaoI<TbDeductionFee> {

    public List<TbDeductionFee> findByExample(TbDeductionFee t);

    public List<TbDeductionFee> findByExample(TbDeductionFee t, int page, int rows);

    public List<TbDeductionFee> findByExample(TbDeductionFee t, int page, int rows, String sortName, String sortOrder);

}
