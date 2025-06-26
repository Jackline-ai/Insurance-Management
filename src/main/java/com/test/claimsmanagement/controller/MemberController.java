package com.test.claimsmanagement.controller;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;
import com.test.claimsmanagement.ClaimsDto.MemberSearchCriteria;
import com.test.claimsmanagement.service.impl.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "Search Members", description = "Search for members using filters")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search completed successfully")
    })
    @GetMapping("/search")
    public ResponseEntity<Page<MemberDetailsDto>> searchMembers(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String dateOfBirth,
            @RequestParam(required = false) String nationalId,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String relationshipStatus,
            @RequestParam(required = false) String memberAddress,
            @RequestParam(required = false) String memberResidence,
            @RequestParam(required = false) String memberCity,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bornBefore,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bornAfter,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "memberId") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir) {

        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        MemberSearchCriteria criteria = new MemberSearchCriteria();
        criteria.setMemberId(memberId);
        criteria.setFullName(fullName);
        criteria.setDateOfBirth(dateOfBirth);
        criteria.setNationalId(nationalId);
        criteria.setPhoneNumber(phoneNumber);
        criteria.setEmail(email);
        criteria.setGender(gender);
        criteria.setRelationshipStatus(relationshipStatus);
        criteria.setMemberAddress(memberAddress);
        criteria.setMemberResidence(memberResidence);
        criteria.setMemberCity(memberCity);
        criteria.setBornBefore(bornBefore);
        criteria.setBornAfter(bornAfter);

        Page<MemberDetailsDto> searchResult = memberService.findMembers(criteria, pageable);
        return ResponseEntity.ok(searchResult);
    }

}
