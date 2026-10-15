package com.nextvisit.visitworker.application.port;

import com.nextvisit.visitworker.domain.Reminder;

import java.util.Optional;
import java.util.UUID;

public interface ReminderRepository {

    Optional<Reminder> findByVisitId(UUID visitId);

    void save(Reminder reminder);
}
