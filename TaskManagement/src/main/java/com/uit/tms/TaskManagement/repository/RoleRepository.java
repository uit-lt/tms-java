package com.uit.tms.TaskManagement.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uit.tms.TaskManagement.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	Optional<RoleEntity> findByName(String roleName);
	Set<RoleEntity> findByNameIn(Set<String> names);
}
