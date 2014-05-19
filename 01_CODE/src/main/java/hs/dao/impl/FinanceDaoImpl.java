package hs.dao.impl;

import hs.dao.FinanceDaoI;
import hs.model.TbFinance;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("financeDao")
public class FinanceDaoImpl extends BaseDaoImpl<TbFinance> implements FinanceDaoI {

	private static String type = "hs.model.TbFinance";

	@Override
	public List<TbFinance> findByExample(TbFinance t) {
		List<TbFinance> results = super.findByE(t, type);
		return results;
	}

	@Override
	public List<TbFinance> findByExample(TbFinance t, int page, int rows) {
		List<TbFinance> results = super.findByE(t, type, page, rows);
		return results;
	}

	@Override
	public List<TbFinance> findByExample(TbFinance t, int page, int rows, String sortName, String sortOrder) {
		List<TbFinance> results = super.findByE(t, type, page, rows, sortName, sortOrder);
		return results;
	}
}
