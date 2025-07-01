package com.test.claimsmanagement.ClaimsDto;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@Data
public class PolicyDto {

    private Long policyId;

    @NotEmpty(message = "Policy Name must not be empty")
    @Size(min = 4, max = 40)
    private String policyName;

    @NotEmpty(message = "Please enter the insurer")
    private String insurer;

    private LocalDate startDate;
    private LocalDate endDate;
    @Column(nullable = false)
    @Min(value = 400, message = "The minimum premium amount is 400")
    private double premiumAmount;
    private String policyType;
    private String status;
    private Long memberId;

}
