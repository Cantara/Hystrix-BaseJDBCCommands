# Hystrix-BaseJDBCCommands

## Purpose
Base command classes for JDBC database operations wrapped in Netflix Hystrix circuit-breaker patterns. Extends the Hystrix-BaseCommands concept to database access, providing resilient database operation abstractions.

## Tech Stack
- Language: Java 8+
- Framework: Netflix Hystrix
- Build: Maven
- Key dependencies: Hystrix, JDBC, SLF4J

## Architecture
Library providing abstract base classes for JDBC operations wrapped in Hystrix commands with circuit-breaker, timeout, and fallback support. Consumers extend these base classes to implement specific database queries and operations with built-in resilience.

## Key Entry Points
- Base JDBC command classes in `src/main/java/`
- `pom.xml` - Maven coordinates: `no.cantara.base:Hystrix-BaseJDBCCommands`

## Development
```bash
# Build
mvn clean install

# Test
mvn test
```

## Domain Context
Resilience infrastructure library for database operations. Complements Hystrix-BaseCommands (HTTP) with JDBC-specific circuit-breaker patterns, ensuring fault tolerance for database access in distributed Cantara services.
