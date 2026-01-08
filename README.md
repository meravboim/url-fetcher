# Url Fetcher

## Overview
A full-stack system designed to fetch and track URL statuses.
Backend: Java Spring Boot service managing the fetching logic and status tracking.
Frontend: React (Vite) dashboard for real-time visualization and URL submission.

## Prerequisites
1. Docker Desktop installed and running.
2. Java 21 (If you wish to build the backend manually outside of Docker).

## Run The Application
The easiest way to run the entire stack is using Docker Compose. This will build both the frontend and backend images and orchestrate the networking between them.

1. Clone the repository (if you haven't already).
2. Build and Start: Run the following command from the root directory:
```bash
docker-compose up --build
```

## Access the services:
Frontend UI: http://localhost:3000

Backend API: http://localhost:8080/urls