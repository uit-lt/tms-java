package com.uit.tms.TaskManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "integrations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntegrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id")
    private PlatformEntity platform;

    private LocalDateTime connectedOn;

    private boolean syncTasks;

    private boolean syncComments;

    @Override
    public String toString() {
        return "IntegrationEntity{" +
                "id=" + id +
                ", platform=" + (platform != null ? platform.getId() : null) +
                ", connectedOn=" + connectedOn +
                ", syncTasks=" + syncTasks +
                ", syncComments=" + syncComments +
                '}';
    }
}
