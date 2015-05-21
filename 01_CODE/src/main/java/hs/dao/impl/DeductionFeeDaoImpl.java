package hs.dao.impl;

import hs.dao.DeductionFeeDaoI;
import hs.model.TbDeductionFee;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("deductionFeeDao")
public class DeductionFeeDaoImpl extends BaseDaoImpl<TbDeductionFee> implements DeductionFeeDaoI {

    private static String type = "hs.model.TbDeductionFee";

    @Override
    public List<TbDeductionFee> findByExample(TbDeductionFee t) {
        List<TbDeductionFee> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbDeductionFee> findByExample(TbDeductionFee t, int page, int rows) {
        List<TbDeductionFee> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbDeductionFee> findByExample(TbDeductionFee t, int page, int rows, String sortName, String sortOrder) {
        List<TbDeductionFee> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
