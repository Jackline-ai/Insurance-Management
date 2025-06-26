package com.test.claimsmanagement.ClaimsDto;

import com.test.claimsmanagement.annotations.QueryField;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberSearchCriteria {
    @QueryField
    private Long memberId;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    private String fullName;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    private String dateOfBirth;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    private String nationalId;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    private String phoneNumber;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    private String email;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    private String gender;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    private String relationshipStatus;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    private String memberAddress;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    private String memberResidence;

    @QueryField(comparison = QueryField.Comparison.CONTAINS)
    private String memberCity;

    @QueryField(qField = "startDate", comparison = QueryField.Comparison.GREATER_THAN)
    private LocalDate startFrom;

    @QueryField(qField = "endDate", comparison = QueryField.Comparison.LESS_THAN)
    private LocalDate endBefore;
}
