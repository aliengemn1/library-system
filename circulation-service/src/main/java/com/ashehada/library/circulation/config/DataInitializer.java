package com.ashehada.library.circulation.config;

import com.ashehada.library.circulation.model.CirculationRecord;
import com.ashehada.library.circulation.repository.CirculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CirculationRepository circulationRepository;

    @Override
    public void run(String... args) throws Exception {
        if (circulationRepository.count() == 0) {
            createSampleRecords();
        }
    }

    private void createSampleRecords() {
        // Sample Circulation Record 1 - Currently checked out
        CirculationRecord record1 = new CirculationRecord(1L, 1L);
        record1.setCheckoutDate(LocalDateTime.now().minusDays(5));
        record1.setDueDate(LocalDateTime.now().plusDays(9));
        record1.setStatus(CirculationRecord.CirculationStatus.CHECKED_OUT);
        record1.setNotes("Book in good condition");
        circulationRepository.save(record1);

        // Sample Circulation Record 2 - Overdue
        CirculationRecord record2 = new CirculationRecord(2L, 2L);
        record2.setCheckoutDate(LocalDateTime.now().minusDays(20));
        record2.setDueDate(LocalDateTime.now().minusDays(6));
        record2.setStatus(CirculationRecord.CirculationStatus.OVERDUE);
        record2.setFineAmount(3.00); // 6 days overdue * $0.50
        record2.setNotes("Overdue book");
        circulationRepository.save(record2);

        // Sample Circulation Record 3 - Returned
        CirculationRecord record3 = new CirculationRecord(3L, 3L);
        record3.setCheckoutDate(LocalDateTime.now().minusDays(10));
        record3.setDueDate(LocalDateTime.now().minusDays(3));
        record3.setReturnDate(LocalDateTime.now().minusDays(1));
        record3.setStatus(CirculationRecord.CirculationStatus.RETURNED);
        record3.setNotes("Returned on time");
        circulationRepository.save(record3);

        // Sample Circulation Record 4 - Recently checked out
        CirculationRecord record4 = new CirculationRecord(1L, 4L);
        record4.setCheckoutDate(LocalDateTime.now().minusDays(1));
        record4.setDueDate(LocalDateTime.now().plusDays(13));
        record4.setStatus(CirculationRecord.CirculationStatus.CHECKED_OUT);
        record4.setNotes("New checkout");
        circulationRepository.save(record4);
    }
} 