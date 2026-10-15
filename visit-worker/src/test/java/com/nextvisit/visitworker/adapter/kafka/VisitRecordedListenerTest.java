package com.nextvisit.visitworker.adapter.kafka;

import com.nextvisit.visitworker.application.RecordReminder;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class VisitRecordedListenerTest {

    private final RecordReminder recordReminder = mock(RecordReminder.class);
    private final VisitRecordedListener listener = new VisitRecordedListener(recordReminder);

    @Test
    void recordsTheVisitId() {
        UUID visitId = UUID.randomUUID();

        listener.onVisitRecorded(new VisitFact(visitId, "Nimal", "2026-05-20", "Deep clean"));

        verify(recordReminder).execute(visitId);
    }

    @Test
    void ignoresAFactWithNoId() {
        listener.onVisitRecorded(new VisitFact(null, "Nimal", "2026-05-20", "Deep clean"));

        verify(recordReminder, never()).execute(org.mockito.ArgumentMatchers.any());
    }
}
