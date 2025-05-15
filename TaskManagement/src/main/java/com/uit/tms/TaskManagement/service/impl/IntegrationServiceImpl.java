package com.uit.tms.TaskManagement.service.impl;

import com.uit.tms.TaskManagement.entity.IntegrationEntity;
import com.uit.tms.TaskManagement.repository.IntegrationRepository;
import com.uit.tms.TaskManagement.service.IntegrationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IntegrationServiceImpl implements IntegrationService {

    private final IntegrationRepository integrationRepository;

    @Override
    public List<IntegrationEntity> getAllIntegrations() {
        return integrationRepository.findAll();
    }

    @Override
    public Optional<IntegrationEntity> getIntegrationById(Long id) {
        return integrationRepository.findById(id);
    }

    @Override
    public IntegrationEntity createIntegration(IntegrationEntity integration) {
        return integrationRepository.save(integration);
    }

    @Override
    public void deleteIntegration(Long id) {
        if (!integrationRepository.existsById(id)) {
            throw new EntityNotFoundException("Integration not found with id: " + id);
        }
        integrationRepository.deleteById(id);
    }
}
