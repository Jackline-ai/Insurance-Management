package com.test.claimsmanagement.repository;

import com.test.claimsmanagement.model.PolicyDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PolicyRepository extends JpaRepository<PolicyDetails, Long> {


}

