package com.nextvisit.visitapi.adapter.web;

import java.time.LocalDate;

public record VisitRequest(String customerName, LocalDate visitDate, String serviceType) {
}
