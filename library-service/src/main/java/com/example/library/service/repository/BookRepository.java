package com.example.library.service.repository;

import com.example.library.service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    Optional<Book> findByIsbn(String isbn);
    
    List<Book> findByAvailable(Boolean available);
    
    List<Book> findByPublicationYear(Integer publicationYear);
} 