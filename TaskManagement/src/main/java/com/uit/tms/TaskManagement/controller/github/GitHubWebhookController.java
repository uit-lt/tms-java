package com.uit.tms.TaskManagement.controller.github;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public class GitHubWebhookController {

    @SuppressWarnings("unchecked")
    @PostMapping("/github-webhook")
    public ResponseEntity<String> handleWebhook(
            @RequestHeader("X-GitHub-Event") String githubEvent,
            @RequestBody Map<String, Object> payload
    ) {
        if ("issues".equals(githubEvent)) {
            Object issueObject = payload.get("issue");
            Map<String, Object> issue = null;
            if (issueObject instanceof Map) {
                issue = (Map<String, Object>) issueObject;
            }
            if (issue == null) {
                return ResponseEntity.badRequest().body("Invalid issue data");
            }
            String action = (String) payload.get("action");
            String title = (String) issue.get("title");
            System.out.println("Issue " + action + ": " + title);
        }

        return ResponseEntity.ok("Webhook received");
    }
}
