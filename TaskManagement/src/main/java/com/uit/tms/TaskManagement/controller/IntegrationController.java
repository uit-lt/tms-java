package com.uit.tms.TaskManagement.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uit.tms.TaskManagement.model.Integration;
import com.uit.tms.TaskManagement.model.Platform;

@Controller
@RequestMapping("/integrations")
public class IntegrationController {
  private static final Logger logger = LoggerFactory.getLogger(IntegrationController.class);

  // Mock data for platforms and integrations
  private List<Platform> allPlatforms = Arrays.asList(
      new Platform(1L, "GitHub", "green"),
      new Platform(2L, "JIRA", "blue"),
      new Platform(3L, "Trello", "teal"),
      new Platform(4L, "Asana", "orange"),
      new Platform(5L, "Monday.com", "purple"));

  // Mock data for task field mapping storage
  private Map<Long, Map<String, String>> fieldMappings = new HashMap<>();

  @GetMapping
  public String listIntegrations(Model model) {
    // Simulating fetched integrations (in a real app would come from a
    // service/repository)
    List<Integration> integrations = Arrays.asList(
        new Integration(1L, allPlatforms.get(0), LocalDateTime.now().minusDays(15), true, true),
        new Integration(2L, allPlatforms.get(1), LocalDateTime.now().minusDays(5), true, false));

    // Get available platforms that are not yet connected
    List<Platform> availablePlatforms = Arrays.asList(
        allPlatforms.get(2),
        allPlatforms.get(3),
        allPlatforms.get(4));

    model.addAttribute("integrations", integrations);
    model.addAttribute("availablePlatforms", availablePlatforms);
    return "integrations/list";
  }

  @PostMapping
  public String addIntegration(@RequestParam("platform") Long platformId,
      @RequestParam("apiKey") String apiKey,
      RedirectAttributes redirectAttributes) {
    // In a real app, you would validate API key, create integration in database,
    // etc.
    logger.info("Adding integration for platform ID: {} with API key: {}", platformId, apiKey);

    // Add success message
    redirectAttributes.addFlashAttribute("message", "Platform successfully connected!");
    redirectAttributes.addFlashAttribute("alertClass", "alert-success");

    return "redirect:/integrations";
  }

  @PostMapping("/{id}")
  public String updateIntegration(@PathVariable Long id,
      @RequestParam(required = false, defaultValue = "false") boolean syncTasks,
      @RequestParam(required = false, defaultValue = "false") boolean syncComments,
      @RequestParam(required = false) Map<String, String> fieldMappings,
      RedirectAttributes redirectAttributes) {
    // In a real app, you would update the integration in the database
    logger.info("Updating integration {}: syncTasks={}, syncComments={}", id, syncTasks, syncComments);

    // If field mappings are provided, update them
    if (fieldMappings != null && !fieldMappings.isEmpty()) {
      logger.info("Updating field mappings for integration {}: {}", id, fieldMappings);
      this.fieldMappings.put(id, fieldMappings);
    }

    // Add success message
    redirectAttributes.addFlashAttribute("message", "Integration settings updated successfully!");
    redirectAttributes.addFlashAttribute("alertClass", "alert-success");

    return "redirect:/integrations";
  }

  @DeleteMapping("/{id}")
  public String deleteIntegration(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    // In a real app, you would delete the integration from the database
    logger.info("Deleting integration: {}", id);

    // Add success message
    redirectAttributes.addFlashAttribute("message", "Platform disconnected successfully!");
    redirectAttributes.addFlashAttribute("alertClass", "alert-success");

    return "redirect:/integrations";
  }

  @GetMapping("/{id}/configure")
  public String configureIntegration(@PathVariable Long id, Model model) {
    // In a real app, you would fetch the integration from the database
    Integration integration = new Integration(id, allPlatforms.get(0), LocalDateTime.now(), true, true);

    // Source fields (from our app)
    Map<String, String> sourceFields = new HashMap<>();
    sourceFields.put("title", "Title");
    sourceFields.put("description", "Description");
    sourceFields.put("status", "Status");
    sourceFields.put("priority", "Priority");
    sourceFields.put("dueDate", "Due Date");
    sourceFields.put("assignee", "Assignee");

    // Target fields based on integration platform (would be dynamic in real app)
    Map<String, String> targetFields = new HashMap<>();

    if (id == 1) { // GitHub
      targetFields.put("title", "Issue Title");
      targetFields.put("description", "Issue Description");
      targetFields.put("status", "State");
      targetFields.put("labels", "Labels");
      targetFields.put("milestone", "Milestone");
      targetFields.put("assignees", "Assignees");
    } else if (id == 2) { // JIRA
      targetFields.put("summary", "Summary");
      targetFields.put("description", "Description");
      targetFields.put("issuetype", "Issue Type");
      targetFields.put("priority", "Priority");
      targetFields.put("assignee", "Assignee");
      targetFields.put("duedate", "Due Date");
    }

    // Get current mappings if they exist, otherwise create empty map
    Map<String, String> currentMappings = fieldMappings.getOrDefault(id, new HashMap<>());

    model.addAttribute("integration", integration);
    model.addAttribute("sourceFields", sourceFields);
    model.addAttribute("targetFields", targetFields);
    model.addAttribute("currentMappings", currentMappings);

    return "integrations/configure";
  }

  @PostMapping("/{id}/sync")
  @ResponseBody
  public ResponseEntity<Map<String, Object>> syncIntegration(@PathVariable Long id) {
    // In a real app, you would trigger the synchronization process here
    logger.info("Manually triggering synchronization for integration: {}", id);

    // Simulate a successful sync (would be asynchronous in a real app)
    Map<String, Object> response = new HashMap<>();
    response.put("success", true);
    response.put("message", "Synchronization started successfully");
    response.put("syncedAt", LocalDateTime.now().toString());

    return ResponseEntity.ok(response);
  }

  @PostMapping("/{id}/reset")
  public String resetIntegration(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    // In a real app, you would reset the integration configuration
    logger.info("Resetting configuration for integration: {}", id);

    // Clear any stored field mappings
    fieldMappings.remove(id);

    // Add success message
    redirectAttributes.addFlashAttribute("message", "Integration configuration reset successfully!");
    redirectAttributes.addFlashAttribute("alertClass", "alert-success");

    return "redirect:/integrations";
  }
}