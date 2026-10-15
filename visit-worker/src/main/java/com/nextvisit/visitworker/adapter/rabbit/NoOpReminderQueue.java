package com.nextvisit.visitworker.adapter.rabbit;

import com.nextvisit.visitworker.application.port.ReminderQueue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnProperty(name = "nextvisit.messaging.enabled", havingValue = "false")
public class NoOpReminderQueue implements ReminderQueue {

    @Override
    public void send(UUID visitId) {
        // Used when tests run without RabbitMQ.
    }
}
