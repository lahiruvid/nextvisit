package com.nextvisit.visitworker.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReminderJpaRepository extends JpaRepository<ReminderEntity, UUID> {
}
