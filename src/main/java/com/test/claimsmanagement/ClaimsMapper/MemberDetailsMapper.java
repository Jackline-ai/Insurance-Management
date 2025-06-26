package com.test.claimsmanagement.ClaimsMapper;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;
import com.test.claimsmanagement.model.MemberDetails;

public class MemberDetailsMapper {
    public static MemberDetailsDto mapsToMemberDetailsDto(MemberDetails memberInfo, MemberDetailsDto memberDetailsDto) {
        memberDetailsDto.setMemberId(memberInfo.getMemberId());
        memberDetailsDto.setFullName(memberInfo.getFullName());
        memberDetailsDto.setMemberAddress(memberInfo.getMemberAddress());
        memberDetailsDto.setMemberCity(memberInfo.getMemberCity());
        memberDetailsDto.setDateOfBirth(memberInfo.getDateOfBirth());
        memberDetailsDto.setNationalId(memberInfo.getNationalId());
        memberDetailsDto.setPhoneNumber(memberInfo.getPhoneNumber());
        memberDetailsDto.setGender(memberInfo.getGender());
        memberDetailsDto.setEmail(memberInfo.getEmail());
        memberDetailsDto.setMemberResidence(memberInfo.getMemberResidence());
        memberDetailsDto.setRelationshipStatus(memberInfo.getRelationshipStatus());
        return memberDetailsDto;

    }
    public static MemberDetails mapsToMemberDetails(MemberDetailsDto memberDetailsDto, MemberDetails memberInfo ) {
        memberInfo.setMemberId(memberDetailsDto.getMemberId());
        memberInfo.setFullName(memberDetailsDto.getFullName());
        memberInfo.setMemberAddress(memberDetailsDto.getMemberAddress());
        memberInfo.setMemberCity(memberDetailsDto.getMemberCity());
        memberInfo.setDateOfBirth(memberDetailsDto.getDateOfBirth());
        memberInfo.setNationalId(memberDetailsDto.getNationalId());
        memberInfo.setPhoneNumber(memberDetailsDto.getPhoneNumber());
        memberInfo.setGender(memberDetailsDto.getGender());
        memberInfo.setEmail(memberDetailsDto.getEmail());
        memberInfo.setRelationshipStatus(memberDetailsDto.getRelationshipStatus());
        memberInfo.setMemberResidence(memberDetailsDto.getMemberResidence());
        return memberInfo;

    }

}
