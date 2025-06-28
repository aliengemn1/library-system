package com.ashehada.library.notification.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Subject is required")
    @Column(nullable = false)
    private String subject;
    
    @NotBlank(message = "Message is required")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;
    
    @NotBlank(message = "Recipient is required")
    @Column(nullable = false)
    private String recipient;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType type;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_method")
    private DeliveryMethod deliveryMethod;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus status;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "sent_date")
    private LocalDateTime sentDate;
    
    @Column(name = "retry_count")
    private Integer retryCount;
    
    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;
    
    // Constructors
    public Notification() {
        this.status = NotificationStatus.PENDING;
        this.createdDate = LocalDateTime.now();
        this.retryCount = 0;
    }
    
    public Notification(String subject, String message, String recipient) {
        this();
        this.subject = subject;
        this.message = message;
        this.recipient = recipient;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    
    public NotificationType getType() {
        return type;
    }
    
    public void setType(NotificationType type) {
        this.type = type;
    }
    
    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }
    
    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
    
    public NotificationStatus getStatus() {
        return status;
    }
    
    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public LocalDateTime getSentDate() {
        return sentDate;
    }
    
    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }
    
    public Integer getRetryCount() {
        return retryCount;
    }
    
    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    // Enums
    public enum NotificationType {
        OVERDUE_REMINDER,
        DUE_DATE_REMINDER,
        RESERVATION_AVAILABLE,
        SYSTEM_ANNOUNCEMENT,
        FINE_NOTICE,
        WELCOME_MESSAGE
    }
    
    public enum DeliveryMethod {
        EMAIL,
        SMS,
        PUSH_NOTIFICATION,
        IN_APP
    }
    
    public enum NotificationStatus {
        PENDING,
        SENT,
        FAILED,
        CANCELLED
    }
} 