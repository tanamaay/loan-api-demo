package com.example.loan_api_demo.service;

import com.example.loan_api_demo.dto.ExternalLoanResponseDto;
import com.example.loan_api_demo.dto.LoanResponseDto;
import com.example.loan_api_demo.entity.LoanAccountEntity;
import com.example.loan_api_demo.integration.LoanIntegrationClient;
import com.example.loan_api_demo.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class LoanService {

    private final LoanIntegrationClient integrationClient;
    private final LoanRepository loanRepository;

    // Constructor MUST be public
    public LoanService(LoanIntegrationClient integrationClient,
                       LoanRepository loanRepository) {
        this.integrationClient = integrationClient;
        this.loanRepository = loanRepository;
    }

    public LoanResponseDto getLoanDetails(String loanAccountNumber) {

        //  Call external API
        ExternalLoanResponseDto externalLoanResponseDto =
                integrationClient.fetchLoanDetails(loanAccountNumber);

        // 2 Map API response to Entity
        LoanAccountEntity entity = new LoanAccountEntity(
                externalLoanResponseDto.getLoanAccountNumber(),
                LocalDate.parse(externalLoanResponseDto.getDueDate()),
                externalLoanResponseDto.getEmiAmount()
        );

        // 3️ Save to DB
        loanRepository.save(entity);
        log.info("Loan details saved for loanAccountNumber={}", loanAccountNumber);

        // 4️ Return required response
        return new LoanResponseDto(
                entity.getLoanAccountNumber(),
                entity.getDueDate(),
                entity.getEmiAmount()
        );
    }
}
