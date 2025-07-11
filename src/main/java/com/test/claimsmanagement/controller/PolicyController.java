package com.test.claimsmanagement.controller;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;
import com.test.claimsmanagement.ClaimsDto.PolicyDto;
import com.test.claimsmanagement.ClaimsDto.PolicySearchCriteria;
import com.test.claimsmanagement.ClaimsDto.ResponseDto;
import com.test.claimsmanagement.constants.ClaimsConstants;
import com.test.claimsmanagement.service.IPolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Policy Management APIs", description = "APIs for managing member policy operations")
@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api")
public class PolicyController {

    private IPolicyService memberService;

    @Operation(summary = "Create Policy", description = "Create a new policy with member details")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Policy created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPolicy(@Valid @RequestBody MemberDetailsDto memberDetailsDto) {
        memberService.createPolicy(memberDetailsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ClaimsConstants.STATUS_201, ClaimsConstants.HTTP_MESSAGE_201));
    }

    @Operation(summary = "Retrieve Policy", description = "Retrieve a member's policy by phone number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Policy retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Policy not found")
    })
    @GetMapping("/retrieve")
    public ResponseEntity<MemberDetailsDto> retrievePolicy(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message ="Phone number must be 10 digits " )
                                                               String phoneNumber) {
        MemberDetailsDto memberDetailsDto = memberService.fetchPolicyDetails(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(memberDetailsDto);
    }

    @Operation(summary = "Update Policy", description = "Update an existing policy for a member")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Policy updated successfully"),
            @ApiResponse(responseCode = "500", description = "Update failed")
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updatePolicyDetails(@Valid@RequestBody MemberDetailsDto memberDetailsDto) {
        boolean isUpdated = memberService.updatePolicyDetails(memberDetailsDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ClaimsConstants.STATUS_200, ClaimsConstants.HTTP_MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(ClaimsConstants.STATUS_500, ClaimsConstants.HTTP_MESSAGE_500));
        }
    }

    @Operation(summary = "Delete Policy", description = "Delete a member's policy using phone number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Policy deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Delete failed")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deletePolicy(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message ="Phone number must be 10 digits " )
                                                        String phoneNumber) {
        boolean isDeleted = memberService.deletePolicy(phoneNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ClaimsConstants.STATUS_200, ClaimsConstants.HTTP_MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(ClaimsConstants.STATUS_500, ClaimsConstants.HTTP_MESSAGE_500));
        }
    }

    @Operation(summary = "Get Inactive Policies", description = "Retrieve all Inactive policies for a given member")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Policies retrieved successfully")
    })
    @GetMapping("/InactivePolicies")
    public ResponseEntity<List<PolicyDto>> getInactivePolicies(@RequestParam Long memberId, @RequestParam String status) {
        List<PolicyDto> InactivePolicies = memberService.findInactivePolicies(memberId,status);
        return ResponseEntity.ok(InactivePolicies);
    }

    @Operation(summary = "Search Policies", description = "Search for policies using various filters")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search completed successfully")
    })
    @GetMapping("/search")
    public ResponseEntity<Page<PolicyDto>> searchPolicies(
            @RequestParam(required = false) Long policyId,
            @RequestParam(required = false) String policyName,
            @RequestParam(required = false) String insurer,
            @RequestParam(required = false) Double premiumAmount,
            @RequestParam(required = false) String policyType,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate upTo,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "policyId") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String sortDir
    ) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        PolicySearchCriteria criteria = new PolicySearchCriteria();
        criteria.setPolicyId(policyId);
        criteria.setPolicyName(policyName);
        criteria.setInsurer(insurer);
        criteria.setPremiumAmount(premiumAmount);
        criteria.setPolicyType(policyType);
        criteria.setStatus(status);
        criteria.setMemberId(memberId);
        criteria.setStartFrom(startFrom);
        criteria.setUpTo(upTo);

        Page<PolicyDto> result = memberService.findPolicies(criteria, pageable);
        return ResponseEntity.ok(result);
    }

}
