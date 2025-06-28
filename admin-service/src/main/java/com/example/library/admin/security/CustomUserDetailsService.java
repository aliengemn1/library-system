package com.example.library.admin.security;

import com.example.library.admin.model.Admin;
import com.example.library.admin.model.AdminStatus;
import com.example.library.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private AdminService adminService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdminByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + username));
        
        if (admin.getStatus() != AdminStatus.ACTIVE) {
            throw new UsernameNotFoundException("Admin account is not active: " + username);
        }
        
        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .authorities(admin.getPermissions().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()))
                .disabled(admin.getStatus() != AdminStatus.ACTIVE)
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(admin.getStatus() == AdminStatus.LOCKED)
                .build();
    }
} 