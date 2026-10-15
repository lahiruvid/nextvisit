package com.nextvisit.visitapi.adapter.persistence;

import com.nextvisit.visitapi.application.port.VisitRepository;
import com.nextvisit.visitapi.domain.Visit;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresVisitRepository implements VisitRepository {

    private final VisitJpaRepository jpa;

    public PostgresVisitRepository(VisitJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(Visit visit) {
        jpa.save(new VisitEntity(
                visit.id(),
                visit.customerName(),
                visit.visitDate(),
                visit.serviceType(),
                visit.status()
        ));
    }

    @Override
    public Optional<Visit> findById(UUID id) {
        return jpa.findById(id).map(row -> Visit.restore(
                row.getId(),
                row.getCustomerName(),
                row.getVisitDate(),
                row.getServiceType(),
                row.getStatus()
        ));
    }
}
