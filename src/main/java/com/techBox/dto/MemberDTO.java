package com.techBox.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {

    private String id;
    private String password;
    private String nickName;
    private String phoneNumber;
    private String address;

}
