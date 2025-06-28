package com.ashehada.library.notification.repository;

import com.ashehada.library.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByType(Notification.NotificationType type);

    List<Notification> findByStatus(Notification.NotificationStatus status);

    List<Notification> findByRecipient(String recipient);

    List<Notification> findByDeliveryMethod(Notification.DeliveryMethod deliveryMethod);

    List<Notification> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Notification> findBySentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Notification> findByRetryCountGreaterThan(Integer retryCount);

    List<Notification> findByTypeAndStatus(Notification.NotificationType type, Notification.NotificationStatus status);

    List<Notification> findByRecipientAndStatus(String recipient, Notification.NotificationStatus status);
} 