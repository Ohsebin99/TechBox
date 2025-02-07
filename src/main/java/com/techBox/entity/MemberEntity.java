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
    private String id;

    @Column(length = 12)
    private String password;

    @Column(length = 8)
    private String nickName;

    @Column(length = 11)
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String betterAddress;

    @Column
    private String postcode;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setNickName(memberDTO.getNickName());
        memberEntity.setPhoneNumber(memberDTO.getPhoneNumber());
        memberEntity.setAddress(memberDTO.getAddress());
        memberEntity.setBetterAddress(memberDTO.getBetterAddress());
        memberEntity.setPostcode(memberDTO.getPostcode());
        return memberEntity;
    }

}
