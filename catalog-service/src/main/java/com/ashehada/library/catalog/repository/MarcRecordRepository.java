package com.ashehada.library.catalog.repository;

import com.ashehada.library.catalog.model.MarcRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcRecordRepository extends JpaRepository<MarcRecord, Long> {

    List<MarcRecord> findByLeaderContainingIgnoreCase(String leader);

    @Query("SELECT DISTINCT r FROM MarcRecord r JOIN r.fields f WHERE f.tag = :tag")
    List<MarcRecord> findByFieldsTag(@Param("tag") String tag);

    @Query("SELECT DISTINCT r FROM MarcRecord r JOIN r.fields f WHERE LOWER(f.content) LIKE LOWER(CONCAT('%', :content, '%'))")
    List<MarcRecord> findByFieldsContentContainingIgnoreCase(@Param("content") String content);

    @Query("SELECT r FROM MarcRecord r WHERE r.leader LIKE %:query% OR EXISTS (SELECT f FROM MarcField f WHERE f.marcRecord = r AND (f.tag LIKE %:query% OR f.content LIKE %:query%))")
    List<MarcRecord> searchByQuery(@Param("query") String query);
} 