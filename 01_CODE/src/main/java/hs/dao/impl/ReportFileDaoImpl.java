package hs.dao.impl;

import hs.dao.ReportFileDaoI;
import hs.model.TbReportFile;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("reportDao")
public class ReportFileDaoImpl extends BaseDaoImpl<TbReportFile> implements ReportFileDaoI {
    private static String type = "hs.model.TbReportFile";

    @Override
    public List<TbReportFile> findByExample(TbReportFile t) {
        List<TbReportFile> results = super.findByE(t, type);
        return results;
    }

    @Override
    public List<TbReportFile> findByExample(TbReportFile t, int page, int rows) {
        List<TbReportFile> results = super.findByE(t, type, page, rows);
        return results;
    }

    @Override
    public List<TbReportFile> findByExample(TbReportFile t, int page, int rows, String sortName, String sortOrder) {
        List<TbReportFile> results = super.findByE(t, type, page, rows, sortName, sortOrder);
        return results;
    }
}
