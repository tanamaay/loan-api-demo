package com.example.loan_api_demo.repository;

import com.example.loan_api_demo.entity.LoanAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository
        extends JpaRepository<LoanAccountEntity, String> {
}