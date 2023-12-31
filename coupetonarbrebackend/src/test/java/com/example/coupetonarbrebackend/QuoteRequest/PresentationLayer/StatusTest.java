package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatusTest {

    @Test
    void shouldHaveCorrectNumberOfEnumValues() {
        assertEquals(4, Status.values().length);
    }

    @Test
    void shouldHaveExpectedEnumValues() {
        assertEquals(Status.PENDING, Status.valueOf("PENDING"));
        assertEquals(Status.QUOTE_SENT, Status.valueOf("QUOTE_SENT"));
        assertEquals(Status.QUOTE_ACCEPTED, Status.valueOf("QUOTE_ACCEPTED"));
        assertEquals(Status.QUOTE_REFUSED, Status.valueOf("QUOTE_REFUSED"));
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        assertEquals("PENDING", Status.PENDING.toString());
        assertEquals("QUOTE_SENT", Status.QUOTE_SENT.toString());
        assertEquals("QUOTE_ACCEPTED", Status.QUOTE_ACCEPTED.toString());
        assertEquals("QUOTE_REFUSED", Status.QUOTE_REFUSED.toString());
    }

    @Test
    void shouldCompareEnumInstances() {
        assertTrue(Status.PENDING.compareTo(Status.QUOTE_SENT) < 0);
        assertTrue(Status.QUOTE_SENT.compareTo(Status.PENDING) > 0);
        assertEquals(0, Status.PENDING.compareTo(Status.PENDING));
    }

}
