package com.example.loan_api_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "loan_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanAccountEntity {

    @Id
    private String loanAccountNumber;

    private LocalDate dueDate;

    private Double emiAmount;
}
