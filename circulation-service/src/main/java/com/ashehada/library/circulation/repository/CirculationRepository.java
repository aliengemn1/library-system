package com.ashehada.library.circulation.repository;

import com.ashehada.library.circulation.model.CirculationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CirculationRepository extends JpaRepository<CirculationRecord, Long> {

    List<CirculationRecord> findByPatronId(Long patronId);

    List<CirculationRecord> findByBookId(Long bookId);

    List<CirculationRecord> findByStatus(CirculationRecord.CirculationStatus status);

    List<CirculationRecord> findByStatusAndDueDateBefore(CirculationRecord.CirculationStatus status, LocalDateTime dueDate);

    List<CirculationRecord> findByCheckoutDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<CirculationRecord> findByReturnDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<CirculationRecord> findByFineAmountGreaterThan(Double amount);
} 