package com.ashehada.library.circulation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "circulation_records")
public class CirculationRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Book ID is required")
    @Column(name = "book_id", nullable = false)
    private Long bookId;
    
    @NotNull(message = "Patron ID is required")
    @Column(name = "patron_id", nullable = false)
    private Long patronId;
    
    @Column(name = "checkout_date")
    private LocalDateTime checkoutDate;
    
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    
    @Column(name = "return_date")
    private LocalDateTime returnDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CirculationStatus status;
    
    @Column(name = "fine_amount")
    private Double fineAmount;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    // Constructors
    public CirculationRecord() {
        this.status = CirculationStatus.CHECKED_OUT;
        this.checkoutDate = LocalDateTime.now();
        this.dueDate = LocalDateTime.now().plusDays(14); // Default 14 days
    }
    
    public CirculationRecord(Long bookId, Long patronId) {
        this();
        this.bookId = bookId;
        this.patronId = patronId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getBookId() {
        return bookId;
    }
    
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    
    public Long getPatronId() {
        return patronId;
    }
    
    public void setPatronId(Long patronId) {
        this.patronId = patronId;
    }
    
    public LocalDateTime getCheckoutDate() {
        return checkoutDate;
    }
    
    public void setCheckoutDate(LocalDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
    
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    
    public LocalDateTime getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
    
    public CirculationStatus getStatus() {
        return status;
    }
    
    public void setStatus(CirculationStatus status) {
        this.status = status;
    }
    
    public Double getFineAmount() {
        return fineAmount;
    }
    
    public void setFineAmount(Double fineAmount) {
        this.fineAmount = fineAmount;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    // Helper methods
    public boolean isOverdue() {
        return status == CirculationStatus.CHECKED_OUT && 
               LocalDateTime.now().isAfter(dueDate);
    }
    
    public long getDaysOverdue() {
        if (!isOverdue()) {
            return 0;
        }
        return java.time.Duration.between(dueDate, LocalDateTime.now()).toDays();
    }
    
    public enum CirculationStatus {
        CHECKED_OUT,
        RETURNED,
        OVERDUE,
        LOST
    }
} 