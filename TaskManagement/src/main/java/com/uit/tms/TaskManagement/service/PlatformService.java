package com.uit.tms.TaskManagement.service;

import com.uit.tms.TaskManagement.entity.PlatformEntity;
import java.util.List;
import java.util.Optional;

public interface PlatformService {
    List<PlatformEntity> getAllPlatforms();
    Optional<PlatformEntity> getPlatformById(Long id);
    PlatformEntity createPlatform(PlatformEntity platform);
    void deletePlatform(Long id);
}
