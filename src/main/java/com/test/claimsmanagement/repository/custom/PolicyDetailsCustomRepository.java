package com.test.claimsmanagement.repository.custom;

import com.test.claimsmanagement.model.PolicyDetails;

import java.util.List;
import java.util.Optional;

public interface PolicyDetailsCustomRepository {
    List<PolicyDetails> findByMemberIdAndStatus(Long memberId, String status);

}
