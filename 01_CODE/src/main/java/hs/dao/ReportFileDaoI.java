package hs.dao;

import hs.model.TbReportFile;

import java.util.List;

public interface ReportFileDaoI extends BaseDaoI<TbReportFile> {

    public List<TbReportFile> findByExample(TbReportFile t);

    public List<TbReportFile> findByExample(TbReportFile t, int page, int rows);

    public List<TbReportFile> findByExample(TbReportFile t, int page, int rows, String sortName, String sortOrder);
}
