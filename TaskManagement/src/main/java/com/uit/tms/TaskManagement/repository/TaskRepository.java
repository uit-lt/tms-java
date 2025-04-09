package com.uit.tms.TaskManagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.uit.tms.TaskManagement.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
