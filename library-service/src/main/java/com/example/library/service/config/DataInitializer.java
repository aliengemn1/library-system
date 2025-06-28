package com.example.library.service.config;

import com.example.library.service.model.Book;
import com.example.library.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
        bookRepository.deleteAll();
        
        // Add sample books
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565");
        book1.setDescription("A story of the fabulously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan.");
        book1.setPublicationYear(1925);
        bookRepository.save(book1);
        
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0446310789");
        book2.setDescription("The story of young Scout Finch and her father Atticus in a racially divided Alabama town.");
        book2.setPublicationYear(1960);
        bookRepository.save(book2);
        
        Book book3 = new Book("1984", "George Orwell", "978-0451524935");
        book3.setDescription("A dystopian novel about totalitarianism and surveillance society.");
        book3.setPublicationYear(1949);
        bookRepository.save(book3);
        
        Book book4 = new Book("Pride and Prejudice", "Jane Austen", "978-0141439518");
        book4.setDescription("A romantic novel of manners that follows the emotional development of Elizabeth Bennet.");
        book4.setPublicationYear(1813);
        bookRepository.save(book4);
        
        Book book5 = new Book("The Hobbit", "J.R.R. Tolkien", "978-0547928241");
        book5.setDescription("A fantasy novel about Bilbo Baggins, a hobbit who embarks on a quest.");
        book5.setPublicationYear(1937);
        bookRepository.save(book5);
        
        System.out.println("Sample books loaded successfully!");
    }
} 