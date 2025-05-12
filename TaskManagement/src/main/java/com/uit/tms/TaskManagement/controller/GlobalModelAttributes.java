package com.uit.tms.TaskManagement.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uit.tms.TaskManagement.constants.Keywords;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute
    public void addGlobalAttributes(Authentication authentication, Model model) {
        if (authentication == null)
            return;
        if (!authentication.isAuthenticated())
            return;

        authenticatedWithJWT(authentication, model);
    }

    private void authenticatedWithJWT(Authentication authentication, Model model) {
        Object principal = authentication.getPrincipal();
        String username = null;

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else if (principal instanceof String) {
            username = (String) principal;
        }

        System.out.println("Welcome to tms: "+username);

        model.addAttribute(Keywords.USERNAME, username);
    }
}
