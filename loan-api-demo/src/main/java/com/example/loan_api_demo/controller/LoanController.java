package com.example.loan_api_demo.controller;

import com.example.loan_api_demo.dto.LoanResponseDto;
import com.example.loan_api_demo.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
@Slf4j

public class LoanController {

    private  final LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService  = loanService;
    }
    @GetMapping("/{loanAccountNumber}")
    public LoanResponseDto getLoan(@PathVariable String loanAccountNumber){
        log.info("Received request for loanAccountNumber={}", loanAccountNumber);
       return loanService.getLoanDetails(loanAccountNumber);
    }
}
