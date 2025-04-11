package com.uit.tms.TaskManagement.initializer;

import java.util.Optional;
import java.util.Set;

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
		String adminRoleName = "ADMIN";
		RoleEntity adminRole = roleRepository.findByName(adminRoleName)
				.orElseGet(() -> roleRepository.save(RoleEntity.builder().name(adminRoleName).build()));

		Optional<UserEntity> existingAdmin = userRepository.findByUsername(adminUsername);
		if (existingAdmin.isEmpty()) {
			UserEntity admin = UserEntity.builder().username(adminUsername)
					.password(passwordEncoder.encode(adminPassword)).email("admin@example.com").twoFactorEnabled(false)
					.roles(Set.of(adminRole)).build();

			userRepository.save(admin);
			System.out.println("Default admin user created.");
		} else {
			UserEntity admin = existingAdmin.get();
			if (!admin.getRoles().contains(adminRole)) {
				admin.getRoles().add(adminRole);
				userRepository.save(admin);
			} else {
				System.out.println("Admin user already exists with admin role.");
			}
		}
	}
}
