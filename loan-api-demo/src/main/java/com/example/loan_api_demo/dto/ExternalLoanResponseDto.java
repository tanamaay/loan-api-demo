package com.example.loan_api_demo.dto;

import lombok.Data;

@Data
public class ExternalLoanResponseDto {
    private String loanAccountNumber;
    private String dueDate;
    private Double emiAmount;
}
