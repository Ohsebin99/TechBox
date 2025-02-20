package com.techBox.dto;

import com.techBox.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {

    private Long idIndex;
    private String userId;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String address;
    private String betterAddress;
    private String postcode;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId(memberEntity.getUserId());
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setNickname(memberEntity.getNickname());
        memberDTO.setPhoneNumber(memberEntity.getPhoneNumber());
        memberDTO.setAddress(memberEntity.getAddress());
        memberDTO.setBetterAddress(memberEntity.getBetterAddress());
        memberDTO.setPostcode(memberEntity.getPostcode());
        return memberDTO;
    }
}
