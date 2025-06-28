package com.example.library.patron.config;

import com.example.library.patron.model.Patron;
import com.example.library.patron.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public void run(String... args) throws Exception {
        if (patronRepository.count() == 0) {
            createSamplePatrons();
        }
    }

    private void createSamplePatrons() {
        // Sample Patron 1
        Patron patron1 = new Patron();
        patron1.setFirstName("John");
        patron1.setLastName("Smith");
        patron1.setEmail("john.smith@email.com");
        patron1.setPhoneNumber("1555010101");
        patron1.setAddress("123 Main St");
        patron1.setCity("Anytown");
        patron1.setState("CA");
        patron1.setZipCode("12345");
        patron1.setPatronType(Patron.PatronType.STUDENT);
        patron1.setStatus(Patron.PatronStatus.ACTIVE);
        patron1.setMaxBooksAllowed(5);
        patronRepository.save(patron1);

        // Sample Patron 2
        Patron patron2 = new Patron();
        patron2.setFirstName("Jane");
        patron2.setLastName("Doe");
        patron2.setEmail("jane.doe@email.com");
        patron2.setPhoneNumber("1555010202");
        patron2.setAddress("456 Oak Ave");
        patron2.setCity("Somewhere");
        patron2.setState("NY");
        patron2.setZipCode("67890");
        patron2.setPatronType(Patron.PatronType.FACULTY);
        patron2.setStatus(Patron.PatronStatus.ACTIVE);
        patron2.setMaxBooksAllowed(10);
        patronRepository.save(patron2);

        // Sample Patron 3
        Patron patron3 = new Patron();
        patron3.setFirstName("Bob");
        patron3.setLastName("Johnson");
        patron3.setEmail("bob.johnson@email.com");
        patron3.setPhoneNumber("1555010303");
        patron3.setAddress("789 Pine Rd");
        patron3.setCity("Elsewhere");
        patron3.setState("TX");
        patron3.setZipCode("11111");
        patron3.setPatronType(Patron.PatronType.COMMUNITY);
        patron3.setStatus(Patron.PatronStatus.ACTIVE);
        patron3.setMaxBooksAllowed(3);
        patronRepository.save(patron3);

        // Sample Patron 4
        Patron patron4 = new Patron();
        patron4.setFirstName("Alice");
        patron4.setLastName("Brown");
        patron4.setEmail("alice.brown@email.com");
        patron4.setPhoneNumber("1555010404");
        patron4.setAddress("321 Elm St");
        patron4.setCity("Nowhere");
        patron4.setState("FL");
        patron4.setZipCode("22222");
        patron4.setPatronType(Patron.PatronType.STUDENT);
        patron4.setStatus(Patron.PatronStatus.SUSPENDED);
        patron4.setMaxBooksAllowed(5);
        patronRepository.save(patron4);
    }
} 