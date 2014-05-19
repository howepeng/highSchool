package hs.dao;

import hs.model.TbFinance;

import java.util.List;

public interface FinanceDaoI extends BaseDaoI<TbFinance> {

	public List<TbFinance> findByExample(TbFinance t);

	public List<TbFinance> findByExample(TbFinance t, int page, int rows);

	public List<TbFinance> findByExample(TbFinance t, int page, int rows, String sortName, String sortOrder);

}
