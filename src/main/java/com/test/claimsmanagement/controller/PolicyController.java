package com.test.claimsmanagement.controller;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;
import com.test.claimsmanagement.ClaimsDto.PolicyDto;
import com.test.claimsmanagement.ClaimsDto.ResponseDto;
import com.test.claimsmanagement.constants.ClaimsConstants;
import com.test.claimsmanagement.service.IPolicyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController@AllArgsConstructor
@RequestMapping("/api")
public class PolicyController {
    private IPolicyService memberService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPolicy(@RequestBody MemberDetailsDto memberDetailsDto){
        memberService.createPolicy(memberDetailsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ClaimsConstants.STATUS_201,ClaimsConstants.HTTP_MESSAGE_201 ));
    }
    @GetMapping("/retrieve")
    public ResponseEntity<MemberDetailsDto> retrievePolicy(@RequestParam String phoneNumber){
       MemberDetailsDto memberDetailsDto = memberService.fetchPolicyDetails(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(memberDetailsDto);

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updatePolicyDetails(@RequestBody MemberDetailsDto memberDetailsDto){
        boolean isUpdated = memberService.updatePolicyDetails(memberDetailsDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ClaimsConstants.STATUS_200, ClaimsConstants.HTTP_MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(ClaimsConstants.STATUS_500, ClaimsConstants.HTTP_MESSAGE_500));
        }
    }
@DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deletePolicy(@RequestParam String phoneNumber){
        boolean isDeleted = memberService.deletePolicy(phoneNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ClaimsConstants.STATUS_200, ClaimsConstants.HTTP_MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(ClaimsConstants.STATUS_500, ClaimsConstants.HTTP_MESSAGE_500));
        }
    }

    @GetMapping("/activePolicies/{memberId}")
    public ResponseEntity<List<PolicyDto>> getInactivePolicies(@PathVariable Long memberId) {
        List<PolicyDto> activePolicies = memberService.findInactivePoliciesByMemberId(memberId);
        return ResponseEntity.ok(activePolicies);
    }
}
