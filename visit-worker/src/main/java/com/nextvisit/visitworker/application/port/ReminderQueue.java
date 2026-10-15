package com.nextvisit.visitworker.application.port;

import java.util.UUID;

public interface ReminderQueue {

    void send(UUID visitId);
}
