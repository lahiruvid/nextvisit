# Architecture

Tech stack is fixed. Do not change it.

## Two services

### visit-api

Spring Boot. Hexagonal.

- `POST /visits` checks the visit, saves one row in PostgreSQL, publishes Kafka topic `visits.recorded`.
- `GET /visits/{id}` reads that row.

Domain rule: a visit needs a customer name, a date, and a service type. The domain does not import Kafka or Spring web.

### visit-worker

Second Spring Boot program. Own folder. Own Docker image.

- Reads `visits.recorded`.
- Puts one job on RabbitMQ queue `visit.reminders`.
- Listener marks that job `DONE` in the worker table.

A retry must not insert a second visit.

## Not in version 1

React, Kubernetes, login, payments, WhatsApp, an AI model.

Kubernetes only after Docker Compose works.
