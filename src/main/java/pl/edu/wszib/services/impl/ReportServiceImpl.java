package pl.edu.wszib.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.dao.ReportDao;
import pl.edu.wszib.model.Report;
import pl.edu.wszib.services.ReportService;

import java.util.List;

@Component
public class ReportServiceImpl implements ReportService {

    private ReportDao reportDao;

    @Autowired
    public ReportServiceImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public Report getReportById(Long id) {
        return reportDao.getReportById(id);
    }

    @Override
    public Report updateReport(Long reportId, Report report) {
        report.setId(reportId);
        return reportDao.updateReport(report);
    }

    @Override
    public Report addReport(Report report) {
        return reportDao.addReport(report);
    }

    @Override
    public void deleteReport(Long id) {
        reportDao.deleteReport(id);
    }

    @Override
    public List<Report> getAllReports() {
        return reportDao.getAllReports();
    }
}
