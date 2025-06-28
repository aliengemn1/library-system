package com.example.library.patron.service;

import com.example.library.patron.model.Patron;
import com.example.library.patron.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long id) {
        Optional<Patron> patron = patronRepository.findById(id);
        return patron.orElseThrow(() -> new RuntimeException("Patron not found with id: " + id));
    }

    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }

    public List<Patron> searchPatrons(String query) {
        return patronRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query, query);
    }

    public List<Patron> findByStatus(String status) {
        return patronRepository.findByStatus(status);
    }

    public List<Patron> findByPatronType(String patronType) {
        return patronRepository.findByPatronType(patronType);
    }
} 