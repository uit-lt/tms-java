package com.uit.tms.TaskManagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.uit.tms.TaskManagement.model.Priority;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String status;

    private int tasksTotal;

    private int tasksDone;

    @ManyToOne
    @JoinColumn(name = "created_by", updatable = false)
    @CreatedBy
    private UserEntity createdBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Transient
    public int getProgressPercent() {
        if (tasksTotal == 0) return 0;
        return (int) ((double) tasksDone / tasksTotal * 100);
    }
}
