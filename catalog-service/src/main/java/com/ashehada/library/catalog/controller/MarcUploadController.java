package com.ashehada.library.catalog.controller;

import com.ashehada.library.catalog.model.Book;
import com.ashehada.library.catalog.repository.BookRepository;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class MarcUploadController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/upload-marc")
    public ResponseEntity<?> uploadMarcFile(@RequestParam("file") MultipartFile file) throws Exception {
        InputStream input = file.getInputStream();
        MarcStreamReader reader = new MarcStreamReader(input);
        List<Book> books = new ArrayList<>();
        while (reader.hasNext()) {
            Record record = reader.next();
            Book book = new Book();
            DataField titleField = (DataField) record.getVariableField("245");
            if (titleField != null) {
                Subfield subfieldA = titleField.getSubfield('a');
                if (subfieldA != null) book.setTitle(subfieldA.getData());
            }
            DataField authorField = (DataField) record.getVariableField("100");
            if (authorField != null) {
                Subfield subfieldA = authorField.getSubfield('a');
                if (subfieldA != null) book.setAuthor(subfieldA.getData());
            }
            DataField isbnField = (DataField) record.getVariableField("020");
            if (isbnField != null) {
                Subfield subfieldA = isbnField.getSubfield('a');
                if (subfieldA != null) book.setIsbn(subfieldA.getData());
            }
            DataField subjectField = (DataField) record.getVariableField("650");
            if (subjectField != null) {
                Subfield subfieldA = subjectField.getSubfield('a');
                if (subfieldA != null) book.setSubject(subfieldA.getData());
            }
            books.add(book);
        }
        bookRepository.saveAll(books);
        return ResponseEntity.ok("Indexed successfully");
    }
} 