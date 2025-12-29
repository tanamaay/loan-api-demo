package com.example.loan_api_demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalLoanResponseDto {

    @JsonProperty("loanAccountNumber")
    private String loanAccountNumber;

    @JsonProperty("emiDetails")
    private List<EmiDetail> emiDetails;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EmiDetail {
        @JsonProperty("month")
        private String month;

        @JsonProperty("emiAmount")
        private Double emiAmount;

        @JsonProperty("paidStatus")
        private Boolean paidStatus;

        @JsonProperty("dueStatus")
        private Boolean dueStatus;
    }
}
