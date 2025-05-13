package com.uit.tms.TaskManagement.controller.github;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.uit.tms.TaskManagement.constants.Endpoint;
import com.uit.tms.TaskManagement.constants.TemplateName;
import com.uit.tms.TaskManagement.entity.UserEntity;
import com.uit.tms.TaskManagement.model.Project;
import com.uit.tms.TaskManagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GithubController {

    private final UserRepository userRepository;

    @GetMapping(Endpoint.GITHUB_REPOS)
    public String githubReposPage(Authentication authentication, Model model) {
        if (authentication == null) {
            return "redirect:" + Endpoint.LOGIN;
        }

        String username = authentication.getName();
        Optional<UserEntity> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return TemplateName.ERROR_404;
        }

        String accessToken = userOpt.get().getGithubAccessToken();
        List<String> repos = fetchGitHubRepos(accessToken);

        List<Project> projects = new ArrayList<>();
        for (long i = 0; i < repos.size(); i++) {
            String repo = repos.get((int) i);
            projects.add(new Project(i, repo));
        }
       
        model.addAttribute("projects", projects);
        return TemplateName.GITHUB_REPOS;
    }

    private List<String> fetchGitHubRepos(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                "https://api.github.com/user/repos",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });

        List<Map<String, Object>> responseBody = response.getBody();
        if (responseBody == null) {
            return List.of(); // Return an empty list if the body is null
        }

        return responseBody.stream()
                .map(repo -> (String) repo.get("full_name"))
                .toList();
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/github-webhook")
    public ResponseEntity<String> handleWebhook(
            @RequestHeader("X-GitHub-Event") String githubEvent,
            @RequestBody Map<String, Object> payload) {
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

    @PostMapping("/github/create-webhook")
    @ResponseBody
    public ResponseEntity<String> createWebhookForRepo(
            Authentication authentication,
            @RequestParam String repoFullName) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        String username = authentication.getName();
        Optional<UserEntity> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        String accessToken = userOpt.get().getGithubAccessToken();

        String[] parts = repoFullName.split("/");
        if (parts.length != 2) {
            return ResponseEntity.badRequest().body("Invalid repo name");
        }

        createWebhook(accessToken, parts[0], parts[1]);

        return ResponseEntity.ok("Webhook creation requested");
    }

    private void createWebhook(String accessToken, String owner, String repo) {
        String url = String.format("https://api.github.com/repos/%s/%s/hooks", owner, repo);
        String serverURL = System.getenv("NGINX_HOST_NAME") + ":" + System.getenv("NGINX_HOST_HTTP_PORT");
        String webhookURL = serverURL + "/github-webhook";
        Map<String, Object> payload = Map.of(
                "name", "web",
                "active", true,
                "events", List.of("issues"),
                "config", Map.of(
                        "url", webhookURL,
                        "content_type", "json",
                        "insecure_ssl", "0"));

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.set("Accept", "application/vnd.github+json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            System.out.println("Webhook created: " + response.getBody());
        } catch (Exception e) {
            System.out.println("Failed to create webhook: " + e.getMessage());
        }
    }
}