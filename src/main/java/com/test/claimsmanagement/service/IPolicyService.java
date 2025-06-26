package com.test.claimsmanagement.service;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;
import com.test.claimsmanagement.ClaimsDto.PolicyDto;
import com.test.claimsmanagement.ClaimsDto.PolicySearchCriteria;
import com.test.claimsmanagement.model.PolicyDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPolicyService {
     void createPolicy(MemberDetailsDto memberDetailsDto);

    MemberDetailsDto fetchPolicyDetails(String phoneNumber);

    boolean updatePolicyDetails(MemberDetailsDto memberDetailsDto);

    boolean deletePolicy(String phoneNumber);
   List <PolicyDto> findInactivePolicies(Long memberId, String status);

    Page<PolicyDto> findPolicies(PolicySearchCriteria criteria, Pageable pageable);
}


