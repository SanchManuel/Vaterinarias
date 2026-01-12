# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Development commands

- Prefer the Gradle wrapper in this repo: run all commands as `./gradlew` (not `gradle`).
- Build the application (runs tests and creates the jar): `./gradlew clean build`
- Run the Spring Boot application locally: `./gradlew bootRun`
- Run the full test suite: `./gradlew test`
- Run a single test class (example):
  - `./gradlew test --tests 'com.systemReady.veterinarias.VeterinariasApplicationTests'`
- Run a single test method (example):
  - `./gradlew test --tests 'com.systemReady.veterinarias.VeterinariasApplicationTests.contextLoads'`

## Project structure and architecture

- **Build & runtime stack**
  - Java 21, built with Gradle via the Spring Boot Gradle plugin (`org.springframework.boot` 3.5.9).
  - Dependency management via `io.spring.dependency-management`.
  - Key dependencies (see `build.gradle`):
    - Web/API: `spring-boot-starter-web`
    - Persistence: `spring-boot-starter-data-jpa`, PostgreSQL driver
    - Database migrations: Flyway (`flyway-core`, `flyway-database-postgresql`)
    - Validation: `spring-boot-starter-validation`
    - Security: `spring-boot-starter-security`
    - Lombok (compile-time annotations only)

- **Modules and packages**
  - Single Spring Boot application module with root package `com.systemReady.veterinarias`.
  - Main application entry point:
    - `src/main/java/com/systemReady/veterinarias/VeterinariasApplication.java`
    - Annotated with `@SpringBootApplication` and used as the primary configuration class.

- **Configuration and resources**
  - Spring Boot configuration is centralized in:
    - `src/main/resources/application.yaml`
  - Database migration structure is set up for Flyway:
    - Migrations live under `src/main/resources/db/migration` (directory exists; add versioned SQL files here as the schema evolves).
  - Static resources and templates (for web views if/when added) are placed under:
    - `src/main/resources/static`
    - `src/main/resources/templates`

- **Testing**
  - Tests use JUnit 5 on the JUnit Platform and Spring Boot’s testing support.
  - Example test class:
    - `src/test/java/com/systemReady/veterinarias/VeterinariasApplicationTests.java` annotated with `@SpringBootTest`.
  - Test configuration is managed by Gradle via the `test` task, which is already configured to use JUnit Platform (`tasks.named("test") { useJUnitPlatform() }` in `build.gradle`).

- **Gradle files**
  - `settings.gradle` defines the root project name (`veterinarias`).
  - `build.gradle` configures plugins, Java toolchain (21), repositories, and all runtime and test dependencies.
