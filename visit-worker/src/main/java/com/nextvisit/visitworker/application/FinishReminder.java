package com.nextvisit.visitworker.application;

import com.nextvisit.visitworker.application.port.ReminderRepository;

import java.util.UUID;

public class FinishReminder {

    private final ReminderRepository reminders;

    public FinishReminder(ReminderRepository reminders) {
        this.reminders = reminders;
    }

    public void execute(UUID visitId) {
        reminders.findByVisitId(visitId).ifPresent(reminder -> reminders.save(reminder.done()));
    }
}
