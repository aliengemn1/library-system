package com.example.library.service.service;

import com.example.library.service.model.Book;
import com.example.library.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailable(true);
    }
    
    public List<Book> getBooksByPublicationYear(Integer year) {
        return bookRepository.findByPublicationYear(year);
    }
    
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    
    public Optional<Book> updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookDetails.getTitle());
                    existingBook.setAuthor(bookDetails.getAuthor());
                    existingBook.setDescription(bookDetails.getDescription());
                    existingBook.setIsbn(bookDetails.getIsbn());
                    existingBook.setPublicationYear(bookDetails.getPublicationYear());
                    existingBook.setAvailable(bookDetails.getAvailable());
                    return bookRepository.save(existingBook);
                });
    }
    
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public Optional<Book> borrowBook(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    if (book.getAvailable()) {
                        book.setAvailable(false);
                        return bookRepository.save(book);
                    }
                    return book;
                });
    }
    
    public Optional<Book> returnBook(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setAvailable(true);
                    return bookRepository.save(book);
                });
    }
} 