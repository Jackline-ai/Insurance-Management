package com.test.claimsmanagement.repository;

import com.test.claimsmanagement.model.PolicyDetails;
import com.test.claimsmanagement.repository.custom.PolicyDetailsCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface PolicyRepository extends JpaRepository<PolicyDetails, Long>, PolicyDetailsCustomRepository {
Optional<PolicyDetails> findByMemberId(Long memberId);

@Transactional
@Modifying
    void deleteByMemberId(Long memberId);
}

