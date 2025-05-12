package com.uit.tms.TaskManagement.security;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.uit.tms.TaskManagement.constants.Endpoint;
import com.uit.tms.TaskManagement.constants.Keywords;
import com.uit.tms.TaskManagement.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public OAuth2LoginSuccessHandler(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        String username = oauthUser.getAttribute("login"); // Or "email", based on your setup

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Create Spring Security Authentication Token
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        // Set authentication in context
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // Generate your JWT using the username
        String jwt = jwtUtil.generateToken(username);

        // Set JWT as HttpOnly cookie
        Cookie jwtCookie = new Cookie(Keywords.JWT_TOKEN, jwt);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(JwtUtil.expirationMs);
        response.addCookie(jwtCookie);

        // Redirect to home page
        response.sendRedirect(Endpoint.HOME);
    }
}
