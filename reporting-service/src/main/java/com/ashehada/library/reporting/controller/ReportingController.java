package com.ashehada.library.reporting.controller;

import com.ashehada.library.reporting.model.Report;
import com.ashehada.library.reporting.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    // Web UI endpoints
    @GetMapping
    public String index(Model model) {
        List<Report> reports = reportingService.getAllReports();
        model.addAttribute("reports", reports);
        return "reporting/index";
    }

    @GetMapping("/new")
    public String newReportForm(Model model) {
        model.addAttribute("report", new Report());
        return "reporting/form";
    }

    @GetMapping("/edit/{id}")
    public String editReportForm(@PathVariable Long id, Model model) {
        Report report = reportingService.getReportById(id);
        model.addAttribute("report", report);
        return "reporting/form";
    }

    @PostMapping("/save")
    public String saveReport(@ModelAttribute Report report) {
        reportingService.saveReport(report);
        return "redirect:/reports";
    }

    @GetMapping("/delete/{id}")
    public String deleteReport(@PathVariable Long id) {
        reportingService.deleteReport(id);
        return "redirect:/reports";
    }

    @GetMapping("/view/{id}")
    public String viewReport(@PathVariable Long id, Model model) {
        Report report = reportingService.getReportById(id);
        model.addAttribute("report", report);
        return "reporting/view";
    }

    @GetMapping("/generate/{id}")
    public String generateReport(@PathVariable Long id) {
        reportingService.generateReport(id);
        return "redirect:/reports";
    }

    @GetMapping("/download/{id}")
    public String downloadReport(@PathVariable Long id) {
        reportingService.downloadReport(id);
        return "redirect:/reports";
    }

    // REST API endpoints
    @GetMapping("/api/reports")
    @ResponseBody
    public List<Report> getAllReportsApi() {
        return reportingService.getAllReports();
    }

    @GetMapping("/api/reports/{id}")
    @ResponseBody
    public Report getReportByIdApi(@PathVariable Long id) {
        return reportingService.getReportById(id);
    }

    @PostMapping("/api/reports")
    @ResponseBody
    public Report createReportApi(@RequestBody Report report) {
        return reportingService.saveReport(report);
    }

    @PutMapping("/api/reports/{id}")
    @ResponseBody
    public Report updateReportApi(@PathVariable Long id, @RequestBody Report report) {
        report.setId(id);
        return reportingService.saveReport(report);
    }

    @DeleteMapping("/api/reports/{id}")
    @ResponseBody
    public void deleteReportApi(@PathVariable Long id) {
        reportingService.deleteReport(id);
    }

    @PostMapping("/api/reports/{id}/generate")
    @ResponseBody
    public Report generateReportApi(@PathVariable Long id) {
        return reportingService.generateReport(id);
    }

    @GetMapping("/api/reports/type/{type}")
    @ResponseBody
    public List<Report> getReportsByTypeApi(@PathVariable String type) {
        return reportingService.getReportsByType(type);
    }

    @GetMapping("/api/reports/status/{status}")
    @ResponseBody
    public List<Report> getReportsByStatusApi(@PathVariable String status) {
        return reportingService.getReportsByStatus(status);
    }

    @GetMapping("/api/reports/scheduled")
    @ResponseBody
    public List<Report> getScheduledReportsApi() {
        return reportingService.getScheduledReports();
    }
} 