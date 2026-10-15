package com.nextvisit.visitapi.adapter.messaging;

import com.nextvisit.visitapi.domain.Visit;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class KafkaVisitPublisherTest {

    @Test
    void publishesTheSavedVisit() {
        @SuppressWarnings("unchecked")
        KafkaTemplate<String, VisitRecordedMessage> kafka = mock(KafkaTemplate.class);
        KafkaVisitPublisher publisher = new KafkaVisitPublisher(kafka);
        Visit visit = Visit.create("Nimal", LocalDate.of(2026, 5, 20), "Deep clean");

        publisher.publish(visit);

        verify(kafka).send(
                "visits.recorded",
                visit.id().toString(),
                new VisitRecordedMessage(visit.id(), "Nimal", "2026-05-20", "Deep clean")
        );
    }
}
