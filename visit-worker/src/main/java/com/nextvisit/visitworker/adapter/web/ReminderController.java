package com.nextvisit.visitworker.adapter.web;

import com.nextvisit.visitworker.application.GetReminder;
import com.nextvisit.visitworker.domain.Reminder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    private final GetReminder getReminder;

    public ReminderController(GetReminder getReminder) {
        this.getReminder = getReminder;
    }

    @GetMapping("/{visitId}")
    public ResponseEntity<ReminderResponse> get(@PathVariable UUID visitId) {
        return getReminder.execute(visitId)
                .map(ReminderResponse::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public record ReminderResponse(UUID visitId, String status) {
        static ReminderResponse from(Reminder reminder) {
            return new ReminderResponse(reminder.visitId(), reminder.status().name());
        }
    }
}
