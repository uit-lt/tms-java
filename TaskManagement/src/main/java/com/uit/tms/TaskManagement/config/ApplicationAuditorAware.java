package com.uit.tms.TaskManagement.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.uit.tms.TaskManagement.entity.UserEntity;
import com.uit.tms.TaskManagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ApplicationAuditorAware implements AuditorAware<UserEntity> {

    private final UserRepository userRepository;


    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }
}
