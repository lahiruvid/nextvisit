package com.nextvisit.visitworker.application;

import com.nextvisit.visitworker.application.port.ReminderRepository;
import com.nextvisit.visitworker.domain.Reminder;

import java.util.Optional;
import java.util.UUID;

public class GetReminder {

    private final ReminderRepository reminders;

    public GetReminder(ReminderRepository reminders) {
        this.reminders = reminders;
    }

    public Optional<Reminder> execute(UUID visitId) {
        return reminders.findByVisitId(visitId);
    }
}
