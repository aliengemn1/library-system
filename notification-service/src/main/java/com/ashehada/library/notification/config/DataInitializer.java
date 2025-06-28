package com.ashehada.library.notification.config;

import com.ashehada.library.notification.model.Notification;
import com.ashehada.library.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void run(String... args) throws Exception {
        if (notificationRepository.count() == 0) {
            createSampleNotifications();
        }
    }

    private void createSampleNotifications() {
        // Sample Notification 1 - Overdue Reminder
        Notification notification1 = new Notification();
        notification1.setSubject("Overdue Book Reminder");
        notification1.setMessage("Your book 'The Great Gatsby' is 5 days overdue. Please return it as soon as possible to avoid additional fines.");
        notification1.setRecipient("john.smith@email.com");
        notification1.setType(Notification.NotificationType.OVERDUE_REMINDER);
        notification1.setDeliveryMethod(Notification.DeliveryMethod.EMAIL);
        notification1.setStatus(Notification.NotificationStatus.SENT);
        notification1.setCreatedDate(LocalDateTime.now().minusDays(1));
        notification1.setSentDate(LocalDateTime.now().minusHours(12));
        notificationRepository.save(notification1);

        // Sample Notification 2 - Due Date Reminder
        Notification notification2 = new Notification();
        notification2.setSubject("Book Due Date Reminder");
        notification2.setMessage("Your book 'To Kill a Mockingbird' is due in 3 days. Please return it on time to avoid fines.");
        notification2.setRecipient("jane.doe@email.com");
        notification2.setType(Notification.NotificationType.DUE_DATE_REMINDER);
        notification2.setDeliveryMethod(Notification.DeliveryMethod.EMAIL);
        notification2.setStatus(Notification.NotificationStatus.PENDING);
        notification2.setCreatedDate(LocalDateTime.now().minusHours(2));
        notificationRepository.save(notification2);

        // Sample Notification 3 - Fine Notice
        Notification notification3 = new Notification();
        notification3.setSubject("Library Fine Notice");
        notification3.setMessage("You have a fine of $3.50 for overdue books. Please pay this fine to continue using library services.");
        notification3.setRecipient("bob.johnson@email.com");
        notification3.setType(Notification.NotificationType.FINE_NOTICE);
        notification3.setDeliveryMethod(Notification.DeliveryMethod.EMAIL);
        notification3.setStatus(Notification.NotificationStatus.SENT);
        notification3.setCreatedDate(LocalDateTime.now().minusDays(2));
        notification3.setSentDate(LocalDateTime.now().minusDays(1));
        notificationRepository.save(notification3);

        // Sample Notification 4 - System Announcement
        Notification notification4 = new Notification();
        notification4.setSubject("Library System Maintenance");
        notification4.setMessage("The library system will be undergoing maintenance on Saturday from 2-4 AM. Some services may be temporarily unavailable.");
        notification4.setRecipient("all@library.com");
        notification4.setType(Notification.NotificationType.SYSTEM_ANNOUNCEMENT);
        notification4.setDeliveryMethod(Notification.DeliveryMethod.IN_APP);
        notification4.setStatus(Notification.NotificationStatus.SENT);
        notification4.setCreatedDate(LocalDateTime.now().minusDays(3));
        notification4.setSentDate(LocalDateTime.now().minusDays(2));
        notificationRepository.save(notification4);

        // Sample Notification 5 - Failed Notification
        Notification notification5 = new Notification();
        notification5.setSubject("Welcome to the Library");
        notification5.setMessage("Welcome to our library! We're glad to have you as a member.");
        notification5.setRecipient("alice.brown@email.com");
        notification5.setType(Notification.NotificationType.WELCOME_MESSAGE);
        notification5.setDeliveryMethod(Notification.DeliveryMethod.EMAIL);
        notification5.setStatus(Notification.NotificationStatus.FAILED);
        notification5.setCreatedDate(LocalDateTime.now().minusHours(6));
        notification5.setRetryCount(3);
        notification5.setErrorMessage("Email server temporarily unavailable");
        notificationRepository.save(notification5);
    }
} 