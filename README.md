# NextVisit

A shop saves each visit. The app then queues the next message. If sending fails, it tries again. It does not book the same visit twice.

This folder is the source of truth. Read `docs/00-READ-THIS-FIRST.md` before changing anything.

Owner: Lahiru Abeykoon  
Product locked: 14 October 2026  
Status: both services are written. Docker Compose starts them.

Personal project: NextVisit. Two Spring Boot services, hexagonal API, PostgreSQL, Kafka for the saved visit, RabbitMQ for the reminder job, Docker Compose.

## What each program uses

| Program | Its database | How it connects |
| --- | --- | --- |
| visit-api | `nextvisit` on visit-db, port 5432 | `jdbc:postgresql://visit-db:5432/nextvisit` user `nextvisit` |
| visit-worker | `visitworker` on worker-db, port 5433 on your PC | `jdbc:postgresql://worker-db:5432/visitworker` user `visitworker` |

They do not share a database. Kafka topic `visits.recorded` carries the fact. RabbitMQ queue `visit.reminders` carries the job.

## Run it

Install Docker Desktop. Then, in this folder:

```
docker compose up --build
```

That builds the images and starts both databases, Kafka, RabbitMQ, both services, and the page.

Open the page: http://localhost:3000

Type a name, a date, and a service. Press Save visit. The page shows the stored visit. After a short wait it shows the reminder status.

Save a visit:

```
curl -X POST http://localhost:8080/visits -H "Content-Type: application/json" -d "{\"customerName\":\"Nimal\",\"visitDate\":\"2026-05-20\",\"serviceType\":\"Deep clean\"}"
```

Read it back with the id from the answer:

```
curl http://localhost:8080/visits/THE-ID
```

The worker writes one row in its own database. A second copy of the same Kafka message does not add a second row.

RabbitMQ’s page is http://localhost:15672 (user `guest`, password `guest`).

Stop it:

```
docker compose down
```

ApproveTrail and ClaimLedger are retired names. Do not build those.
