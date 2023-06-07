package com.saessak.momo.user.model.service;


import com.saessak.momo.user.dto.SignupForm;
import com.saessak.momo.user.dto.UserDto;

import java.util.Map;

public interface UserService {
    void signup(UserDto form) throws Exception;
    UserDto login(Map<String, String> form) throws Exception;

    void todayThrowTrash(Map<String, String> param) throws Exception;

    boolean checkUserByPwd(Map<String, String> param) throws Exception;

    UserDto findUserByUserIdx(int userIdx) throws Exception;
}
