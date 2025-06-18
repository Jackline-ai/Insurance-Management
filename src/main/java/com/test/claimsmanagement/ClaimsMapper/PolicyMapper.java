package com.test.claimsmanagement.ClaimsMapper;

import com.test.claimsmanagement.ClaimsDto.PolicyDto;
import com.test.claimsmanagement.model.PolicyDetails;


public class PolicyMapper {
    public static PolicyDto mapsPolicyDto(PolicyDetails policyDetails, PolicyDto policyDto) {
        policyDto.setPolicyId(policyDetails.getPolicyId());
        policyDto.setPolicyName(policyDetails.getPolicyName());
        policyDto.setPolicyType(policyDetails.getPolicyType());
        policyDto.setInsurer(policyDetails.getInsurer());
        policyDto.setStartDate(policyDetails.getStartDate());
        policyDto.setEndDate(policyDetails.getEndDate());
        policyDto.setPremiumAmount(policyDetails.getPremiumAmount());
        policyDto.setStatus(policyDetails.getStatus());
        policyDto.setMemberId(policyDetails.getMemberId());

        return policyDto;
    }

    public static PolicyDetails mapsPolicyDetails(PolicyDto policyDto, PolicyDetails policyDetails) {
        policyDetails.setPolicyId(policyDto.getPolicyId());
        policyDetails.setPolicyName(policyDto.getPolicyName());
        policyDetails.setPolicyType(policyDto.getPolicyType());
        policyDetails.setInsurer(policyDto.getInsurer());
        policyDetails.setStartDate(policyDto.getStartDate());
        policyDetails.setEndDate(policyDto.getEndDate());
        policyDetails.setPremiumAmount(policyDto.getPremiumAmount());
        policyDetails.setStatus(policyDto.getStatus());
        policyDetails.setMemberId(policyDto.getMemberId());
        return policyDetails;
    }
}
