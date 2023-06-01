package com.saessak.momo.user.model.service;

import com.saessak.momo.user.dto.SignupForm;
import com.saessak.momo.user.dto.UserDto;
import com.saessak.momo.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public void signup(SignupForm form) throws Exception {
        log.info("form={}", form);
        userMapper.insertUser(form);

    }

    @Override
    public UserDto login(Map<String, String> form) throws Exception {
        log.info("form={}", form);
        UserDto findUser = userMapper.selectUser(form);

        if (findUser == null || !findUser.getUserPwd().equals(form.get("userPwd"))) {
            return null;
        }

        return findUser;
    }
}
