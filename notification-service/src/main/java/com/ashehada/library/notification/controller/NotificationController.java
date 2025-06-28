package com.ashehada.library.notification.controller;

import com.ashehada.library.notification.model.Notification;
import com.ashehada.library.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Web UI endpoints
    @GetMapping
    public String index(Model model) {
        List<Notification> notifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", notifications);
        return "notification/index";
    }

    @GetMapping("/new")
    public String newNotificationForm(Model model) {
        model.addAttribute("notification", new Notification());
        return "notification/form";
    }

    @GetMapping("/edit/{id}")
    public String editNotificationForm(@PathVariable Long id, Model model) {
        Notification notification = notificationService.getNotificationById(id);
        model.addAttribute("notification", notification);
        return "notification/form";
    }

    @PostMapping("/save")
    public String saveNotification(@ModelAttribute Notification notification) {
        notificationService.saveNotification(notification);
        return "redirect:/notifications";
    }

    @GetMapping("/delete/{id}")
    public String deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return "redirect:/notifications";
    }

    @GetMapping("/view/{id}")
    public String viewNotification(@PathVariable Long id, Model model) {
        Notification notification = notificationService.getNotificationById(id);
        model.addAttribute("notification", notification);
        return "notification/view";
    }

    @GetMapping("/send/{id}")
    public String sendNotification(@PathVariable Long id) {
        notificationService.sendNotification(id);
        return "redirect:/notifications";
    }

    // REST API endpoints
    @GetMapping("/api/notifications")
    @ResponseBody
    public List<Notification> getAllNotificationsApi() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/api/notifications/{id}")
    @ResponseBody
    public Notification getNotificationByIdApi(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @PostMapping("/api/notifications")
    @ResponseBody
    public Notification createNotificationApi(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification);
    }

    @PutMapping("/api/notifications/{id}")
    @ResponseBody
    public Notification updateNotificationApi(@PathVariable Long id, @RequestBody Notification notification) {
        notification.setId(id);
        return notificationService.saveNotification(notification);
    }

    @DeleteMapping("/api/notifications/{id}")
    @ResponseBody
    public void deleteNotificationApi(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }

    @PostMapping("/api/notifications/{id}/send")
    @ResponseBody
    public Notification sendNotificationApi(@PathVariable Long id) {
        return notificationService.sendNotification(id);
    }

    @GetMapping("/api/notifications/type/{type}")
    @ResponseBody
    public List<Notification> getNotificationsByTypeApi(@PathVariable String type) {
        return notificationService.getNotificationsByType(type);
    }

    @GetMapping("/api/notifications/status/{status}")
    @ResponseBody
    public List<Notification> getNotificationsByStatusApi(@PathVariable String status) {
        return notificationService.getNotificationsByStatus(status);
    }
} 