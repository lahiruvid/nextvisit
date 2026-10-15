package com.nextvisit.visitworker.application;

import com.nextvisit.visitworker.application.port.ReminderQueue;
import com.nextvisit.visitworker.application.port.ReminderRepository;
import com.nextvisit.visitworker.domain.Reminder;

import java.util.UUID;

public class RecordReminder {

    private final ReminderRepository reminders;
    private final ReminderQueue queue;

    public RecordReminder(ReminderRepository reminders, ReminderQueue queue) {
        this.reminders = reminders;
        this.queue = queue;
    }

    public void execute(UUID visitId) {
        if (reminders.findByVisitId(visitId).isPresent()) {
            return;
        }
        reminders.save(Reminder.queued(visitId));
        queue.send(visitId);
    }
}
