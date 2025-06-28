package com.ashehada.library.catalog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "marc_records")
public class MarcRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Control number is required")
    @Column(unique = true, nullable = false)
    private String controlNumber;
    
    @Column(name = "leader", length = 24)
    private String leader;
    
    @OneToMany(mappedBy = "marcRecord", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MarcField> fields;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "status")
    private String status = "ACTIVE";
    
    // Constructors
    public MarcRecord() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public MarcRecord(String controlNumber) {
        this();
        this.controlNumber = controlNumber;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getControlNumber() {
        return controlNumber;
    }
    
    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }
    
    public String getLeader() {
        return leader;
    }
    
    public void setLeader(String leader) {
        this.leader = leader;
    }
    
    public List<MarcField> getFields() {
        return fields;
    }
    
    public void setFields(List<MarcField> fields) {
        this.fields = fields;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
} 