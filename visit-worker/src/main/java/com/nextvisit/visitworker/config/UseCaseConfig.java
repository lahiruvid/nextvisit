package com.nextvisit.visitworker.config;

import com.nextvisit.visitworker.application.FinishReminder;
import com.nextvisit.visitworker.application.GetReminder;
import com.nextvisit.visitworker.application.RecordReminder;
import com.nextvisit.visitworker.application.port.ReminderQueue;
import com.nextvisit.visitworker.application.port.ReminderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    RecordReminder recordReminder(ReminderRepository reminders, ReminderQueue queue) {
        return new RecordReminder(reminders, queue);
    }

    @Bean
    FinishReminder finishReminder(ReminderRepository reminders) {
        return new FinishReminder(reminders);
    }

    @Bean
    GetReminder getReminder(ReminderRepository reminders) {
        return new GetReminder(reminders);
    }
}
