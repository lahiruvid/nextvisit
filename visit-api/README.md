# visit-api

Saves one visit and reads it back.

Not included yet: Kafka, RabbitMQ, React, circuit breaker.

## Folders

- `domain` holds the visit rule. It does not know Spring or the database.
- `application` decides to save or read. Tests here use JUnit and Mockito, with a fake database.
- `adapter/web` is the HTTP door.
- `adapter/persistence` is the real PostgreSQL door.

## Run the database

From the project root:

```
docker compose up -d
```

## Run the API

Needs JDK 21 and Maven.

```
mvn spring-boot:run
```

## Tests

```
mvn test
```

- `VisitTest` checks the rule with JUnit only.
- `SaveVisitTest` and `GetVisitTest` use Mockito so PostgreSQL is not started.
- `VisitControllerTest` starts the app with a temporary database and calls the real HTTP path.
