package com.uit.tms.TaskManagement.service;

import com.uit.tms.TaskManagement.entity.IntegrationEntity;

import java.util.List;
import java.util.Optional;

public interface IntegrationService {
    List<IntegrationEntity> getAllIntegrations();
    Optional<IntegrationEntity> getIntegrationById(Long id);
    IntegrationEntity createIntegration(IntegrationEntity integration);
    void deleteIntegration(Long id);
}
