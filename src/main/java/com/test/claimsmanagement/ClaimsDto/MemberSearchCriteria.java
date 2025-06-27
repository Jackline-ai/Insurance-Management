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

    @QueryField(comparison = QueryField.Comparison.EQUALS)
    private LocalDate dateOfBirth;

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

    @QueryField(qField = "dateOfBirth", comparison = QueryField.Comparison.GREATER_THAN_OR_EQUALS)
    private LocalDate dobFrom;

    @QueryField(qField = "dateOfBirth", comparison = QueryField.Comparison.LESS_THAN_OR_EQUALS)
    private LocalDate dobTo;


}
