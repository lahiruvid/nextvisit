# Change log

## 2026-10-14

- Created the folder and locked ClaimLedger.
- Market check the same day. ClaimLedger retired.
- Locked ApproveTrail: store one proposed action, then queue one follow-up job.
- Reason: claims platforms are a large market already led by Guidewire, Duck Creek, and others. The growing buy is an audit record and a work queue for actions, including actions an assistant wants to take.
- No application code yet. Next step is repo layout in `03-BUILD-STEPS.md` step 2.
- Market research saved in `06-MARKET-RESEARCH.md`. ApproveTrail is a crowded AI-record category. The more stable sale is a job record for a service business. The two services stay.
- Product to sell locked as NextVisit. Same tech stack. A shop saves one visit. The worker queues one reminder. ClaimLedger and ApproveTrail stay retired.
- Built `visit-api` only. It saves a visit and reads it back. No Kafka, no RabbitMQ, no circuit breaker.
- Moved use cases under `application`. Added Mockito tests for save and read. JUnit still runs every test.
- `visit-api` publishes `visits.recorded` after a successful save. Tests turn Kafka off and use H2.
- Added `visit-worker`. It has its own PostgreSQL database. It reads the Kafka fact, queues one RabbitMQ reminder, and marks that row DONE. A repeat fact does not queue a second job.
- Docker Compose now starts both databases, Kafka, RabbitMQ, and both services.
- Worker can show one reminder by visit id. A small React page saves a visit and reads that reminder. The page is part of Docker Compose on port 3000.
