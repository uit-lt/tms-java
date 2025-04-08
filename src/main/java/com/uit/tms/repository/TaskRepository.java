package com.uit.tms.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.uit.tms.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
