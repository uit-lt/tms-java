package com.uit.tms.TaskManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.uit.tms.TaskManagement.entity.UserEntity;
import com.uit.tms.TaskManagement.repository.UserRepository;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {
	
	@Bean
    public AuditorAware<UserEntity> auditorProvider(UserRepository userRepository) {
        return new ApplicationAuditorAware(userRepository);
    }
}
