package com.techBox.entity;

import com.techBox.dto.MemberDTO;
import com.techBox.repository.MemberRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIndex;

    @Column(unique = true, length = 10)
    private String userId;

    @Column(length = 12)
    private String password;

    @Column(length = 8)
    private String nickname;

    @Column(length = 11)
    private String phoneNumber;

    @Column(length = 40)
    private String address;

    @Column(length = 30)
    private String betterAddress;

    @Column(length = 5)
    private String postcode;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setIdIndex(memberDTO.getIdIndex());
        memberEntity.setUserId(memberDTO.getUserId());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setNickname(memberDTO.getNickname());
        memberEntity.setPhoneNumber(memberDTO.getPhoneNumber());
        memberEntity.setAddress(memberDTO.getAddress());
        memberEntity.setBetterAddress(memberDTO.getBetterAddress());
        memberEntity.setPostcode(memberDTO.getPostcode());
        return memberEntity;
    }

}
