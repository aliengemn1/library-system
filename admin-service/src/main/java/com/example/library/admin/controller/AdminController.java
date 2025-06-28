package com.example.library.admin.controller;

import com.example.library.admin.model.Admin;
import com.example.library.admin.model.AdminRole;
import com.example.library.admin.model.AdminStatus;
import com.example.library.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/admin/api")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }
    
    @GetMapping("/admins/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/admins")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        try {
            Admin createdAdmin = adminService.createAdmin(admin);
            return ResponseEntity.ok(createdAdmin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create admin: " + e.getMessage());
        }
    }
    
    @PutMapping("/admins/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        try {
            Admin updatedAdmin = adminService.updateAdmin(id, adminDetails);
            return ResponseEntity.ok(updatedAdmin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update admin: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/admins/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.ok().body("Admin deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete admin: " + e.getMessage());
        }
    }
    
    @GetMapping("/admins/role/{role}")
    public ResponseEntity<List<Admin>> getAdminsByRole(@PathVariable AdminRole role) {
        List<Admin> admins = adminService.getAdminsByRole(role);
        return ResponseEntity.ok(admins);
    }
    
    @GetMapping("/admins/status/{status}")
    public ResponseEntity<List<Admin>> getAdminsByStatus(@PathVariable AdminStatus status) {
        List<Admin> admins = adminService.getAdminsByStatus(status);
        return ResponseEntity.ok(admins);
    }
    
    @GetMapping("/admins/search")
    public ResponseEntity<List<Admin>> searchAdmins(@RequestParam String query) {
        List<Admin> admins = adminService.searchAdmins(query);
        return ResponseEntity.ok(admins);
    }
    
    @GetMapping("/admins/recent")
    public ResponseEntity<List<Admin>> getRecentlyActiveAdmins() {
        List<Admin> admins = adminService.getRecentlyActiveAdmins();
        return ResponseEntity.ok(admins);
    }
    
    @PutMapping("/admins/{id}/permissions")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> updatePermissions(@PathVariable Long id, @RequestBody Set<String> permissions) {
        try {
            adminService.updatePermissions(id, permissions);
            return ResponseEntity.ok().body("Permissions updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update permissions: " + e.getMessage());
        }
    }
    
    @PutMapping("/admins/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            String newPassword = request.get("newPassword");
            if (newPassword == null || newPassword.isEmpty()) {
                return ResponseEntity.badRequest().body("New password is required");
            }
            adminService.changePassword(id, newPassword);
            return ResponseEntity.ok().body("Password changed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to change password: " + e.getMessage());
        }
    }
    
    @PutMapping("/admins/{id}/lock")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> lockAdmin(@PathVariable Long id) {
        try {
            adminService.lockAdmin(id);
            return ResponseEntity.ok().body("Admin locked successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to lock admin: " + e.getMessage());
        }
    }
    
    @PutMapping("/admins/{id}/unlock")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> unlockAdmin(@PathVariable Long id) {
        try {
            adminService.unlockAdmin(id);
            return ResponseEntity.ok().body("Admin unlocked successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to unlock admin: " + e.getMessage());
        }
    }
} 