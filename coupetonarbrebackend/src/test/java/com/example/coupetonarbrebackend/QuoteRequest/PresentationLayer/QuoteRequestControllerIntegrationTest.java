package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer.QuoteRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
@AutoConfigureMockMvc//(addFilters = false)
class QuoteRequestControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private QuoteRequestService quoteRequestService;

    @Autowired
    private MockMvc mockMvc;

    private final String BASE_URI_QUOTEREQUESTS = "/quoteRequests";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllQuoteRequests() throws Exception {
        mockMvc.perform(get(BASE_URI_QUOTEREQUESTS)
                .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject("google|123456789")).authorities(new SimpleGrantedAuthority("Admin")))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}