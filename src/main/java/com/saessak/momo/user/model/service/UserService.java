package com.saessak.momo.user.model.service;


import com.saessak.momo.user.dto.SignupForm;

public interface UserService {
    void signup(SignupForm form) throws Exception;
}
