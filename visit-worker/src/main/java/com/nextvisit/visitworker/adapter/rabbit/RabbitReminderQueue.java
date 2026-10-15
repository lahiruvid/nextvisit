package com.nextvisit.visitworker.adapter.rabbit;

import com.nextvisit.visitworker.application.port.ReminderQueue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnProperty(name = "nextvisit.messaging.enabled", havingValue = "true", matchIfMissing = true)
public class RabbitReminderQueue implements ReminderQueue {

    private final RabbitTemplate rabbit;

    public RabbitReminderQueue(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void send(UUID visitId) {
        rabbit.convertAndSend(RabbitConfig.QUEUE, visitId.toString());
    }
}
