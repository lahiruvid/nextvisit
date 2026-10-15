package com.nextvisit.visitapi.domain;

import java.time.LocalDate;
import java.util.UUID;

public final class Visit {

    private final UUID id;
    private final String customerName;
    private final LocalDate visitDate;
    private final String serviceType;
    private final VisitStatus status;

    private Visit(UUID id, String customerName, LocalDate visitDate, String serviceType, VisitStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.visitDate = visitDate;
        this.serviceType = serviceType;
        this.status = status;
    }

    public static Visit create(String customerName, LocalDate visitDate, String serviceType) {
        if (customerName == null || customerName.isBlank()) {
            throw new InvalidVisit("Customer name is required");
        }
        if (visitDate == null) {
            throw new InvalidVisit("Visit date is required");
        }
        if (serviceType == null || serviceType.isBlank()) {
            throw new InvalidVisit("Service type is required");
        }
        return new Visit(
                UUID.randomUUID(),
                customerName.trim(),
                visitDate,
                serviceType.trim(),
                VisitStatus.STORED
        );
    }

    public static Visit restore(UUID id, String customerName, LocalDate visitDate, String serviceType, VisitStatus status) {
        return new Visit(id, customerName, visitDate, serviceType, status);
    }

    public UUID id() {
        return id;
    }

    public String customerName() {
        return customerName;
    }

    public LocalDate visitDate() {
        return visitDate;
    }

    public String serviceType() {
        return serviceType;
    }

    public VisitStatus status() {
        return status;
    }
}
