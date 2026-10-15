package com.nextvisit.visitapi.adapter.messaging;

import com.nextvisit.visitapi.application.port.VisitRecorded;
import com.nextvisit.visitapi.domain.Visit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "nextvisit.kafka.enabled", havingValue = "false")
public class NoOpVisitPublisher implements VisitRecorded {

    @Override
    public void publish(Visit visit) {
        // Tests and a PC without Kafka still save the visit.
    }
}
