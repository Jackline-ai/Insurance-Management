package com.test.claimsmanagement.controller;

import com.test.claimsmanagement.ClaimsDto.MemberDetailsDto;
import com.test.claimsmanagement.ClaimsDto.ResponseDto;
import com.test.claimsmanagement.constants.ClaimsConstants;
import com.test.claimsmanagement.service.IPolicyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController@AllArgsConstructor
@RequestMapping("/api")
public class PolicyController {
    private IPolicyService memberService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPolicy(@RequestBody MemberDetailsDto memberDetailsDto){
        memberService.createPolicy(memberDetailsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ClaimsConstants.HTTP_CODE,ClaimsConstants.HTTP_MESSAGE ));
    }
    @GetMapping("/retrieve")
    public ResponseEntity<MemberDetailsDto> retrievePolicy(@RequestParam String phoneNumber){
       MemberDetailsDto memberDetailsDto = memberService.fetchPolicyDetails(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(memberDetailsDto);

    }

}
