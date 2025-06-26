package com.test.claimsmanagement.repository.custom;

import com.test.claimsmanagement.ClaimsDto.PolicySearchCriteria;
import com.test.claimsmanagement.model.PolicyDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PolicyDetailsCustomRepository {
    List<PolicyDetails> findByMemberIdAndStatus(Long memberId, String status);

    Page<PolicyDetails> searchPolicies(PolicySearchCriteria criteria, Pageable pageable);

}
