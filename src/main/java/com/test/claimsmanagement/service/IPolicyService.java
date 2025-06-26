package com.test.claimsmanagement.service;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;
import com.test.claimsmanagement.ClaimsDto.PolicyDto;
import com.test.claimsmanagement.model.PolicyDetails;

import java.util.List;

public interface IPolicyService {
     void createPolicy(MemberDetailsDto memberDetailsDto);

    MemberDetailsDto fetchPolicyDetails(String phoneNumber);

    boolean updatePolicyDetails(MemberDetailsDto memberDetailsDto);

    boolean deletePolicy(String phoneNumber);
   List<PolicyDto> findInactivePoliciesByMemberId(Long memberId);
}


