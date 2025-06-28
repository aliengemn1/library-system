package com.example.library.service.controller;

import com.example.library.service.model.Book;
import com.example.library.service.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Optional<Book> book = bookService.getBookByIsbn(isbn);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = bookService.getBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
        List<Book> books = bookService.getBooksByTitle(title);
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> books = bookService.getAvailableBooks();
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Book>> getBooksByPublicationYear(@PathVariable Integer year) {
        List<Book> books = bookService.getBooksByPublicationYear(year);
        return ResponseEntity.ok(books);
    }
    
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Optional<Book> updatedBook = bookService.updateBook(id, bookDetails);
        return updatedBook.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/{id}/borrow")
    public ResponseEntity<Book> borrowBook(@PathVariable Long id) {
        Optional<Book> book = bookService.borrowBook(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{id}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long id) {
        Optional<Book> book = bookService.returnBook(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 