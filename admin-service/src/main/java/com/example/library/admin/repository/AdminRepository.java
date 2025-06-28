package com.example.library.admin.repository;

import com.example.library.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    Optional<Admin> findByUsername(String username);
    
    Optional<Admin> findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    List<Admin> findByRole(com.example.library.admin.model.AdminRole role);
    
    List<Admin> findByStatus(com.example.library.admin.model.AdminStatus status);
    
    @Query("SELECT a FROM Admin a WHERE a.username LIKE %:query% OR a.email LIKE %:query% OR a.firstName LIKE %:query% OR a.lastName LIKE %:query%")
    List<Admin> searchAdmins(@Param("query") String query);
    
    @Query("SELECT a FROM Admin a WHERE a.lastLogin IS NOT NULL ORDER BY a.lastLogin DESC")
    List<Admin> findRecentlyActiveAdmins();
} 