package com.saessak.momo.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private int userIdx;
    private String userId;
    private String userPwd;
    private String nickName;
    private String userName;
}
