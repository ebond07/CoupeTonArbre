package com.example.coupetonarbrebackend.QuoteRequest.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRequestRepository extends JpaRepository<QuoteRequest, Integer> {
    QuoteRequest findQuoteRequestByQuoteRequestId(String quoteRequestId);

    void deleteByQuoteRequestId(String quoteRequestId);
}
