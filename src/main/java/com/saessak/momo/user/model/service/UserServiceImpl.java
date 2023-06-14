package com.saessak.momo.user.model.service;

import com.saessak.momo.trash.model.mapper.TrashMapper;
import com.saessak.momo.user.dto.SignupForm;
import com.saessak.momo.user.dto.UserDto;
import com.saessak.momo.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final TrashMapper trashMapper;

    /**
     * 회원가입 로직
     * 무결성을 위해 Transaction + 2개의 SQL
     */
    @Override
    public void signup(UserDto form) throws Exception {

        // 검증로직은 미구현 (시간 많으면 할 예정
        userMapper.insertUser(form);
        Map<String, Integer> param = new HashMap<>();
        param.put("userIdx", form.getUserIdx());

        int cnt = userMapper.selectTreeCategory();
        for (int i = 1; i <= cnt; i++) {
            param.put("treeIdx", i);
            userMapper.insertUserStatus(param);
        }
    }

    @Override
    public UserDto login(Map<String, String> form) throws Exception {
        UserDto findUser = userMapper.selectUser(form);

        if (findUser == null || !findUser.getUserPwd().equals(form.get("userPwd"))) {
            return null;
        }

        // UUID 밀어 넣기
        String token = UUID.randomUUID().toString();
        findUser.setToken(token);
        userMapper.updateToken(findUser);

        return findUser;
    }

    @Override
    public UserDto authLogin(String token) throws Exception {
        return userMapper.findUserByToken(token);
    }

    @Override
    public void logout(String userIdx) throws Exception {
        userMapper.deleteTokenByUserIdx(userIdx);
    }

    @Override
    public void todayThrowTrash(Map<String, String> param) throws Exception {
        param.put("date", String.valueOf(LocalDate.now()));

        int hasData = trashMapper.getTodayThrowTrash(param);

        // 오늘 이미 버린적이 있으면 update
        if (hasData == 1) {
            trashMapper.updateTodayThrowTrash(param);
        } else {
            trashMapper.insertTodayThrowTrash(param);
        }
    }

    @Override
    public boolean checkUserByPwd(Map<String, String> param) throws Exception {
        UserDto findUser = userMapper.findUserByUserIdx(param.get("userIdx"));

        if (findUser != null && param.get("password").equals(findUser.getUserPwd())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDto findUserByUserIdx(int userIdx) throws Exception {
        return userMapper.findUserByUserIdx(String.valueOf(userIdx));
    }

    @Override
    public boolean findUserByUserId(String userId) throws Exception {

        int cnt = userMapper.findUserByUserId(userId);

        if (cnt > 0) {
            return false;
        }

        return true;
    }


}
