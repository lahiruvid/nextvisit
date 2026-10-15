package com.nextvisit.visitapi.adapter.messaging;

import com.nextvisit.visitapi.application.port.VisitRecorded;
import com.nextvisit.visitapi.domain.Visit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "nextvisit.kafka.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaVisitPublisher implements VisitRecorded {

    static final String TOPIC = "visits.recorded";

    private final KafkaTemplate<String, VisitRecordedMessage> kafka;

    public KafkaVisitPublisher(KafkaTemplate<String, VisitRecordedMessage> kafka) {
        this.kafka = kafka;
    }

    @Override
    public void publish(Visit visit) {
        kafka.send(TOPIC, visit.id().toString(), new VisitRecordedMessage(
                visit.id(),
                visit.customerName(),
                visit.visitDate().toString(),
                visit.serviceType()
        ));
    }
}
