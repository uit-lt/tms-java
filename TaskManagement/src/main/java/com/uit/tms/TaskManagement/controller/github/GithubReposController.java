package com.uit.tms.TaskManagement.controller.github;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class GithubReposController {
    @GetMapping("/user/repos")
    public ResponseEntity<String> getRepo(
            @RequestHeader("X-GitHub-Event") String githubEvent
    ) {
       
        return ResponseEntity.ok("Webhook received");
    }
}
