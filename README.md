# TMS Java App

Task Management System built with **Spring Boot** and **MySQL**, containerized using **Docker Compose**.

## 🚀 Getting Started

### Prerequisites

- Docker and Docker Compose
- Git
- Java 17 (for local dev, optional if using Docker)

### Installation and Setup

1. Clone the repository
   ```bash
   git clone <repository-url>
   cd tms-java/TaskManagement
   ```

2. Create environment file
   ```bash
   cp .env.example .env
   ./scripts/generate-nginx-config.sh
   ```

3. Build and start the services
   ```bash
   docker-compose up --build -d
   ```

4. Access the application at http://tms.uit.local:8095

   > **Note**: Add the domain to your hosts file:
   > ```
   > 127.0.0.1    tms.uit.local
   > ```

## 🛠️ Development

### Project Structure

```
TaskManagement/
├── src/                         # Source code (Java Spring Boot)
│   └── main/java/com.uit.tms.TaskManagement
├── docker/                      # Docker and nginx configs
│   └── nginx/templates/
│   └── logs/                    # Nginx logs 
├── .env.example                 # Environment variable template
├── docker-compose.yml           # Compose setup
├── Dockerfile                   # Spring Boot build file
├── pom.xml                      # Maven configuration
```

### Database

The database is provisioned using MySQL 8.0, and the volume is persisted in `db_data`.

## 📄 License

This project is licensed under the MIT License.
