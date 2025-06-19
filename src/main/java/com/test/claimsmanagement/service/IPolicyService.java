package com.test.claimsmanagement.service;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;

public interface IPolicyService {
     void createPolicy(MemberDetailsDto memberDetailsDto);

    MemberDetailsDto fetchPolicyDetails(String phoneNumber);



}
