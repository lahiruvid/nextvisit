package com.nextvisit.visitworker.adapter.kafka;

import java.util.UUID;

public record VisitFact(UUID id, String customerName, String visitDate, String serviceType) {
}
