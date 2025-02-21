# Submersible Probe API

## Overview
The **Submersible Probe API** is a RESTful service that allows controlling a remotely operated probe. The probe moves within a defined grid, avoiding obstacles and tracking visited positions. It supports movement commands such as:
- Move Forward (`F`)
- Move Backward (`B`)
- Turn Left (`L`)
- Turn Right (`R`)

## Features
- REST API to control and track the probe.
- Command Pattern for executing movements.
- Obstacle detection and boundary constraints.
- Logging and error handling with custom exceptions.
- Unit tests using **JUnit** and **Mockito**.

## Tech Stack
- **Java 17**
- **Spring Boot**
- **JUnit 5 & Mockito** (for testing)
- **SLF4J & Logback** (for logging)

## Project Structure
```
/src/main/java/com/example/probe
│── controller/          # REST API controllers
│── service/             # Business logic layer
│── models/              # Data models for probe, position, etc.
│── commands/            # Command pattern classes
│── exceptions/          # Custom exceptions
│── dto/                 # Data Transfer Objects
│── SubmersibleProbeApiApplication.java  # Main Spring Boot application
/src/test/java/com/example/probe
│── controller/          # Unit tests for controllers
│── service/             # Unit tests for services
```

## API Endpoints

### **1. Execute Commands**
**Endpoint:** `POST /probe/execute`

**Request Body:**
```json
{
  "commands": "FFL"
}
```

**Response:**
```json
{
  "x": 0,
  "y": 2,
  "direction": "WEST",
  "visitedPositions": [ {"x": 0, "y": 0}, {"x": 0, "y": 1}, {"x": 0, "y": 2} ]
}
```

### **2. Get Visited Positions**
**Endpoint:** `GET /probe/visited`

**Response:**
```json
[
  { "x": 0, "y": 1 },
  { "x": 1, "y": 2 }
]
```

## How to Run
1. **Clone the repository:**
   ```sh
   git clone <repo-url>
   cd submersible-probe-api
   ```
2. **Build and run the application:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
3. **Run tests:**
   ```sh
   mvn test
   ```

## Testing
This project includes unit tests for the controller and service layers. Run the tests using:
```sh
mvn test
```

## Author
Developed by **Manik Goyal**

