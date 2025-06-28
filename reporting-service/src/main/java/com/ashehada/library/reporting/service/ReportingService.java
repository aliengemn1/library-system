package com.ashehada.library.reporting.service;

import com.ashehada.library.reporting.model.Report;
import com.ashehada.library.reporting.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportingService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report getReportById(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        return report.orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
    }

    public Report saveReport(Report report) {
        report.setUpdatedDate(LocalDateTime.now());
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    public Report generateReport(Long id) {
        Report report = getReportById(id);
        
        try {
            report.setStatus(Report.ReportStatus.GENERATING);
            saveReport(report);
            
            // Simulate report generation
            System.out.println("Generating report: " + report.getName());
            
            // In a real implementation, you would execute the SQL query and generate the report
            Thread.sleep(3000); // Simulate generation time
            
            report.setStatus(Report.ReportStatus.COMPLETED);
            report.setGeneratedDate(LocalDateTime.now());
            report.setLastRunDate(LocalDateTime.now());
            report.setFilePath("/reports/" + report.getName().toLowerCase().replace(" ", "_") + ".pdf");
            report.setFileSize(1024L); // Simulate file size
            report.setErrorMessage(null);
            
            return saveReport(report);
        } catch (Exception e) {
            report.setStatus(Report.ReportStatus.FAILED);
            report.setErrorMessage(e.getMessage());
            return saveReport(report);
        }
    }

    public void downloadReport(Long id) {
        Report report = getReportById(id);
        
        if (report.getStatus() != Report.ReportStatus.COMPLETED) {
            throw new RuntimeException("Report is not ready for download");
        }
        
        // In a real implementation, you would serve the file for download
        System.out.println("Downloading report: " + report.getFilePath());
    }

    public List<Report> getReportsByType(String type) {
        return reportRepository.findByType(Report.ReportType.valueOf(type.toUpperCase()));
    }

    public List<Report> getReportsByStatus(String status) {
        return reportRepository.findByStatus(Report.ReportStatus.valueOf(status.toUpperCase()));
    }

    public List<Report> getScheduledReports() {
        return reportRepository.findByStatus(Report.ReportStatus.SCHEDULED);
    }

    public List<Report> getEnabledReports() {
        return reportRepository.findByEnabledTrue();
    }

    public Report enableReport(Long id) {
        Report report = getReportById(id);
        report.setEnabled(true);
        return saveReport(report);
    }

    public Report disableReport(Long id) {
        Report report = getReportById(id);
        report.setEnabled(false);
        return saveReport(report);
    }

    public Report scheduleReport(Long id, LocalDateTime scheduledDate) {
        Report report = getReportById(id);
        report.setStatus(Report.ReportStatus.SCHEDULED);
        report.setScheduledDate(scheduledDate);
        return saveReport(report);
    }

    public List<Report> getReportsNeedingGeneration() {
        return reportRepository.findByStatusAndScheduledDateBefore(Report.ReportStatus.SCHEDULED, LocalDateTime.now());
    }

    public Report createCirculationReport() {
        Report report = new Report();
        report.setName("Daily Circulation Report");
        report.setDescription("Daily summary of book checkouts and returns");
        report.setType(Report.ReportType.CIRCULATION_REPORT);
        report.setFormat(Report.ReportFormat.PDF);
        report.setRunFrequency("DAILY");
        report.setQuerySql("SELECT * FROM circulation_records WHERE DATE(checkout_date) = CURRENT_DATE");
        return saveReport(report);
    }

    public Report createOverdueReport() {
        Report report = new Report();
        report.setName("Overdue Books Report");
        report.setDescription("List of all overdue books and associated fines");
        report.setType(Report.ReportType.OVERDUE_REPORT);
        report.setFormat(Report.ReportFormat.EXCEL);
        report.setRunFrequency("WEEKLY");
        report.setQuerySql("SELECT * FROM circulation_records WHERE status = 'OVERDUE'");
        return saveReport(report);
    }

    public Report createPopularBooksReport() {
        Report report = new Report();
        report.setName("Popular Books Report");
        report.setDescription("Most frequently checked out books");
        report.setType(Report.ReportType.POPULAR_BOOKS_REPORT);
        report.setFormat(Report.ReportFormat.HTML);
        report.setRunFrequency("MONTHLY");
        report.setQuerySql("SELECT book_id, COUNT(*) as checkout_count FROM circulation_records GROUP BY book_id ORDER BY checkout_count DESC");
        return saveReport(report);
    }
} 