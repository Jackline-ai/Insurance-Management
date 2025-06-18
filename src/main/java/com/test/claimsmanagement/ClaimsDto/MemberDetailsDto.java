package com.test.claimsmanagement.ClaimsDto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDetailsDto {
    private Long memberId;
    private String fullName;
    private String dateOfBirth;
    private String nationalId;
    private String phoneNumber;
    private String email;
    private String gender;
    private String relationshipStatus;
    private String memberAddress;
    private String memberResidence;
    private String memberCity;



}
