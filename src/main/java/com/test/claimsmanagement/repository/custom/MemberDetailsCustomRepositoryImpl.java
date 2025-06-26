package com.test.claimsmanagement.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.claimsmanagement.ClaimsDto.MemberSearchCriteria;
import com.test.claimsmanagement.model.MemberDetails;
import com.test.claimsmanagement.model.QMemberDetails;
import com.test.claimsmanagement.utils.GenericQuerydslFilterBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberDetailsCustomRepositoryImpl implements MemberDetailsCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final GenericQuerydslFilterBuilder filterBuilder;

    public Page<MemberDetails> searchMembers(MemberSearchCriteria memberSearchCriteria, Pageable pageable) {
        QMemberDetails qMemberDetails = QMemberDetails.memberDetails;
        BooleanBuilder predicate = filterBuilder.buildPredicate(memberSearchCriteria, qMemberDetails);
        log.info("memberSearchCriteria: {} and predicate: {}", memberSearchCriteria, predicate);
        JPAQuery<MemberDetails> query = jpaQueryFactory.selectFrom(qMemberDetails).where(predicate);
        log.info("member query: {}", query);
        List<MemberDetails> search = filterBuilder.fetchWithPagination(query, pageable);
        log.info("search: {}", search);
        long totalCount = jpaQueryFactory.selectFrom(qMemberDetails).where(predicate).fetchCount();
        log.info("totalCount: {}", totalCount);

        return new PageImpl<>(search, pageable, totalCount);

    }
}
