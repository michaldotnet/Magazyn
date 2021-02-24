package pl.edu.wszib.dao;


import pl.edu.wszib.model.Report;

import java.util.List;

public interface ReportDao {

    Report getReportById(Long id);

    Report updateReport(Report report);

    Report addReport(Report report);

    void deleteReport(Long id);

    List<Report> getAllReports();

}
