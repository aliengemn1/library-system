package com.example.library.admin.service;

import com.example.library.admin.model.Admin;
import com.example.library.admin.model.AdminRole;
import com.example.library.admin.model.AdminStatus;
import com.example.library.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }
    
    public Optional<Admin> getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
    
    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
    
    public Admin createAdmin(Admin admin) {
        // Check if username or email already exists
        if (adminRepository.existsByUsername(admin.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Encode password
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        
        // Set default values
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        admin.setStatus(AdminStatus.ACTIVE);
        
        return adminRepository.save(admin);
    }
    
    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        
        // Check if username or email is being changed and if it already exists
        if (!admin.getUsername().equals(adminDetails.getUsername()) && 
            adminRepository.existsByUsername(adminDetails.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (!admin.getEmail().equals(adminDetails.getEmail()) && 
            adminRepository.existsByEmail(adminDetails.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Update fields
        admin.setUsername(adminDetails.getUsername());
        admin.setEmail(adminDetails.getEmail());
        admin.setFirstName(adminDetails.getFirstName());
        admin.setLastName(adminDetails.getLastName());
        admin.setRole(adminDetails.getRole());
        admin.setStatus(adminDetails.getStatus());
        admin.setUpdatedAt(LocalDateTime.now());
        
        // Update password if provided
        if (adminDetails.getPassword() != null && !adminDetails.getPassword().isEmpty()) {
            admin.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
        }
        
        return adminRepository.save(admin);
    }
    
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        adminRepository.delete(admin);
    }
    
    public List<Admin> getAdminsByRole(AdminRole role) {
        return adminRepository.findByRole(role);
    }
    
    public List<Admin> getAdminsByStatus(AdminStatus status) {
        return adminRepository.findByStatus(status);
    }
    
    public List<Admin> searchAdmins(String query) {
        return adminRepository.searchAdmins(query);
    }
    
    public List<Admin> getRecentlyActiveAdmins() {
        return adminRepository.findRecentlyActiveAdmins();
    }
    
    public void updateLastLogin(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setLastLogin(LocalDateTime.now());
        adminRepository.save(admin);
    }
    
    public boolean validateCredentials(String username, String password) {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isPresent() && admin.get().getStatus() == AdminStatus.ACTIVE) {
            return passwordEncoder.matches(password, admin.get().getPassword());
        }
        return false;
    }
    
    public void updatePermissions(Long adminId, Set<String> permissions) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setPermissions(permissions);
        admin.setUpdatedAt(LocalDateTime.now());
        adminRepository.save(admin);
    }
    
    public void changePassword(Long adminId, String newPassword) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setPassword(passwordEncoder.encode(newPassword));
        admin.setUpdatedAt(LocalDateTime.now());
        adminRepository.save(admin);
    }
    
    public void lockAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setStatus(AdminStatus.LOCKED);
        admin.setUpdatedAt(LocalDateTime.now());
        adminRepository.save(admin);
    }
    
    public void unlockAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setStatus(AdminStatus.ACTIVE);
        admin.setUpdatedAt(LocalDateTime.now());
        adminRepository.save(admin);
    }
} 