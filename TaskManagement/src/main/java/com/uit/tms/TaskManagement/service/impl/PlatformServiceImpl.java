package com.uit.tms.TaskManagement.service.impl;

import com.uit.tms.TaskManagement.entity.PlatformEntity;
import com.uit.tms.TaskManagement.repository.PlatformRepository;
import com.uit.tms.TaskManagement.service.PlatformService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;

    @Override
    public List<PlatformEntity> getAllPlatforms() {
        return platformRepository.findAll();
    }

    @Override
    public Optional<PlatformEntity> getPlatformById(Long id) {
        return platformRepository.findById(id);
    }

    @Override
    public PlatformEntity createPlatform(PlatformEntity platform) {
        return platformRepository.save(platform);
    }

    @Override
    public void deletePlatform(Long id) {
        if (!platformRepository.existsById(id)) {
            throw new EntityNotFoundException("Platform not found with id: " + id);
        }
        platformRepository.deleteById(id);
    }
}
