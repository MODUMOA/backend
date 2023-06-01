package com.saessak.momo.user.model.service;


import com.saessak.momo.user.dto.SignupForm;
import com.saessak.momo.user.dto.UserDto;

import java.util.Map;

public interface UserService {
    void signup(SignupForm form) throws Exception;

    UserDto login(Map<String, String> form) throws Exception;
}
