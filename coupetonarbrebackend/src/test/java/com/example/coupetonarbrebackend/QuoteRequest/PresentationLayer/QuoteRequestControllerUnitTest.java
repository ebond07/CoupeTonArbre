package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer.QuoteRequestService;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class QuoteRequestControllerUnitTest {

    @Mock
    private QuoteRequestService quoteRequestService;

    @Mock
    private QuoteRequestRequestMapper quoteRequestRequestMapper;

    @InjectMocks
    private QuoteRequestController quoteRequestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void constructor_shouldInitializeDependencies() {
        assertNotNull(quoteRequestController);
    }
}
