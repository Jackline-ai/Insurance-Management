package com.test.claimsmanagement.ClaimsDto;

import com.test.claimsmanagement.annotations.QueryField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(
        name = "PolicySearchCriteria",
        description = "Search criteria for filtering policies based on various parameters"
)
public class PolicySearchCriteria {

    @QueryField
    @Schema(description = "Unique identifier for the policy", example = "1001")
    private Long policyId;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    @Schema(description = "Name of the policy", example = "Health Insurance")
    private String policyName;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    @Schema(description = "Name of the insurer", example = "Aetna")
    private String insurer;

    @QueryField
    @Schema(description = "Amount of premium for the policy", example = "2500.50")
    private Double premiumAmount;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    @Schema(description = "Type of the policy", example = "Medical")
    private String policyType;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    @Schema(description = "Current status of the policy", example = "Active")
    private String status;

    @QueryField(comparison = QueryField.Comparison.EQUALS)
    @Schema(description = "Unique identifier of the associated member", example = "2002")
    private Long memberId;

    @QueryField(qField = "startDate", comparison = QueryField.Comparison.GREATER_THAN)
    @Schema(description = "Filter policies that start after this date", example = "2024-01-01")
    private LocalDate startFrom;

    @QueryField(qField = "endDate", comparison = QueryField.Comparison.LESS_THAN_OR_EQUALS)
    @Schema(description = "Filter policies that end before this date", example = "2025-12-31")
    private LocalDate upTo;
}
