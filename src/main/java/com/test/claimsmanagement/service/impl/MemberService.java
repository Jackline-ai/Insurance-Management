package com.test.claimsmanagement.service.impl;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;
import com.test.claimsmanagement.ClaimsDto.PolicyDto;
import com.test.claimsmanagement.ClaimsDto.PolicySearchCriteria;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberService implements IPolicyService {
    private MemberRepository memberRepository;
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
        newPolicy.setPolicyType(newPolicy.getPolicyType());
        newPolicy.setPolicyName(newPolicy.getPolicyName());
        newPolicy.setInsurer(newPolicy.getInsurer());
        newPolicy.setStatus(newPolicy.getStatus());
        newPolicy.setPremiumAmount(newPolicy.getPremiumAmount());
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

    @Override
    public boolean updatePolicyDetails(MemberDetailsDto memberDetailsDto) {
        boolean isupdated = false;
        PolicyDto policyDto = memberDetailsDto.getPolicyDto();
        if (policyDto != null) {
            PolicyDetails policyDetails = policyRepository.findById(policyDto.getPolicyId())
                    .orElseThrow(() -> new ResourceNotFoundException("PolicyDetails", "policyId", policyDto.getPolicyId().toString()));
            PolicyMapper.mapsPolicyDetails(policyDto, policyDetails);
            policyRepository.save(policyDetails);

            Long memberId = memberDetailsDto.getMemberId();
            MemberDetails memberDetails = memberRepository.findById(memberId)
                    .orElseThrow(() -> new ResourceNotFoundException("MemberDetails", "memberId", memberId.toString()));
            MemberDetailsMapper.mapsToMemberDetails(memberDetailsDto, memberDetails);
            memberRepository.save(memberDetails);
            isupdated = true;
        }
        return isupdated;
    }

    @Override
    public boolean deletePolicy(String phoneNumber) {
        MemberDetails memberDetails = memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("MemberDetails", "phoneNumber", phoneNumber));
        policyRepository.deleteByMemberId(memberDetails.getMemberId());
        memberRepository.deleteById(memberDetails.getMemberId());
        return true;
    }

    @Override
    public List<PolicyDto> findInactivePoliciesByMemberId(Long memberId) {
        List<PolicyDetails> activePolicies = policyRepository.findByMemberIdAndStatus(memberId, "Inactive");
        if (activePolicies.isEmpty()) {
            throw new ResourceNotFoundException("PolicyDetails", "memberId", memberId.toString());
        }

        return activePolicies.stream()
                .map(policy -> PolicyMapper.mapsPolicyDto(policy, new PolicyDto()))
                .collect(Collectors.toList());


    }
    @Override
    public Page<PolicyDto> findPolicies(PolicySearchCriteria criteria, Pageable pageable) {
        Page<PolicyDetails> policyDetailsPage = policyRepository.searchPolicies(criteria, pageable);

        if (policyDetailsPage.isEmpty()) {
            throw new ResourceNotFoundException("Policies", "Generic search", criteria.toString());
        }

        List<PolicyDto> dtoList = policyDetailsPage.getContent()
                .stream()
                .map(policy -> PolicyMapper.mapsPolicyDto(policy, new PolicyDto()))
                .toList();

        return new PageImpl<>(dtoList, pageable, policyDetailsPage.getTotalElements());
    }


}






