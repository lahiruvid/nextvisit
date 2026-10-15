# Build steps

- [x] 1. Product ideas ClaimLedger and ApproveTrail. Retired.
- [x] 1b. Product locked: NextVisit. 14 October 2026.
- [x] 2. Create `visit-api/`, `visit-worker/`, and `docker-compose.yml`.
- [x] 3. `visit-api`: save a visit in PostgreSQL. `POST /visits` and `GET /visits/{id}`. JUnit for the rule.
- [x] 4. `visit-api`: after a successful save, publish `visits.recorded`.
- [x] 5. `visit-worker`: read that fact, enqueue one RabbitMQ reminder, mark the job `DONE`. A second Kafka delivery does not queue a second job.
- [x] 6. Docker Compose. One command starts both databases, Kafka, RabbitMQ, and both services.
- [x] 7. README with the interview sentence and how to run it.
- [ ] 8. Kubernetes only after step 6.
- [x] 9. Small React page. Save a visit and see the reminder status. Asked for before Kubernetes.
- [ ] 10. CV line only after GitHub is public and Compose works.

## CV line, do not use early

Personal project: NextVisit. Two Spring Boot services, hexagonal API, PostgreSQL, Kafka for the saved visit, RabbitMQ for the reminder job, Docker Compose. GitHub: LINK.
