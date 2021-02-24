package pl.edu.wszib.services;

import pl.edu.wszib.model.Report;

import java.util.List;

public interface ReportService {

    Report getReportById(Long id);

    Report updateReport(Long reportId, Report report);

    Report addReport(Report report);

    void deleteReport(Long id);

    List<Report> getAllReports();
}
