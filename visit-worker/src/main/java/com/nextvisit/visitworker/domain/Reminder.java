package com.nextvisit.visitworker.domain;

import java.util.UUID;

public final class Reminder {

    private final UUID visitId;
    private final ReminderStatus status;

    private Reminder(UUID visitId, ReminderStatus status) {
        this.visitId = visitId;
        this.status = status;
    }

    public static Reminder queued(UUID visitId) {
        return new Reminder(visitId, ReminderStatus.QUEUED);
    }

    public static Reminder restore(UUID visitId, ReminderStatus status) {
        return new Reminder(visitId, status);
    }

    public Reminder done() {
        return new Reminder(visitId, ReminderStatus.DONE);
    }

    public UUID visitId() {
        return visitId;
    }

    public ReminderStatus status() {
        return status;
    }
}
