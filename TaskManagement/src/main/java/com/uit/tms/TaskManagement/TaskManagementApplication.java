package com.uit.tms.TaskManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
		String hostName = System.getenv("NGINX_HOST_NAME");
		String hostPort = System.getenv("NGINX_HOST_HTTP_PORT");

		if (hostName == null || hostPort == null) {
			System.out.println("NGINX_HOST_NAME or NGINX_HOST_HTTP_PORT is not set.");
			return;
		} else {
			System.out.println("| ====================================================");
			System.out.println("| 🚀 Task Management Application is running at: ");
			System.out.println("| http://" + hostName + ":" + hostPort);
			System.out.println("| 🚀 Localhost is: " 
			+ "http://localhost" + ":" + System.getenv("SPRING_HOST_PORT"));
			System.out.println("| ====================================================");
		}
	}
}
