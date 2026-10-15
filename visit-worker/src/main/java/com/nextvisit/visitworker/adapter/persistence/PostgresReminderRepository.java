package com.nextvisit.visitworker.adapter.persistence;

import com.nextvisit.visitworker.application.port.ReminderRepository;
import com.nextvisit.visitworker.domain.Reminder;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresReminderRepository implements ReminderRepository {

    private final ReminderJpaRepository jpa;

    public PostgresReminderRepository(ReminderJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Reminder> findByVisitId(UUID visitId) {
        return jpa.findById(visitId).map(row -> Reminder.restore(row.getVisitId(), row.getStatus()));
    }

    @Override
    public void save(Reminder reminder) {
        jpa.save(new ReminderEntity(reminder.visitId(), reminder.status()));
    }
}
