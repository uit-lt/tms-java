package com.uit.tms.TaskManagement.entity;

import java.time.LocalDateTime;

import com.uit.tms.TaskManagement.enums.TaskStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private float estTime;
    
    private LocalDateTime dueDate;
    
    private String priority;
    
    private int assigneeId;
    
    private int projectId;
    
    @ManyToOne
    @JoinColumn(name = "created_by", updatable = false)
    @CreatedBy
    private UserEntity createdBy;
    
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
