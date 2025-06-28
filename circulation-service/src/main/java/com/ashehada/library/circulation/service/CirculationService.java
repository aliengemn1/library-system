package com.ashehada.library.circulation.service;

import com.ashehada.library.circulation.model.CirculationRecord;
import com.ashehada.library.circulation.repository.CirculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CirculationService {

    @Autowired
    private CirculationRepository circulationRepository;

    public List<CirculationRecord> getAllRecords() {
        return circulationRepository.findAll();
    }

    public CirculationRecord getRecordById(Long id) {
        Optional<CirculationRecord> record = circulationRepository.findById(id);
        return record.orElseThrow(() -> new RuntimeException("Circulation record not found with id: " + id));
    }

    public CirculationRecord saveRecord(CirculationRecord record) {
        return circulationRepository.save(record);
    }

    public void deleteRecord(Long id) {
        circulationRepository.deleteById(id);
    }

    public CirculationRecord checkoutBook(CirculationRecord record) {
        record.setCheckoutDate(LocalDateTime.now());
        record.setDueDate(LocalDateTime.now().plusDays(14)); // Default 14 days
        record.setStatus(CirculationRecord.CirculationStatus.CHECKED_OUT);
        return circulationRepository.save(record);
    }

    public CirculationRecord checkoutBook(Long bookId, Long patronId) {
        CirculationRecord record = new CirculationRecord(bookId, patronId);
        return checkoutBook(record);
    }

    public CirculationRecord returnBook(Long id) {
        CirculationRecord record = getRecordById(id);
        record.setReturnDate(LocalDateTime.now());
        record.setStatus(CirculationRecord.CirculationStatus.RETURNED);
        
        // Calculate fine if overdue
        if (record.isOverdue()) {
            long daysOverdue = record.getDaysOverdue();
            record.setFineAmount(daysOverdue * 0.50); // $0.50 per day
        }
        
        return circulationRepository.save(record);
    }

    public List<CirculationRecord> getOverdueRecords() {
        return circulationRepository.findByStatusAndDueDateBefore(
            CirculationRecord.CirculationStatus.CHECKED_OUT, 
            LocalDateTime.now()
        );
    }

    public List<CirculationRecord> findByPatronId(Long patronId) {
        return circulationRepository.findByPatronId(patronId);
    }

    public List<CirculationRecord> findByBookId(Long bookId) {
        return circulationRepository.findByBookId(bookId);
    }

    public List<CirculationRecord> findByStatus(CirculationRecord.CirculationStatus status) {
        return circulationRepository.findByStatus(status);
    }

    public double calculateTotalFines(Long patronId) {
        List<CirculationRecord> records = findByPatronId(patronId);
        return records.stream()
                .mapToDouble(record -> record.getFineAmount() != null ? record.getFineAmount() : 0.0)
                .sum();
    }
} 