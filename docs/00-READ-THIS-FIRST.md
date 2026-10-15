# Read this first

Talk to Lahiru in short, plain English. Short sentences. No long paragraphs.

Any new AI chat must follow this file. Do not restart the product decision.

1. Read `01-PRODUCT-DECISION.md`.
2. Read `02-ARCHITECTURE.md`.
3. Read `04-DECISIONS-LOCKED.md`.
4. Do only the next unchecked step in `03-BUILD-STEPS.md`.
5. When a step is done, mark it done and add one line to `05-CHANGE-LOG.md`.

## Rules

- The product is NextVisit. ApproveTrail and ClaimLedger are retired.
- Tech stack stays: Java, Spring Boot, PostgreSQL, Kafka, RabbitMQ, hexagonal layout, Docker Compose. Kubernetes only after Compose works.
- Do not add a third service until both current services run.
- Do not add React, login, payments, or an AI chatbot in the first build.
- Do not call this app ArkaTasks. ArkaTasks is a different product. NextVisit is the one to sell.
- Kafka stores a fact. RabbitMQ runs a job. Do not swap them.
- PostgreSQL is the truth. A message is not the truth.

## Done for version 1

- `visit-api` saves one visit and publishes one Kafka fact.
- `visit-worker` reads that fact, puts one reminder job on RabbitMQ, and marks it done.
- A retry does not create a second visit.
- Both start with Docker Compose.
