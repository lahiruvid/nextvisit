package com.nextvisit.visitapi.application;

import com.nextvisit.visitapi.application.port.VisitRecorded;
import com.nextvisit.visitapi.application.port.VisitRepository;
import com.nextvisit.visitapi.domain.Visit;

import java.time.LocalDate;

public class SaveVisit {

    private final VisitRepository visits;
    private final VisitRecorded events;

    public SaveVisit(VisitRepository visits, VisitRecorded events) {
        this.visits = visits;
        this.events = events;
    }

    public Visit execute(String customerName, LocalDate visitDate, String serviceType) {
        Visit visit = Visit.create(customerName, visitDate, serviceType);
        visits.save(visit);
        events.publish(visit);
        return visit;
    }
}
