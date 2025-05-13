package com.uit.tms.TaskManagement.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.uit.tms.TaskManagement.constants.Endpoint;
import com.uit.tms.TaskManagement.constants.Keywords;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity // Enables @PreAuthorize, @Secured, etc.
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final ApplicationAuthenticationEntrypoint entryPoint;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    @Autowired
    private final CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                .cors(cors -> cors.disable()) // Disable CORS
                .logout(logout -> logout
                        .logoutUrl(Endpoint.LOGOUT) // the URL to trigger logout
                        .logoutSuccessUrl(Endpoint.HOME) // where to redirect after logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID", Keywords.JWT_TOKEN))
                .authorizeHttpRequests(auth -> auth
                        // Allow anonymous access to the following endpoints
                        .requestMatchers(
                                Endpoint.HOME,
                                "/swagger-ui/**",
                                "/actuator/**",
                                "/v3/api-docs/**",
                                "/auth/**",
                                "/login/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(entryPoint))
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(authorization -> authorization
                                .authorizationRequestResolver(customAuthorizationRequestResolver()))
                        .loginPage(Endpoint.LOGIN)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService))
                        .successHandler(oAuth2LoginSuccessHandler)
                        .failureUrl(Endpoint.LOGIN + "?error=true") // Redirect on failure
                        .permitAll())
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic authentication
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        return new DefaultOAuth2UserService();
    }

    // Custom OAuth2AuthorizationRequestResolver to add additional parameters
    // To let Github show the authorization page anytime user login with Github (linked to
    // the same account)
    private OAuth2AuthorizationRequestResolver customAuthorizationRequestResolver() {
        DefaultOAuth2AuthorizationRequestResolver defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository, "/oauth2/authorization");

        return new OAuth2AuthorizationRequestResolver() {
            @Override
            public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
                OAuth2AuthorizationRequest authorizationRequest = defaultResolver.resolve(request);
                return customizeAuthorizationRequest(authorizationRequest);
            }

            @Override
            public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
                OAuth2AuthorizationRequest authorizationRequest = defaultResolver.resolve(request,
                        clientRegistrationId);
                return customizeAuthorizationRequest(authorizationRequest);
            }

            private OAuth2AuthorizationRequest customizeAuthorizationRequest(
                    OAuth2AuthorizationRequest authorizationRequest) {
                if (authorizationRequest == null)
                    return null;

                Map<String, Object> additionalParameters = new HashMap<>(
                        authorizationRequest.getAdditionalParameters());
                additionalParameters.put("prompt", "consent");

                return OAuth2AuthorizationRequest.from(authorizationRequest)
                        .additionalParameters(additionalParameters)
                        .build();
            }
        };
    }
}
