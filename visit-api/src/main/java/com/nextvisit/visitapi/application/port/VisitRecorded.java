package com.nextvisit.visitapi.application.port;

import com.nextvisit.visitapi.domain.Visit;

public interface VisitRecorded {

    void publish(Visit visit);
}
