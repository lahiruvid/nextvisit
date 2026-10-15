package com.nextvisit.visitapi.application;

import com.nextvisit.visitapi.application.port.VisitRepository;
import com.nextvisit.visitapi.domain.Visit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetVisitTest {

    @Mock
    private VisitRepository visits;

    @InjectMocks
    private GetVisit getVisit;

    @Test
    void returnsEmptyWhenTheVisitIsMissing() {
        UUID id = UUID.randomUUID();
        when(visits.findById(id)).thenReturn(Optional.empty());

        assertTrue(getVisit.execute(id).isEmpty());
    }

    @Test
    void returnsTheSavedVisit() {
        Visit visit = Visit.create("Okitha", LocalDate.of(2026, 10, 14), "Cleaning");
        when(visits.findById(visit.id())).thenReturn(Optional.of(visit));

        assertTrue(getVisit.execute(visit.id()).isPresent());
    }
}
