package com.nextvisit.visitworker.adapter.rabbit;

import com.nextvisit.visitworker.application.FinishReminder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnProperty(name = "nextvisit.messaging.enabled", havingValue = "true", matchIfMissing = true)
public class ReminderJobListener {

    private final FinishReminder finishReminder;

    public ReminderJobListener(FinishReminder finishReminder) {
        this.finishReminder = finishReminder;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void onReminder(String visitId) {
        finishReminder.execute(UUID.fromString(visitId));
    }
}
