package com.nextvisit.visitapi.adapter.messaging;

import java.util.UUID;

public record VisitRecordedMessage(UUID id, String customerName, String visitDate, String serviceType) {
}
