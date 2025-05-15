package com.uit.tms.TaskManagement.repository;

import com.uit.tms.TaskManagement.entity.PlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformEntity, Long> {
}
