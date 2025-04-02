/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        // Add the "Hello World" message to the model
        model.addAttribute("message", "Hello World");
        // Return the name of the Thymeleaf template
        return "home";
    }
}
