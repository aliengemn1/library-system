package com.ashehada.library.catalog.service;

import com.ashehada.library.catalog.model.MarcField;
import com.ashehada.library.catalog.model.MarcRecord;
import com.ashehada.library.catalog.repository.MarcRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    @Autowired
    private MarcRecordRepository marcRecordRepository;

    public List<MarcRecord> getAllRecords() {
        return marcRecordRepository.findAll();
    }

    public MarcRecord getRecordById(Long id) {
        Optional<MarcRecord> record = marcRecordRepository.findById(id);
        return record.orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }

    public MarcRecord saveRecord(MarcRecord record) {
        // Set the relationship for MarcFields
        if (record.getFields() != null) {
            for (MarcField field : record.getFields()) {
                field.setMarcRecord(record);
            }
        }
        return marcRecordRepository.save(record);
    }

    public void deleteRecord(Long id) {
        marcRecordRepository.deleteById(id);
    }

    public List<MarcRecord> searchRecords(String query) {
        return marcRecordRepository.findByLeaderContainingIgnoreCase(query);
    }

    public List<MarcRecord> findByTag(String tag) {
        return marcRecordRepository.findByFieldsTag(tag);
    }

    public List<MarcRecord> findByContent(String content) {
        return marcRecordRepository.findByFieldsContentContainingIgnoreCase(content);
    }
} 