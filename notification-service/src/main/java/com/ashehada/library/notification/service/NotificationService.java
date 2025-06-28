package com.ashehada.library.notification.service;

import com.ashehada.library.notification.model.Notification;
import com.ashehada.library.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        return notification.orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public Notification sendNotification(Long id) {
        Notification notification = getNotificationById(id);
        
        try {
            // Simulate sending notification
            notification.setStatus(Notification.NotificationStatus.SENT);
            notification.setSentDate(LocalDateTime.now());
            
            // In a real implementation, you would integrate with email/SMS services
            System.out.println("Sending notification: " + notification.getSubject() + " to " + notification.getRecipient());
            
            return notificationRepository.save(notification);
        } catch (Exception e) {
            notification.setStatus(Notification.NotificationStatus.FAILED);
            notification.setErrorMessage(e.getMessage());
            notification.setRetryCount(notification.getRetryCount() + 1);
            return notificationRepository.save(notification);
        }
    }

    public List<Notification> getNotificationsByType(String type) {
        return notificationRepository.findByType(Notification.NotificationType.valueOf(type.toUpperCase()));
    }

    public List<Notification> getNotificationsByStatus(String status) {
        return notificationRepository.findByStatus(Notification.NotificationStatus.valueOf(status.toUpperCase()));
    }

    public List<Notification> getPendingNotifications() {
        return notificationRepository.findByStatus(Notification.NotificationStatus.PENDING);
    }

    public List<Notification> getFailedNotifications() {
        return notificationRepository.findByStatus(Notification.NotificationStatus.FAILED);
    }

    public List<Notification> findByRecipient(String recipient) {
        return notificationRepository.findByRecipient(recipient);
    }

    public List<Notification> findByDeliveryMethod(String deliveryMethod) {
        return notificationRepository.findByDeliveryMethod(Notification.DeliveryMethod.valueOf(deliveryMethod.toUpperCase()));
    }

    public Notification createOverdueReminder(String recipient, String bookTitle, int daysOverdue) {
        Notification notification = new Notification();
        notification.setSubject("Overdue Book Reminder");
        notification.setMessage("Your book '" + bookTitle + "' is " + daysOverdue + " days overdue. Please return it as soon as possible.");
        notification.setRecipient(recipient);
        notification.setType(Notification.NotificationType.OVERDUE_REMINDER);
        notification.setDeliveryMethod(Notification.DeliveryMethod.EMAIL);
        return saveNotification(notification);
    }

    public Notification createDueDateReminder(String recipient, String bookTitle, int daysUntilDue) {
        Notification notification = new Notification();
        notification.setSubject("Book Due Date Reminder");
        notification.setMessage("Your book '" + bookTitle + "' is due in " + daysUntilDue + " days. Please return it on time.");
        notification.setRecipient(recipient);
        notification.setType(Notification.NotificationType.DUE_DATE_REMINDER);
        notification.setDeliveryMethod(Notification.DeliveryMethod.EMAIL);
        return saveNotification(notification);
    }

    public Notification createFineNotice(String recipient, double fineAmount) {
        Notification notification = new Notification();
        notification.setSubject("Library Fine Notice");
        notification.setMessage("You have a fine of $" + fineAmount + " for overdue books. Please pay this fine to continue using library services.");
        notification.setRecipient(recipient);
        notification.setType(Notification.NotificationType.FINE_NOTICE);
        notification.setDeliveryMethod(Notification.DeliveryMethod.EMAIL);
        return saveNotification(notification);
    }
} 