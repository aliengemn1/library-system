package com.ashehada.library.catalog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "marc_fields")
public class MarcField {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Tag is required")
    @Column(nullable = false)
    private String tag;
    
    @Column(name = "indicator1")
    private String indicator1;
    
    @Column(name = "indicator2")
    private String indicator2;
    
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marc_record_id")
    private MarcRecord marcRecord;
    
    // Constructors
    public MarcField() {}
    
    public MarcField(String tag, String content) {
        this.tag = tag;
        this.content = content;
    }
    
    public MarcField(String tag, String indicator1, String indicator2, String content) {
        this.tag = tag;
        this.indicator1 = indicator1;
        this.indicator2 = indicator2;
        this.content = content;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTag() {
        return tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public String getIndicator1() {
        return indicator1;
    }
    
    public void setIndicator1(String indicator1) {
        this.indicator1 = indicator1;
    }
    
    public String getIndicator2() {
        return indicator2;
    }
    
    public void setIndicator2(String indicator2) {
        this.indicator2 = indicator2;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public MarcRecord getMarcRecord() {
        return marcRecord;
    }
    
    public void setMarcRecord(MarcRecord marcRecord) {
        this.marcRecord = marcRecord;
    }
} 