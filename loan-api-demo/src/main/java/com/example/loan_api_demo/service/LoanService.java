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

    public LoanService(LoanIntegrationClient integrationClient,
                       LoanRepository loanRepository) {
        this.integrationClient = integrationClient;
        this.loanRepository = loanRepository;
    }

    public LoanResponseDto getLoanDetails(String loanAccountNumber) {

        ExternalLoanResponseDto externalLoanResponseDto =
                integrationClient.fetchLoanDetails(loanAccountNumber);

        if (externalLoanResponseDto == null || externalLoanResponseDto.getEmiDetails() == null
                || externalLoanResponseDto.getEmiDetails().isEmpty()) {
            throw new RuntimeException("No EMI details found for loanAccountNumber=" + loanAccountNumber);
        }

        ExternalLoanResponseDto.EmiDetail dueEmi = externalLoanResponseDto.getEmiDetails()
                .stream()
                .filter(emi -> Boolean.TRUE.equals(emi.getDueStatus()))
                .findFirst()
                .orElse(externalLoanResponseDto.getEmiDetails().get(0));

        LocalDate dueDate = null;
        try {
            dueDate = java.time.YearMonth.parse(dueEmi.getMonth(), java.time.format.DateTimeFormatter.ofPattern("MMMM yyyy"))
                    .atDay(1);
        } catch (Exception e) {
            log.warn("Failed to parse month {} for loanAccountNumber={}", dueEmi.getMonth(), loanAccountNumber);
        }

        // Map to Entity
        LoanAccountEntity entity = new LoanAccountEntity(
                externalLoanResponseDto.getLoanAccountNumber(),
                dueDate,
                dueEmi.getEmiAmount()
        );

        // Save to DB
        loanRepository.save(entity);
        log.info("Loan details saved for loanAccountNumber={}", loanAccountNumber);

        // Return API response
        return new LoanResponseDto(
                entity.getLoanAccountNumber(),
                entity.getDueDate(),
                entity.getEmiAmount()
        );
    }
}
