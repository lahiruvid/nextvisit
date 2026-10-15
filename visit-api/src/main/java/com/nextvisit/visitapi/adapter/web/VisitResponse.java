package com.nextvisit.visitapi.adapter.web;

import com.nextvisit.visitapi.domain.Visit;

import java.time.LocalDate;
import java.util.UUID;

public record VisitResponse(
        UUID id,
        String customerName,
        LocalDate visitDate,
        String serviceType,
        String status
) {
    public static VisitResponse from(Visit visit) {
        return new VisitResponse(
                visit.id(),
                visit.customerName(),
                visit.visitDate(),
                visit.serviceType(),
                visit.status().name()
        );
    }
}
