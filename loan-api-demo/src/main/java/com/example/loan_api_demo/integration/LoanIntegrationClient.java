package com.example.loan_api_demo.integration;

import com.example.loan_api_demo.dto.ExternalLoanResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class LoanIntegrationClient {

    private final RestTemplate restTemplate;

    public LoanIntegrationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalLoanResponseDto fetchLoanDetails(String loanAccountNumber) {
        String url = "https://demo9993930.mockable.io/loanaccount/" + loanAccountNumber;
        log.info("Calling external loan API for loanAccountNumber={}", loanAccountNumber);

        String rawResponse = restTemplate.getForObject(url, String.class);
        log.info("RAW API RESPONSE: {}", rawResponse);

        ExternalLoanResponseDto loanResponse =
                restTemplate.getForObject(url, ExternalLoanResponseDto.class);

        log.info("Mapped ExternalLoanResponseDto = {}", loanResponse);
        return loanResponse;
    }
}
