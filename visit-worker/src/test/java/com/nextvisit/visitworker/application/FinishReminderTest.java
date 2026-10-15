package com.nextvisit.visitworker.application;

import com.nextvisit.visitworker.application.port.ReminderRepository;
import com.nextvisit.visitworker.domain.Reminder;
import com.nextvisit.visitworker.domain.ReminderStatus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FinishReminderTest {

    @Test
    void marksTheReminderDone() {
        ReminderRepository reminders = mock(ReminderRepository.class);
        FinishReminder finishReminder = new FinishReminder(reminders);
        UUID visitId = UUID.randomUUID();
        when(reminders.findByVisitId(visitId)).thenReturn(Optional.of(Reminder.queued(visitId)));

        finishReminder.execute(visitId);

        ArgumentCaptor<Reminder> saved = ArgumentCaptor.forClass(Reminder.class);
        verify(reminders).save(saved.capture());
        assertEquals(ReminderStatus.DONE, saved.getValue().status());
        assertEquals(visitId, saved.getValue().visitId());
    }
}
