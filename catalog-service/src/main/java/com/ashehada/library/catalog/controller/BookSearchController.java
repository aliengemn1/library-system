package com.ashehada.library.catalog.controller;

import com.ashehada.library.catalog.model.Book;
import com.ashehada.library.catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class BookSearchController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> search(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String isbn,
        @RequestParam(required = false) String subject) {
        if (title != null) return bookRepository.findByTitleContaining(title);
        if (author != null) return bookRepository.findByAuthorContaining(author);
        if (isbn != null) return bookRepository.findByIsbn(isbn);
        if (subject != null) return bookRepository.findBySubjectContaining(subject);
        return new ArrayList<>();
    }
} 