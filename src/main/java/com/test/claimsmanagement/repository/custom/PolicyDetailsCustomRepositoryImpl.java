package com.test.claimsmanagement.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.claimsmanagement.ClaimsDto.PolicySearchCriteria;
import com.test.claimsmanagement.model.PolicyDetails;
import com.test.claimsmanagement.model.QPolicyDetails;
import com.test.claimsmanagement.utils.GenericQuerydslFilterBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PolicyDetailsCustomRepositoryImpl implements PolicyDetailsCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final GenericQuerydslFilterBuilder filterBuilder;

    @Override
    public List<PolicyDetails> findByStatus( String status) {
        QPolicyDetails qPolicy = QPolicyDetails.policyDetails;
        List<PolicyDetails> policies = jpaQueryFactory
                .selectFrom(qPolicy)
                .where(qPolicy.status.eq(status))
                .fetch();
        return policies;
    }
    @Override
    public Page<PolicyDetails> searchPolicies(PolicySearchCriteria criteria, Pageable pageable) {
        QPolicyDetails q = QPolicyDetails.policyDetails;
        BooleanBuilder predicate = filterBuilder.buildPredicate(criteria, q);
        log.info("criteria: {} and predicate: {}", criteria, predicate);

        JPAQuery<PolicyDetails> query = jpaQueryFactory.selectFrom(q).where(predicate);
        log.info("query: {}", query);
        List<PolicyDetails> result = filterBuilder.fetchWithPagination(query, pageable);
        log.info("result: {}", result);
        long total = jpaQueryFactory.selectFrom(q).where(predicate).fetchCount();
        log.info("total: {}", total);

        return new PageImpl<>(result, pageable, total);
    }

}
