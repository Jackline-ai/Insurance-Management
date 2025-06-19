package com.test.claimsmanagement.service.impl;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;
import com.test.claimsmanagement.ClaimsDto.PolicyDto;
import com.test.claimsmanagement.ClaimsMapper.MemberDetailsMapper;
import com.test.claimsmanagement.ClaimsMapper.PolicyMapper;
import com.test.claimsmanagement.constants.ClaimsConstants;
import com.test.claimsmanagement.exception.MemberAlreadyExistsException;
import com.test.claimsmanagement.exception.ResourceNotFoundException;
import com.test.claimsmanagement.model.MemberDetails;
import com.test.claimsmanagement.model.PolicyDetails;
import com.test.claimsmanagement.repository.MemberRepository;
import com.test.claimsmanagement.repository.PolicyRepository;
import com.test.claimsmanagement.service.IPolicyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service@AllArgsConstructor
public class MemberService implements IPolicyService {
    private  MemberRepository memberRepository;
    private PolicyRepository policyRepository;


    @Override
    public void createPolicy(MemberDetailsDto memberDetailsDto) {
        MemberDetails memberDetails = MemberDetailsMapper.mapsToMemberDetails(memberDetailsDto, new MemberDetails());
        Optional<MemberDetails> optionalMember = memberRepository.findByPhoneNumber(memberDetailsDto.getPhoneNumber());
        if (optionalMember.isPresent()) {
            throw new MemberAlreadyExistsException("Member with : " + memberDetailsDto.getPhoneNumber() + "already exists");
        }
        MemberDetails savedMemberDetails = memberRepository.save(memberDetails);
        policyRepository.save(createNewPolicy(savedMemberDetails));

    }


    private PolicyDetails createNewPolicy(MemberDetails memberDetails) {
        PolicyDetails newPolicy = new PolicyDetails();
        newPolicy.setMemberId(memberDetails.getMemberId());
        newPolicy.setPolicyType(ClaimsConstants.Policy_TYPE);
        newPolicy.setPolicyName(ClaimsConstants.POLICY_NAME);
        newPolicy.setInsurer(ClaimsConstants.INSURER);
        newPolicy.setStatus(ClaimsConstants.STATUS);
        newPolicy.setPremiumAmount(ClaimsConstants.PREMIUM_AMOUNT);
        return newPolicy;

    }

    @Override
    public MemberDetailsDto fetchPolicyDetails(String phoneNumber) {
        MemberDetails member = memberRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new ResourceNotFoundException("MemberDetails", "phoneNumber", phoneNumber));
        PolicyDetails policy = policyRepository.findByMemberId(member.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("PolicyDetails", "memberId", member.getMemberId().toString()));
        MemberDetailsDto memberDetailsDto = MemberDetailsMapper.mapsToMemberDetailsDto(member, new MemberDetailsDto());
        memberDetailsDto.setPolicyDto(PolicyMapper.mapsPolicyDto(policy, new PolicyDto()));
        return memberDetailsDto;




    }
}
