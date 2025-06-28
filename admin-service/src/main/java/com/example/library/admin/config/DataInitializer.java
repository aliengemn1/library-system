package com.example.library.admin.config;

import com.example.library.admin.model.Admin;
import com.example.library.admin.model.AdminRole;
import com.example.library.admin.model.AdminStatus;
import com.example.library.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private AdminService adminService;
    
    @Override
    public void run(String... args) throws Exception {
        // Create default super admin if no admins exist
        if (adminService.getAllAdmins().isEmpty()) {
            createDefaultSuperAdmin();
            createDefaultAdmin();
        }
    }
    
    private void createDefaultSuperAdmin() {
        try {
            Admin superAdmin = new Admin();
            superAdmin.setUsername("superadmin");
            superAdmin.setEmail("superadmin@library.com");
            superAdmin.setPassword("superadmin123");
            superAdmin.setFirstName("Super");
            superAdmin.setLastName("Administrator");
            superAdmin.setRole(AdminRole.SUPER_ADMIN);
            superAdmin.setStatus(AdminStatus.ACTIVE);
            
            // Set super admin permissions
            Set<String> permissions = new HashSet<>();
            permissions.add("ROLE_SUPER_ADMIN");
            permissions.add("ROLE_ADMIN");
            permissions.add("ROLE_LIBRARIAN");
            permissions.add("ROLE_ASSISTANT");
            permissions.add("ADMIN_CREATE");
            permissions.add("ADMIN_UPDATE");
            permissions.add("ADMIN_DELETE");
            permissions.add("ADMIN_VIEW");
            permissions.add("SYSTEM_MANAGE");
            superAdmin.setPermissions(permissions);
            
            adminService.createAdmin(superAdmin);
            System.out.println("Default Super Admin created: superadmin / superadmin123");
        } catch (Exception e) {
            System.out.println("Failed to create default super admin: " + e.getMessage());
        }
    }
    
    private void createDefaultAdmin() {
        try {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setEmail("admin@library.com");
            admin.setPassword("admin123");
            admin.setFirstName("Library");
            admin.setLastName("Administrator");
            admin.setRole(AdminRole.ADMIN);
            admin.setStatus(AdminStatus.ACTIVE);
            
            // Set admin permissions
            Set<String> permissions = new HashSet<>();
            permissions.add("ROLE_ADMIN");
            permissions.add("ROLE_LIBRARIAN");
            permissions.add("ROLE_ASSISTANT");
            permissions.add("ADMIN_VIEW");
            permissions.add("CATALOG_MANAGE");
            permissions.add("PATRON_MANAGE");
            permissions.add("CIRCULATION_MANAGE");
            permissions.add("REPORT_VIEW");
            admin.setPermissions(permissions);
            
            adminService.createAdmin(admin);
            System.out.println("Default Admin created: admin / admin123");
        } catch (Exception e) {
            System.out.println("Failed to create default admin: " + e.getMessage());
        }
    }
} 