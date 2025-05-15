package com.uit.tms.TaskManagement.controller;

import com.uit.tms.TaskManagement.entity.TagEntity;
import com.uit.tms.TaskManagement.service.TagService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public String listTags(Model model) {
        List<TagEntity> tags = tagService.getAllTags();
        model.addAttribute("tags", tags);
        return "tags/list";
    }

    @GetMapping("/new")
    public String newTagForm(Model model) {
        model.addAttribute("tag", new TagEntity());
        return "tags/form";
    }

    @PostMapping
    public String createTag(@RequestParam @NotBlank String title) {
        tagService.createTag(title);
        return "redirect:/tags";
    }

    @PostMapping("/{id}/delete")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/tags";
    }
}
