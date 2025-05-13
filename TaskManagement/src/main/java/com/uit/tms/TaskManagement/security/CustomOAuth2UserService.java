package com.uit.tms.TaskManagement.security;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.uit.tms.TaskManagement.constants.Keywords;
import com.uit.tms.TaskManagement.entity.RoleEntity;
import com.uit.tms.TaskManagement.entity.UserEntity;
import com.uit.tms.TaskManagement.repository.RoleRepository;
import com.uit.tms.TaskManagement.repository.UserRepository;
import com.uit.tms.TaskManagement.util.Utils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauthUser = delegate.loadUser(userRequest);
        String username = oauthUser.getAttribute("login"); // GitHub username
        String email = oauthUser.getAttribute("email");    // May be null

        String accessToken = userRequest.getAccessToken().getTokenValue();
        Optional<UserEntity> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            System.out.println("User already exists: " + username);
            user.setGithubAccessToken(accessToken);
            userRepository.save(user);
            System.out.println("Updated GitHub access token: " + accessToken);
        } else {
            RoleEntity userRole = roleRepository.findByName(Keywords.ROLE_USER).orElse(null);
            if (userRole == null) {
                System.out.println("User role not found, check database setup.");
                return oauthUser;
            }

            String password = Utils.generateRandomPassword(12);
            UserEntity newUser = new UserEntity();
            newUser.setTwoFactorEnabled(false);
            newUser.setUsername(username);
            newUser.setPassword(new BCryptPasswordEncoder().encode(password));
            newUser.setEmail(email);
            newUser.setRoles(Set.of(userRole));
            newUser.setGithubAccessToken(accessToken);
            userRepository.save(newUser);

            System.out.println("New GitHub user created: " + username);
        }

        return oauthUser;
    }
}