package com.uit.tms.TaskManagement.controller;

import com.uit.tms.TaskManagement.entity.PlatformEntity;
import com.uit.tms.TaskManagement.service.PlatformService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/platforms")
public class PlatformController {

    private final PlatformService platformService;

    @GetMapping
    public String listPlatforms(Model model) {
        model.addAttribute("platforms", platformService.getAllPlatforms());
        return "platform/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("platform", new PlatformEntity());
        return "platform/form";
    }

    @PostMapping
    public String createPlatform(@ModelAttribute @Valid PlatformEntity platform) {
        platformService.createPlatform(platform);
        return "redirect:/platforms";
    }

    @GetMapping("/delete/{id}")
    public String deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
        return "redirect:/platforms";
    }
}
