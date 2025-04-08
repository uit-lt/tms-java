/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uit.tms.TaskManagement.controller;

import com.uit.tms.TaskManagement.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final DashboardService dashboardService;

    @Autowired
    public HomeController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Add the "Hello World" message to the model
        model.addAttribute("message", "Hello World");
        // Return the name of the Thymeleaf template
        model.addAllAttributes(dashboardService.getDashboardStats());
        return "home";
    }
}
