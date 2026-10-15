package com.nextvisit.visitapi.adapter.persistence;

import com.nextvisit.visitapi.domain.VisitStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "visits")
public class VisitEntity {

    @Id
    private UUID id;
    private String customerName;
    private LocalDate visitDate;
    private String serviceType;

    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    protected VisitEntity() {
    }

    public VisitEntity(UUID id, String customerName, LocalDate visitDate, String serviceType, VisitStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.visitDate = visitDate;
        this.serviceType = serviceType;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public VisitStatus getStatus() {
        return status;
    }
}
