# Decisions locked

| Decision | Choice |
| --- | --- |
| Product to sell | NextVisit. A shop saves a visit. The app queues the next reminder. A retry does not double-book. |
| Tech stack | Java 21, Spring Boot, PostgreSQL, Kafka, RabbitMQ, hexagonal API, Docker Compose. Kubernetes later. |
| Services now | `visit-api` and `visit-worker` only |
| Buyer | Owner of a small cleaning company, repair shop, or clinic |
| Price aim | $20 to $50 a month per shop |
| Retired | ClaimLedger, ApproveTrail |
| Not now | React, login, payments, AI chatbot, third service |
