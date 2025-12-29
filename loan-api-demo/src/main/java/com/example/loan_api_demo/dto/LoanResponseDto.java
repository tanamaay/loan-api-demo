package com.example.loan_api_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LoanResponseDto {

    private String loanAccountNumber;
    private LocalDate dueDate;
    private Double emiAmount;
}
