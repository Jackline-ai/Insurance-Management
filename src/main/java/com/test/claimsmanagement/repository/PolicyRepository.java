package com.test.claimsmanagement.repository;

import com.test.claimsmanagement.model.PolicyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PolicyRepository extends JpaRepository<PolicyDetails, Long> {
Optional<PolicyDetails> findByMemberId(Long memberId);

}

