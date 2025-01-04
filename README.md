# Learning Application Platform - README

## Overview
This application is a robust learning platform designed to provide training in various fields, including First Aid and Anatomy. The platform includes Android and web clients, enabling participants to enroll in courses, take quizzes, and earn certifications upon successful completion. Advanced features include Augmented Reality (AR) modules for interactive learning and Artificial Intelligence (AI) tools to provide feedback on CPR training using video analysis. 

The platform is built on a microservices architecture and follows modern development and deployment practices, such as CI/CD pipelines, containerization, and observability.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Architecture](#architecture)
  - [Global Architecture](#global-architecture)
  - [CI/CD Pipeline Architecture](#cicd-pipeline-architecture)
- [Functionalities](#functionalities)
- [Project Structure](#project-structure)
- [CI/CD Pipeline](#cicd-pipeline)
- [Docker Configuration](#docker-configuration)
- [Getting Started](#getting-started)
- [Deployment](#deployment)
- [Monitoring and Logging](#monitoring-and-logging)
- [Security Considerations](#security-considerations)
- [Demo](#demo)
- [Contributors](#contributors)
- [License](#license)

## Prerequisites

### Development Environment
- Java JDK 11 or higher
- Node.js 14.x or higher
- Android Studio 4.x or higher
- Git
- Docker and Docker Compose

### Infrastructure Requirements
- Jenkins server (port 8080)
- SonarQube server (port 9000)
- Nexus Repository (ports 5001-5003)
- Docker Registry
- Ubuntu servers for test and production environments

## Architecture

### Global Architecture
The system consists of three main components:
- **Web Client**: A React-based frontend for both participants and administrators.
- **Mobile Client**: A native Android application tailored for participants.
- **Backend Services**: Microservices architecture to manage core functionalities like training content, quizzes, and certifications.

### CI/CD Pipeline Architecture
The CI/CD pipeline automates the build, testing, and deployment processes:
1. Developer pushes code to GitHub.
2. Jenkins triggers the build process.
3. Maven builds the project and runs unit tests.
4. SonarQube performs code analysis and quality checks.
5. Docker images are built and pushed to the Docker registry.
6. The application is deployed to test and production environments.

## Functionalities

### Participant Functionalities
- **Authentication**: Secure login via Keycloak using JWT and OpenID Connect.
- **Course Enrollment**: Browse and enroll in courses categorized by topics.
- **AR-Based Learning**: Participate in interactive AR training sessions.
- **AI-Powered Feedback**: Receive AI-driven feedback on CPR training performance.
- **Quiz and Certification**: Take quizzes after training and earn certifications.

### Administrator Functionalities
- **Course Management**: Create, update, and manage training courses.
- **Participant Monitoring**: Track participant progress and quiz results.

## Project Structure

```
├── backend/
│   ├── microservices/
│   ├── config/
│   └── docker-compose-local.yml
    └── Jenkinsfile
├── web-client/
│   ├── src/
│   └── Dockerfile
├── mobile-client/
   ├── app/
   └── Dockerfile
```

## CI/CD Pipeline

### Jenkins Pipeline Stages
1. **Source Code Management**:
   - GitHub repository integration.
   - Branch strategy: main and develop.

2. **Build & Test**:
   - Maven build and unit testing.
   - Integration testing.

3. **Code Quality**:
   - SonarQube analysis (port 9000).
   - Code coverage reports.
   - Quality gates enforcement.

4. **Artifact Management**:
   - Nexus Repository Manager for artifact storage.
   - Docker image storage and version management.

5. **Deployment**:
   - Deploy to test server (192.168.1.26).
   - Deploy to production server (192.168.1.66).

## Docker Configuration

### Docker Compose Configuration
The platform uses Docker Compose to manage its services:
- **Keycloak**: Authentication and user management.
- **MySQL**: Database for storing user data and training information.
- **Backend Microservices**: Handle course content, authentication, and notifications.
- **Observability Stack**: Includes Grafana, Prometheus, Loki, and Tempo.

A sample `docker-compose.yml` file is included for local setup.

## Getting Started

### Backend Setup
```bash
# Clone repository
git clone https://github.com/CHOUAY15/PFM-Coursey.git

# Navigate to backend directory
cd backend

# Start local environment
docker-compose -f docker-compose-local.yml up -d
```

### Web Client Setup
```bash
# Navigate to web client directory
cd web-client

# Install dependencies
npm install

# Start development server
npm start
```

### Mobile Client Setup
```bash
# Open project in Android Studio
# Configure gradle settings
# Build and run on emulator/device
```

## Deployment

### Test Environment
- **Server**: 192.168.1.26
- **Docker Configuration**: `docker-compose.yml`
- **Deployment**: Automated via Jenkins.

### Production Environment
- **Server**: 192.168.1.66
- **Docker Configuration**: `docker-compose.prod.yml`
- **Deployment Process**: Manual approval with rollback options.

### Deployment Commands

#### Development
```bash
# Local development deployment
docker-compose -f docker-compose-local.yml up -d
```

#### Testing
```bash
# Test environment deployment
docker stack deploy -c docker-compose.yml app_stack
```

#### Production
```bash
# Production deployment
docker stack deploy -c docker-compose.yml app_stack
```

## Monitoring and Logging

- **Build Monitoring**: Jenkins pipelines track build status.
- **Code Quality Metrics**: SonarQube for static analysis and quality gates.
- **Container Logs**: Managed via Loki and visualized in Grafana.
- **Application-Level Logs**: Managed via Tempo.

## Security Considerations

- **HTTPS Enforcement**: Ensures secure communication.
- **JWT Authentication**: Secures API endpoints.
- **Docker Best Practices**: Minimizes vulnerabilities.
- **Network Segmentation**: Isolates test and production environments.

## Demo
A detailed video demonstration is available [here](#).

## Contributors
- **CHOUAY Walid**
- **BESSAM Adam**
- **DAOUDI Mohammed**

## License
This project is licensed under the MIT License. See the LICENSE.md file for details.

