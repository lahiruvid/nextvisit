package com.nextvisit.visitworker.adapter.kafka;

import com.nextvisit.visitworker.application.RecordReminder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "nextvisit.messaging.enabled", havingValue = "true", matchIfMissing = true)
public class VisitRecordedListener {

    private final RecordReminder recordReminder;

    public VisitRecordedListener(RecordReminder recordReminder) {
        this.recordReminder = recordReminder;
    }

    @KafkaListener(topics = "visits.recorded", groupId = "visit-worker")
    public void onVisitRecorded(VisitFact fact) {
        if (fact == null || fact.id() == null) {
            return;
        }
        recordReminder.execute(fact.id());
    }
}
