package com.uit.tms.TaskManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author quochuynh
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // Add the welcome message to the model
        model.addAttribute("message", "Welcome to TaskManagement, Quoc Huynh!");

        // Add dummy task stats to the model (replace with real data in a real app)
        model.addAttribute("totalTasks", 10);
        model.addAttribute("completedTasks", 6);
        model.addAttribute("pendingTasks", 4);

        // Return the name of the Thymeleaf template
        return "home";
    }
}