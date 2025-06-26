package com.test.claimsmanagement.ClaimsDto;

import lombok.*;

import java.time.LocalDate;


@Data
public class PolicyDto {

    private Long policyId;
    private String policyName;
    private String insurer;
    private LocalDate startDate;
    private LocalDate endDate;
    private double premiumAmount;
    private String policyType;
    private String status;
    private Long memberId;

}
