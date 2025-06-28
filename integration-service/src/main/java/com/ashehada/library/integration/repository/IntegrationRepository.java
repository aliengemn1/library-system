package com.ashehada.library.integration.repository;

import com.ashehada.library.integration.model.IntegrationConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IntegrationRepository extends JpaRepository<IntegrationConfig, Long> {

    List<IntegrationConfig> findByType(String type);

    List<IntegrationConfig> findByStatus(IntegrationConfig.IntegrationStatus status);

    List<IntegrationConfig> findByEnabledTrue();

    List<IntegrationConfig> findByEnabledFalse();

    List<IntegrationConfig> findByTypeAndStatus(String type, IntegrationConfig.IntegrationStatus status);

    List<IntegrationConfig> findByLastSyncDateBefore(LocalDateTime date);

    List<IntegrationConfig> findByLastSyncDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<IntegrationConfig> findByEnabledTrueAndLastSyncDateBeforeOrLastSyncDateIsNull(LocalDateTime date);

    List<IntegrationConfig> findByStatusAndEnabledTrue(IntegrationConfig.IntegrationStatus status);
} 