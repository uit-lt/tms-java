package com.uit.tms.TaskManagement.initializer;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.uit.tms.TaskManagement.entity.RoleEntity;
import com.uit.tms.TaskManagement.entity.UserEntity;
import com.uit.tms.TaskManagement.repository.RoleRepository;
import com.uit.tms.TaskManagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String adminUsername = "admin";
        String adminPassword = "admin";
        String adminRole = "ADMIN";
        Optional<RoleEntity> existingRoleAdmin = roleRepository.findByName(adminRole);
        if (existingRoleAdmin.isEmpty()) {
        	RoleEntity newRole = new RoleEntity();
            newRole.setName("ADMIN");
            roleRepository.save(newRole);
        }
        
        Optional<UserEntity> existingAdmin = userRepository.findByUsername(adminUsername);
        if (existingAdmin.isEmpty()) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUsername(adminUsername);
            adminUser.setPassword(passwordEncoder.encode(adminPassword)); // Use password encoder in real apps
            roleRepository.findByName(adminRole).ifPresent(role -> adminUser.getRoles().add(role));
            userRepository.save(adminUser);
            System.out.println("Admin user created.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}

