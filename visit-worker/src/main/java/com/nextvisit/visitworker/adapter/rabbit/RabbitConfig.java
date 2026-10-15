package com.nextvisit.visitworker.adapter.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "nextvisit.messaging.enabled", havingValue = "true", matchIfMissing = true)
public class RabbitConfig {

    public static final String QUEUE = "visit.reminders";

    @Bean
    Queue visitReminders() {
        return new Queue(QUEUE, true);
    }
}
