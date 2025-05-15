package com.uit.tms.TaskManagement.repository;

import com.uit.tms.TaskManagement.entity.IntegrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationRepository extends JpaRepository<IntegrationEntity, Long> {
}
