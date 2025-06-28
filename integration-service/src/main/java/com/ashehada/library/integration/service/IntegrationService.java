package com.ashehada.library.integration.service;

import com.ashehada.library.integration.model.IntegrationConfig;
import com.ashehada.library.integration.repository.IntegrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IntegrationService {

    @Autowired
    private IntegrationRepository integrationRepository;

    public List<IntegrationConfig> getAllConfigs() {
        return integrationRepository.findAll();
    }

    public IntegrationConfig getConfigById(Long id) {
        Optional<IntegrationConfig> config = integrationRepository.findById(id);
        return config.orElseThrow(() -> new RuntimeException("Integration config not found with id: " + id));
    }

    public IntegrationConfig saveConfig(IntegrationConfig config) {
        config.setUpdatedDate(LocalDateTime.now());
        return integrationRepository.save(config);
    }

    public void deleteConfig(Long id) {
        integrationRepository.deleteById(id);
    }

    public boolean testConnection(Long id) {
        IntegrationConfig config = getConfigById(id);
        
        try {
            // Simulate connection test
            System.out.println("Testing connection to: " + config.getEndpointUrl());
            
            // In a real implementation, you would test the actual connection
            // For now, we'll simulate a successful test
            config.setStatus(IntegrationConfig.IntegrationStatus.ACTIVE);
            config.setErrorMessage(null);
            config.setLastSyncDate(LocalDateTime.now());
            
            integrationRepository.save(config);
            return true;
        } catch (Exception e) {
            config.setStatus(IntegrationConfig.IntegrationStatus.ERROR);
            config.setErrorMessage(e.getMessage());
            integrationRepository.save(config);
            return false;
        }
    }

    public List<IntegrationConfig> getConfigsByType(String type) {
        return integrationRepository.findByType(type);
    }

    public List<IntegrationConfig> getConfigsByStatus(String status) {
        return integrationRepository.findByStatus(IntegrationConfig.IntegrationStatus.valueOf(status.toUpperCase()));
    }

    public List<IntegrationConfig> getActiveConfigs() {
        return integrationRepository.findByStatus(IntegrationConfig.IntegrationStatus.ACTIVE);
    }

    public List<IntegrationConfig> getEnabledConfigs() {
        return integrationRepository.findByEnabledTrue();
    }

    public IntegrationConfig enableConfig(Long id) {
        IntegrationConfig config = getConfigById(id);
        config.setEnabled(true);
        return saveConfig(config);
    }

    public IntegrationConfig disableConfig(Long id) {
        IntegrationConfig config = getConfigById(id);
        config.setEnabled(false);
        return saveConfig(config);
    }

    public void syncData(Long id) {
        IntegrationConfig config = getConfigById(id);
        
        if (!config.getEnabled()) {
            throw new RuntimeException("Integration is not enabled");
        }
        
        try {
            config.setStatus(IntegrationConfig.IntegrationStatus.SYNCING);
            saveConfig(config);
            
            // Simulate data synchronization
            System.out.println("Syncing data for integration: " + config.getName());
            
            // In a real implementation, you would perform the actual sync
            Thread.sleep(2000); // Simulate sync time
            
            config.setStatus(IntegrationConfig.IntegrationStatus.ACTIVE);
            config.setLastSyncDate(LocalDateTime.now());
            config.setErrorMessage(null);
            
            saveConfig(config);
        } catch (Exception e) {
            config.setStatus(IntegrationConfig.IntegrationStatus.ERROR);
            config.setErrorMessage(e.getMessage());
            saveConfig(config);
            throw new RuntimeException("Sync failed: " + e.getMessage());
        }
    }

    public List<IntegrationConfig> getConfigsNeedingSync() {
        return integrationRepository.findByEnabledTrueAndLastSyncDateBeforeOrLastSyncDateIsNull(
            LocalDateTime.now().minusMinutes(30)
        );
    }
} 