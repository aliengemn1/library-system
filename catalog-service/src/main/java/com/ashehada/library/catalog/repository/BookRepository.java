package com.ashehada.library.catalog.repository;

import com.ashehada.library.catalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByAuthorContaining(String author);
    List<Book> findByIsbn(String isbn);
    List<Book> findBySubjectContaining(String subject);
} 