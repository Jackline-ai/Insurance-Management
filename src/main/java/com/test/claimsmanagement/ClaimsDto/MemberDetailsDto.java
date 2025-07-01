package com.test.claimsmanagement.ClaimsDto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDetailsDto {
    private Long memberId;
    @NotEmpty(message = "full name cannot be empty")
    @Size(min = 5, max = 38, message = " Full Name must be between 5 and 38 characters" )
    private String fullName;

    private LocalDate dateOfBirth;
    private String nationalId;

    @Pattern(regexp = "(^$|[0-9]{10})", message ="Phone number must be 10 digits " )
    private String phoneNumber;

    @Email(message = "enter a valid email")
    private String email;

    private String gender;
    private String relationshipStatus;
    private String memberAddress;
    private String memberResidence;
    private String memberCity;
    private PolicyDto policyDto;



}
