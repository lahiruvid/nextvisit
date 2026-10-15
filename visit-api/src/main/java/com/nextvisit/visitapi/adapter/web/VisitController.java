package com.nextvisit.visitapi.adapter.web;

import com.nextvisit.visitapi.application.GetVisit;
import com.nextvisit.visitapi.application.SaveVisit;
import com.nextvisit.visitapi.domain.Visit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/visits")
public class VisitController {

    private final SaveVisit saveVisit;
    private final GetVisit getVisit;

    public VisitController(SaveVisit saveVisit, GetVisit getVisit) {
        this.saveVisit = saveVisit;
        this.getVisit = getVisit;
    }

    @PostMapping
    public ResponseEntity<VisitResponse> create(@RequestBody VisitRequest request) {
        Visit visit = saveVisit.execute(request.customerName(), request.visitDate(), request.serviceType());
        return ResponseEntity.status(HttpStatus.CREATED).body(VisitResponse.from(visit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitResponse> get(@PathVariable UUID id) {
        return getVisit.execute(id)
                .map(visit -> ResponseEntity.ok(VisitResponse.from(visit)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
