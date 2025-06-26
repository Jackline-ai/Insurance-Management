package com.test.claimsmanagement.repository.custom;

import com.test.claimsmanagement.ClaimsDto.MemberSearchCriteria;
import com.test.claimsmanagement.model.MemberDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberDetailsCustomRepository {
    Page<MemberDetails> searchMembers(MemberSearchCriteria memberSearchCriteria, Pageable pageable);

}
