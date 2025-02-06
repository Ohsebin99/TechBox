package com.techBox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {

    private String idIndex;
    private String id;
    private String password;
    private String nickName;
    private String phoneNumber;
    private String address;

}
