//package com.uit.tms.TaskManagement.initializer;
//
//import java.util.Optional;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.uit.tms.TaskManagement.entity.RoleEntity;
//import com.uit.tms.TaskManagement.entity.UserEntity;
//import com.uit.tms.TaskManagement.repository.RoleRepository;
//import com.uit.tms.TaskManagement.repository.UserRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Component
//@RequiredArgsConstructor
//public class UserInitializer implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//
//    private final RoleRepository roleRepository;
//    
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        String adminUsername = "admin";
//
//        Optional<UserEntity> existingAdmin = userRepository.findByUsername(adminUsername);
//        if (existingAdmin.isEmpty()) {
//            RoleEntity adminRole = roleRepository.findByName("ADMIN")
//                    .orElseGet(() -> {
//                        RoleEntity newRole = new RoleEntity();
//                        newRole.setName("ADMIN");
//                        return roleRepository.save(newRole);
//                    });
//
//            UserEntity adminUser = new UserEntity();
//            adminUser.setUsername(adminUsername);
//            adminUser.setPassword(passwordEncoder.encode("admin")); // Use password encoder in real apps
//            adminUser.getRoles().add(adminRole);
//
//            userRepository.save(adminUser);
//            System.out.println("Admin user created.");
//        } else {
//            System.out.println("Admin user already exists.");
//        }
//    }
//}
//
