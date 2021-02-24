package pl.edu.wszib.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.model.Report;
import pl.edu.wszib.services.ReportService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/report")
public class ReportWebService {

    private final ReportService reportService;

    @Autowired
    public ReportWebService(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<?> getCouponById(@PathVariable Long reportId) {
        return new ResponseEntity<>(reportService.getReportById(reportId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllReports() {
        return new ResponseEntity<List<Report>>(reportService.getAllReports(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveNewReport(@RequestBody Report report) {
        return new ResponseEntity<Report>(reportService.addReport(report), HttpStatus.CREATED);
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<?> updateUser(@RequestBody Report report, @PathVariable Long reportId) {
        return new ResponseEntity<Report>(reportService.updateReport(reportId, report), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<?> deleteReport(@PathVariable Long reportId) {
        reportService.deleteReport(reportId);
        return new ResponseEntity<String>("Report with id: " + reportId + " was deleted successfully",
                HttpStatus.OK);
    }

}
