package com.ashehada.library.integration.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "integration_configs")
public class IntegrationConfig {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Type is required")
    @Column(nullable = false)
    private String type;
    
    @Column(name = "endpoint_url")
    private String endpointUrl;
    
    @Column(name = "api_key")
    private String apiKey;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "config_data", columnDefinition = "TEXT")
    private String configData;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private IntegrationStatus status;
    
    @Column(name = "last_sync_date")
    private LocalDateTime lastSyncDate;
    
    @Column(name = "sync_frequency")
    private Integer syncFrequency; // in minutes
    
    @Column(name = "enabled")
    private Boolean enabled;
    
    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Constructors
    public IntegrationConfig() {
        this.status = IntegrationStatus.INACTIVE;
        this.enabled = false;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    public IntegrationConfig(String name, String type) {
        this();
        this.name = name;
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getEndpointUrl() {
        return endpointUrl;
    }
    
    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfigData() {
        return configData;
    }
    
    public void setConfigData(String configData) {
        this.configData = configData;
    }
    
    public IntegrationStatus getStatus() {
        return status;
    }
    
    public void setStatus(IntegrationStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getLastSyncDate() {
        return lastSyncDate;
    }
    
    public void setLastSyncDate(LocalDateTime lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }
    
    public Integer getSyncFrequency() {
        return syncFrequency;
    }
    
    public void setSyncFrequency(Integer syncFrequency) {
        this.syncFrequency = syncFrequency;
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
    
    public enum IntegrationStatus {
        ACTIVE,
        INACTIVE,
        ERROR,
        SYNCING
    }
} 