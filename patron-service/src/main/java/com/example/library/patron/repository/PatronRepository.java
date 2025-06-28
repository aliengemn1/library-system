package com.example.library.patron.repository;

import com.example.library.patron.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

    List<Patron> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String firstName, String lastName, String email);

    List<Patron> findByStatus(String status);

    List<Patron> findByPatronType(String patronType);

    List<Patron> findByPhoneNumberContaining(String phoneNumber);

    List<Patron> findByAddressContainingIgnoreCase(String address);
} 