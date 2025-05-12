package com.uit.tms.TaskManagement.initializer;

import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.uit.tms.TaskManagement.constants.Keywords;
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
		createAdminAccount();
		createUserAccount();
	}

	private void createAdminAccount() {
		String adminUsername = Keywords.ADMIN;
		String adminPassword = Keywords.ADMIN;
		String adminRoleName = Keywords.ROLE_ADMIN;
		RoleEntity adminRole = roleRepository.findByName(adminRoleName)
				.orElseGet(() -> roleRepository.save(RoleEntity.builder().name(adminRoleName).build()));

		Optional<UserEntity> existingAdmin = userRepository.findByUsername(adminUsername);
		if (existingAdmin.isEmpty()) {
			String adminMail = "admin@example.com";
			UserEntity admin = UserEntity.builder()
					.username(adminUsername)
					.password(passwordEncoder.encode(adminPassword))
					.email(adminMail).twoFactorEnabled(false)
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

	private void createUserAccount() {
		String userUsername = Keywords.USER;
		String userPassword = Keywords.USER;
		String userRoleName = Keywords.ROLE_USER;
		RoleEntity userRole = roleRepository.findByName(userRoleName)
				.orElseGet(() -> roleRepository.save(RoleEntity.builder().name(userRoleName).build()));
		Optional<UserEntity> existingUser = userRepository.findByUsername(userUsername);
		if (existingUser.isEmpty()) {
			String userMail = "tmsuser123@gmail.com";
			UserEntity user = UserEntity.builder()
					.username(userUsername)
					.password(passwordEncoder.encode(userPassword))
					.email(userMail).twoFactorEnabled(false)
					.roles(Set.of(userRole)).build();
			userRepository.save(user);
			System.out.println("Default user created.");
		} else {
			UserEntity user = existingUser.get();
			if (!user.getRoles().contains(userRole)) {
				user.getRoles().add(userRole);
				userRepository.save(user);
			} else {
				System.out.println("User already exists with user role.");
			}
		}
	}
}
