# Product decision

Date locked: 14 October 2026

## The app

**NextVisit** is for a small service shop. Cleaning, repair, or a clinic.

The shop saves one finished visit. The app stores it. Then it queues the next message to the customer or the worker. If the message fails, it tries again. It does not create a second visit.

## Why shops will buy it

A missed next visit is lost money. Shops already lose bookings when a message fails or nobody wrote the visit down.

By 2030 and 2036, a bot may write the message. The shop still needs two things:

- a saved visit
- one next job that can try again, without double booking

That need does not depend on an AI trend. The work happens every week.

## Why not the other ideas

- Insurance claims: big market, already owned by Guidewire and Duck Creek.
- AI approval record: real need, but US and European teams already buy Auditant, Arahi, and others. Hard to sell from Sri Lanka.
- ArkaTasks: a cleaning tool that already exists. NextVisit is the new product in this folder, built to sell to many shops.

## Who buys

The owner of a small cleaning company, repair shop, or clinic. They pay about $20 to $50 a month so the next visit is not lost.

## Version 1

One visit saved. One Kafka fact. One RabbitMQ reminder job. No real WhatsApp yet. No website yet.

## Interview sentence

Kafka stores the fact that the visit was saved. RabbitMQ sends the next reminder and can retry it. PostgreSQL is the truth. A retry does not create a second visit.
