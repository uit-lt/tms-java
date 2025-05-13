package com.uit.tms.TaskManagement.entity;


import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;
	
	private String email;
	
	private boolean twoFactorEnabled;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "user_roles",
	  joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	  inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	private Set<RoleEntity> roles;

	@Schema(description = "Platform type: 0 for TMS, 1 for GitHub, 2 for Jira")
	@Builder.Default
	private int platformType = 0;

	private String githubAccessToken;
}
