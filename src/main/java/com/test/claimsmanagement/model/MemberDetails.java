package com.test.claimsmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor@AllArgsConstructor@Getter@Setter@ToString
@Entity
@Table(name = "member")
public class MemberDetails extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String fullName;
    private LocalDate dateOfBirth;
    private String nationalId;
    private String phoneNumber;
    private String email;
    private String gender;
    private String relationshipStatus;
    private String memberAddress;
    private String memberResidence;
    private String memberCity;
}


