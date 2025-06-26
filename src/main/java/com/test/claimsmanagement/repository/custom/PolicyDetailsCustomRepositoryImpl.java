package com.test.claimsmanagement.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.claimsmanagement.model.PolicyDetails;
import com.test.claimsmanagement.model.QPolicyDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PolicyDetailsCustomRepositoryImpl implements PolicyDetailsCustomRepository {
private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PolicyDetails> findByMemberIdAndStatus(Long memberId, String status) {
        QPolicyDetails qPolicy = QPolicyDetails.policyDetails;
        List<PolicyDetails> policies = jpaQueryFactory
                .selectFrom(qPolicy)
                .where(qPolicy.status.eq(status)
                        .and(qPolicy.memberId.eq(memberId)))
                .fetch();
        return policies;
    }
}
