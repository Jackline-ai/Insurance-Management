package com.test.claimsmanagement.repository;

import com.test.claimsmanagement.model.MemberDetails;
import com.test.claimsmanagement.repository.custom.MemberDetailsCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository <MemberDetails, Long>, MemberDetailsCustomRepository {
    Optional<MemberDetails> findByPhoneNumber(String phoneNumber);


}
