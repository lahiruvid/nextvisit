package com.nextvisit.visitapi.application.port;

import com.nextvisit.visitapi.domain.Visit;

import java.util.Optional;
import java.util.UUID;

public interface VisitRepository {

    void save(Visit visit);

    Optional<Visit> findById(UUID id);
}
