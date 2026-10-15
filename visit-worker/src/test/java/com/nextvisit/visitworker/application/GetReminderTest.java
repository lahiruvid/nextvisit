package com.nextvisit.visitworker.application;

import com.nextvisit.visitworker.application.port.ReminderRepository;
import com.nextvisit.visitworker.domain.Reminder;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetReminderTest {

    @Test
    void returnsTheStoredReminder() {
        ReminderRepository reminders = mock(ReminderRepository.class);
        GetReminder getReminder = new GetReminder(reminders);
        UUID visitId = UUID.randomUUID();
        when(reminders.findByVisitId(visitId)).thenReturn(Optional.of(Reminder.queued(visitId).done()));

        Optional<Reminder> found = getReminder.execute(visitId);

        assertTrue(found.isPresent());
        assertEquals(visitId, found.get().visitId());
    }
}
