package com.nextvisit.visitapi.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VisitTest {

    @Test
    void storesAVisitWhenAllFieldsArePresent() {
        Visit visit = Visit.create("Okitha", LocalDate.of(2026, 10, 14), "Cleaning");

        assertEquals("Okitha", visit.customerName());
        assertEquals(VisitStatus.STORED, visit.status());
    }

    @Test
    void rejectsABlankCustomerName() {
        assertThrows(InvalidVisit.class, () -> Visit.create("  ", LocalDate.of(2026, 10, 14), "Cleaning"));
    }
}
