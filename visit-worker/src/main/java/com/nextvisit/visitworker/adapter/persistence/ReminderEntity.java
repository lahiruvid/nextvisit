package com.nextvisit.visitworker.adapter.persistence;

import com.nextvisit.visitworker.domain.ReminderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "reminders")
public class ReminderEntity {

    @Id
    private UUID visitId;

    @Enumerated(EnumType.STRING)
    private ReminderStatus status;

    protected ReminderEntity() {
    }

    public ReminderEntity(UUID visitId, ReminderStatus status) {
        this.visitId = visitId;
        this.status = status;
    }

    public UUID getVisitId() {
        return visitId;
    }

    public ReminderStatus getStatus() {
        return status;
    }

    public void setStatus(ReminderStatus status) {
        this.status = status;
    }
}
