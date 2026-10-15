package com.nextvisit.visitapi.application;

import com.nextvisit.visitapi.application.port.VisitRepository;
import com.nextvisit.visitapi.domain.Visit;

import java.util.Optional;
import java.util.UUID;

public class GetVisit {

    private final VisitRepository visits;

    public GetVisit(VisitRepository visits) {
        this.visits = visits;
    }

    public Optional<Visit> execute(UUID id) {
        return visits.findById(id);
    }
}
