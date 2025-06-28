package com.ashehada.library.reporting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Description is required")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "report_type")
    private ReportType type;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    private ReportFormat format;
    
    @Column(name = "query_sql", columnDefinition = "TEXT")
    private String querySql;
    
    @Column(name = "parameters", columnDefinition = "TEXT")
    private String parameters;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReportStatus status;
    
    @Column(name = "file_path")
    private String filePath;
    
    @Column(name = "file_size")
    private Long fileSize;
    
    @Column(name = "generated_date")
    private LocalDateTime generatedDate;
    
    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;
    
    @Column(name = "last_run_date")
    private LocalDateTime lastRunDate;
    
    @Column(name = "run_frequency")
    private String runFrequency; // DAILY, WEEKLY, MONTHLY, MANUAL
    
    @Column(name = "enabled")
    private Boolean enabled;
    
    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Constructors
    public Report() {
        this.status = ReportStatus.DRAFT;
        this.enabled = false;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    public Report(String name, String description, ReportType type) {
        this();
        this.name = name;
        this.description = description;
        this.type = type;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public ReportType getType() {
        return type;
    }
    
    public void setType(ReportType type) {
        this.type = type;
    }
    
    public ReportFormat getFormat() {
        return format;
    }
    
    public void setFormat(ReportFormat format) {
        this.format = format;
    }
    
    public String getQuerySql() {
        return querySql;
    }
    
    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }
    
    public String getParameters() {
        return parameters;
    }
    
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
    
    public ReportStatus getStatus() {
        return status;
    }
    
    public void setStatus(ReportStatus status) {
        this.status = status;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public Long getFileSize() {
        return fileSize;
    }
    
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    public LocalDateTime getGeneratedDate() {
        return generatedDate;
    }
    
    public void setGeneratedDate(LocalDateTime generatedDate) {
        this.generatedDate = generatedDate;
    }
    
    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }
    
    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
    
    public LocalDateTime getLastRunDate() {
        return lastRunDate;
    }
    
    public void setLastRunDate(LocalDateTime lastRunDate) {
        this.lastRunDate = lastRunDate;
    }
    
    public String getRunFrequency() {
        return runFrequency;
    }
    
    public void setRunFrequency(String runFrequency) {
        this.runFrequency = runFrequency;
    }
    
    public Boolean getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    // Enums
    public enum ReportType {
        CIRCULATION_REPORT,
        INVENTORY_REPORT,
        PATRON_REPORT,
        FINANCIAL_REPORT,
        OVERDUE_REPORT,
        POPULAR_BOOKS_REPORT,
        CUSTOM_REPORT
    }
    
    public enum ReportFormat {
        PDF,
        EXCEL,
        CSV,
        HTML,
        JSON
    }
    
    public enum ReportStatus {
        DRAFT,
        GENERATING,
        COMPLETED,
        FAILED,
        SCHEDULED
    }
} 