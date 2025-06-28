package com.ashehada.library.reporting.repository;

import com.ashehada.library.reporting.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByType(Report.ReportType type);

    List<Report> findByStatus(Report.ReportStatus status);

    List<Report> findByFormat(Report.ReportFormat format);

    List<Report> findByEnabledTrue();

    List<Report> findByEnabledFalse();

    List<Report> findByTypeAndStatus(Report.ReportType type, Report.ReportStatus status);

    List<Report> findByGeneratedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Report> findByLastRunDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Report> findByStatusAndScheduledDateBefore(Report.ReportStatus status, LocalDateTime date);

    List<Report> findByRunFrequency(String runFrequency);

    List<Report> findByStatusAndEnabledTrue(Report.ReportStatus status);
} 