package com.nextvisit.visitworker.application;

import com.nextvisit.visitworker.application.port.ReminderQueue;
import com.nextvisit.visitworker.application.port.ReminderRepository;
import com.nextvisit.visitworker.domain.Reminder;
import com.nextvisit.visitworker.domain.ReminderStatus;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RecordReminderTest {

    private final ReminderRepository reminders = mock(ReminderRepository.class);
    private final ReminderQueue queue = mock(ReminderQueue.class);
    private final RecordReminder recordReminder = new RecordReminder(reminders, queue);

    @Test
    void queuesOneReminder() {
        UUID visitId = UUID.randomUUID();
        when(reminders.findByVisitId(visitId)).thenReturn(Optional.empty());

        recordReminder.execute(visitId);

        verify(reminders).save(any(Reminder.class));
        verify(queue).send(visitId);
    }

    @Test
    void doesNotQueueTheSameVisitAgain() {
        UUID visitId = UUID.randomUUID();
        when(reminders.findByVisitId(visitId)).thenReturn(Optional.of(Reminder.queued(visitId)));

        recordReminder.execute(visitId);

        verify(reminders, never()).save(any());
        verify(queue, never()).send(visitId);
    }

    @Test
    void aFinishedJobIsNotQueuedAgain() {
        UUID visitId = UUID.randomUUID();
        when(reminders.findByVisitId(visitId)).thenReturn(Optional.of(Reminder.queued(visitId).done()));

        recordReminder.execute(visitId);

        verify(queue, never()).send(visitId);
        org.junit.jupiter.api.Assertions.assertEquals(ReminderStatus.DONE, Reminder.queued(visitId).done().status());
    }
}
