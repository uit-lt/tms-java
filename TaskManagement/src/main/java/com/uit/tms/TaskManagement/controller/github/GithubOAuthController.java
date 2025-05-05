package com.uit.tms.TaskManagement.controller.github;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import com.uit.tms.TaskManagement.constants.Endpoint;

public class GithubOAuthController {
   @GetMapping(Endpoint.USER_GITHUB)
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println("User: " + principal.getAttributes());
        return principal.getAttributes().toString();
    }
}
