package com.test.claimsmanagement.service;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;

public interface IPolicyService {
    public void createPolicy(MemberDetailsDto memberDetailsDto);

    public void fetchPolicyDetails(String phoneNumber);



}
