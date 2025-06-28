package com.ashehada.library.catalog.config;

import com.ashehada.library.catalog.model.MarcField;
import com.ashehada.library.catalog.model.MarcRecord;
import com.ashehada.library.catalog.repository.MarcRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MarcRecordRepository marcRecordRepository;

    @Override
    public void run(String... args) throws Exception {
        if (marcRecordRepository.count() == 0) {
            createSampleRecords();
        }
    }

    private void createSampleRecords() {
        // Sample Book 1: "The Great Gatsby"
        MarcRecord gatsby = new MarcRecord();
        gatsby.setLeader("00000cam a2200000 a 4500");
        gatsby.setControlNumber("123456789");
        
        MarcField gatsby001 = new MarcField("001", "123456789");
        MarcField gatsby020 = new MarcField("020", "978-0743273565");
        MarcField gatsby100 = new MarcField("100", "Fitzgerald, F. Scott");
        MarcField gatsby245 = new MarcField("245", "The Great Gatsby");
        MarcField gatsby260 = new MarcField("260", "New York : Scribner, 2004");
        MarcField gatsby300 = new MarcField("300", "180 pages ; 21 cm");
        MarcField gatsby650 = new MarcField("650", "American fiction -- 20th century");
        
        gatsby.setFields(Arrays.asList(gatsby001, gatsby020, gatsby100, gatsby245, gatsby260, gatsby300, gatsby650));
        marcRecordRepository.save(gatsby);

        // Sample Book 2: "To Kill a Mockingbird"
        MarcRecord mockingbird = new MarcRecord();
        mockingbird.setLeader("00000cam a2200000 a 4500");
        mockingbird.setControlNumber("987654321");
        
        MarcField mb001 = new MarcField("001", "987654321");
        MarcField mb020 = new MarcField("020", "978-0446310789");
        MarcField mb100 = new MarcField("100", "Lee, Harper");
        MarcField mb245 = new MarcField("245", "To Kill a Mockingbird");
        MarcField mb260 = new MarcField("260", "New York : Warner Books, 1982");
        MarcField mb300 = new MarcField("300", "281 pages ; 18 cm");
        MarcField mb650 = new MarcField("650", "Southern States -- Fiction");
        
        mockingbird.setFields(Arrays.asList(mb001, mb020, mb100, mb245, mb260, mb300, mb650));
        marcRecordRepository.save(mockingbird);

        // Sample Book 3: "1984"
        MarcRecord nineteen84 = new MarcRecord();
        nineteen84.setLeader("00000cam a2200000 a 4500");
        nineteen84.setControlNumber("456789123");
        
        MarcField n84001 = new MarcField("001", "456789123");
        MarcField n84020 = new MarcField("020", "978-0451524935");
        MarcField n84100 = new MarcField("100", "Orwell, George");
        MarcField n84245 = new MarcField("245", "1984");
        MarcField n84260 = new MarcField("260", "New York : Signet Classic, 1950");
        MarcField n84300 = new MarcField("300", "328 pages ; 18 cm");
        MarcField n84650 = new MarcField("650", "Dystopian fiction");
        
        nineteen84.setFields(Arrays.asList(n84001, n84020, n84100, n84245, n84260, n84300, n84650));
        marcRecordRepository.save(nineteen84);
    }
} 