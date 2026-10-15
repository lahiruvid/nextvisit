package com.nextvisit.visitapi.config;

import com.nextvisit.visitapi.application.GetVisit;
import com.nextvisit.visitapi.application.SaveVisit;
import com.nextvisit.visitapi.application.port.VisitRecorded;
import com.nextvisit.visitapi.application.port.VisitRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    SaveVisit saveVisit(VisitRepository visits, VisitRecorded events) {
        return new SaveVisit(visits, events);
    }

    @Bean
    GetVisit getVisit(VisitRepository visits) {
        return new GetVisit(visits);
    }
}
