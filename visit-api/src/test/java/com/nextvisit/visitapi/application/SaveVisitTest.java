package com.nextvisit.visitapi.application;

import com.nextvisit.visitapi.application.port.VisitRecorded;
import com.nextvisit.visitapi.application.port.VisitRepository;
import com.nextvisit.visitapi.domain.InvalidVisit;
import com.nextvisit.visitapi.domain.Visit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class SaveVisitTest {

    @Mock
    private VisitRepository visits;

    @Mock
    private VisitRecorded events;

    @InjectMocks
    private SaveVisit saveVisit;

    @Test
    void savesOneVisit() {
        saveVisit.execute("Okitha", LocalDate.of(2026, 10, 14), "Cleaning");

        ArgumentCaptor<Visit> saved = ArgumentCaptor.forClass(Visit.class);
        verify(visits).save(saved.capture());
        verify(events).publish(saved.getValue());
        assertEquals("Okitha", saved.getValue().customerName());
    }

    @Test
    void doesNotSaveAnInvalidVisit() {
        assertThrows(InvalidVisit.class, () -> saveVisit.execute(" ", LocalDate.of(2026, 10, 14), "Cleaning"));

        verify(visits, never()).save(org.mockito.ArgumentMatchers.any());
        verifyNoInteractions(events);
    }
}
