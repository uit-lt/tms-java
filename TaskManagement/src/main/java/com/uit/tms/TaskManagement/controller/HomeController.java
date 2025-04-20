package com.uit.tms.TaskManagement.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uit.tms.TaskManagement.model.Comment;
import com.uit.tms.TaskManagement.model.Platform;
import com.uit.tms.TaskManagement.model.Priority;
import com.uit.tms.TaskManagement.model.Project;
import com.uit.tms.TaskManagement.model.Tag;
import com.uit.tms.TaskManagement.model.Task;
import com.uit.tms.TaskManagement.model.TaskStatus;

@Controller
public class HomeController {
        private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

        @GetMapping("/")
        public String home(Model model) {
                // Dashboard statistics
                model.addAttribute("totalTasks", 10);
                model.addAttribute("completedTasks", 4);
                model.addAttribute("pendingTasks", 6);

                // Recent activities
                List<String> activities = Arrays.asList(
                                "Task 'Update documentation' completed",
                                "New task 'Fix navbar issue' added",
                                "Task 'Deploy application' assigned to you");
                model.addAttribute("activities", activities);

                return "home";
        }

        @GetMapping("/tasks")
        public String taskList(Model model) {
                // Sample task data
                List<Task> tasks = Arrays.asList(
                                new Task(1L, "Implement login page", "Create login UI with validation",
                                                TaskStatus.IN_PROGRESS, Priority.MEDIUM,
                                                LocalDateTime.now().plusDays(3)),
                                new Task(2L, "Design database schema", "Design initial database tables",
                                                TaskStatus.TO_DO, Priority.HIGH, LocalDateTime.now().plusDays(5)),
                                new Task(3L, "Setup CI/CD pipeline", "Configure GitHub Actions",
                                                TaskStatus.COMPLETED, Priority.LOW, LocalDateTime.now().minusDays(1)));

                // Add sample projects and platforms
                List<Project> projects = Arrays.asList(
                                new Project(1L, "Website Redesign"),
                                new Project(2L, "API Development"));

                List<Platform> platforms = Arrays.asList(
                                new Platform(1L, "JIRA", "blue"),
                                new Platform(2L, "Trello", "green"));

                model.addAttribute("tasks", tasks);
                model.addAttribute("projects", projects);
                model.addAttribute("platforms", platforms);
                model.addAttribute("statuses", TaskStatus.values());
                model.addAttribute("priorities", Priority.values());

                return "tasks/list";
        }

        @GetMapping("/task/{id}")
        public String taskDetail(@PathVariable Long id, Model model) {
                // Sample task with details
                Task task = new Task(
                                id,
                                "Implement user authentication",
                                "Create login, registration and password reset flows with OAuth2 support",
                                TaskStatus.IN_PROGRESS,
                                Priority.HIGH,
                                LocalDateTime.now().plusDays(7));
                task.setProject(new Project(1L, "Website Redesign"));
                task.setPlatform(new Platform(1L, "JIRA", "blue"));
                task.setCreatedBy("admin");
                task.setCreatedAt(LocalDateTime.now().minusDays(2));

                List<Comment> comments = Arrays.asList(
                                new Comment("Let's focus on the basic auth first", LocalDateTime.now().minusDays(1),
                                                "John Doe"),
                                new Comment("Don't forget about password recovery", LocalDateTime.now().minusHours(3),
                                                "Jane Smith"));
                List<Tag> tags = new ArrayList<>();
                tags.add(new Tag(1L, "Frontend"));
                tags.add(new Tag(2L, "Urgent"));
                task.setTags(tags);

                model.addAttribute("task", task);
                model.addAttribute("task", task);
                model.addAttribute("comments", comments);
                return "tasks/detail";
        }

        @GetMapping("/task/new")
        public String newTaskForm(Model model) {
                // Add empty task and selection options
                model.addAttribute("task", new Task());
                model.addAttribute("projects", Arrays.asList(
                                new Project(1L, "Website Redesign"),
                                new Project(2L, "API Development")));
                model.addAttribute("platforms", Arrays.asList(
                                new Platform(1L, "JIRA", "blue"),
                                new Platform(2L, "Trello", "green")));
                model.addAttribute("statuses", TaskStatus.values());
                model.addAttribute("priorities", Priority.values());

                return "tasks/form";
        }

        @GetMapping("/tasks/{id}/edit")
        public String editTaskForm(@PathVariable Long id, Model model) {
                // Get existing task (in real app, this would come from service)
                Task task = new Task(
                                id,
                                "Implement user authentication",
                                "Create login, registration and password reset flows with OAuth2 support",
                                TaskStatus.IN_PROGRESS,
                                Priority.HIGH,
                                LocalDateTime.now().plusDays(7));
                task.setProject(new Project(1L, "Website Redesign"));
                task.setPlatform(new Platform(1L, "JIRA", "blue"));
                System.out.println("Task: " + task);
                logger.info("Task: {}", task);
                model.addAttribute("task", task);
                model.addAttribute("projects", Arrays.asList(
                                new Project(1L, "Website Redesign"),
                                new Project(2L, "API Development")));
                model.addAttribute("platforms", Arrays.asList(
                                new Platform(1L, "JIRA", "blue"),
                                new Platform(2L, "Trello", "green")));
                model.addAttribute("statuses", TaskStatus.values());
                model.addAttribute("priorities", Priority.values());

                return "tasks/form";
        }

        @PostMapping("/task/{id}/run")
        public String runTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
                // In a real application, you would retrieve the task from a repository
                Task task = new Task(
                                id,
                                "Implement user authentication",
                                "Create login, registration and password reset flows with OAuth2 support",
                                TaskStatus.IN_PROGRESS,
                                Priority.HIGH,
                                LocalDateTime.now().plusDays(7));

                // Set execution in progress
                task.setExecutionInProgress(true);
                task.setLastExecuted(LocalDateTime.now());

                // Simulate asynchronous task execution
                CompletableFuture.runAsync(() -> {
                        try {
                                // Simulate task execution time
                                Thread.sleep(5000);

                                // Update task status (in a real app, save to database)
                                task.setExecutionInProgress(false);
                                task.setExecutionResult("Task executed successfully");

                                // Optionally update task status based on execution result
                                if (Math.random() > 0.2) { // 80% success rate for simulation
                                        task.setStatus(TaskStatus.COMPLETED);
                                }
                        } catch (InterruptedException e) {
                                task.setExecutionInProgress(false);
                                task.setExecutionResult("Task execution failed: " + e.getMessage());
                        }
                });

                // Add a flash message
                redirectAttributes.addFlashAttribute("message", "Task execution started");
                redirectAttributes.addFlashAttribute("alertClass", "alert-info");

                // Redirect to the task details page
                return "redirect:/task/" + id;
        }

        @GetMapping("/login")
        public String login() {
                return "auths/login";
        }

        @GetMapping("/register")
        public String register() {
                return "auths/register";
        }

        @GetMapping("/user")
        public String user() {
                return "auths/user";
        }

        @GetMapping("/profile")
        public String profile() {
                return "auths/profile";
        }

        @GetMapping("/forgot-password")
        public String forgotPassword() {
                return "auths/forgot";
        }

        @GetMapping("/reset-password")
        public String resetPassword() {
                return "auths/reset";
        }

        @GetMapping("/projects")
        public String projectList(Model model) {
                // Sample project data
                List<Project> projects = Arrays.asList(
                                new Project(1L, "Website Redesign"),
                                new Project(2L, "API Development"),
                                new Project(3L, "Mobile App Development"));

                model.addAttribute("projects", projects);
                return "projects/list";
        }

        @GetMapping("/projects/new")
        public String newProjectForm(Model model) {
                return "projects/form";
        }

        @GetMapping("/projects/{id}/edit")
        public String editProjectForm(@PathVariable Long id, Model model) {
                // Mock data for editing a project
                Project project = new Project(id, "Mock Project Name", "Mock Project Description",
                                LocalDate.now(), LocalDate.now().plusDays(30), Priority.MEDIUM, "In Progress");
                project.setId(id);
                project.setName("Project " + id);
                System.out.println("Project: " + project);
                logger.info("Project: {}", project);
                model.addAttribute("project", project);
                return "projects/form";
        }

}