package com.saessak.momo.user.model.mapper;

import com.saessak.momo.user.dto.SignupForm;
import com.saessak.momo.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface UserMapper {
    void insertUser(UserDto form) throws SQLException;

    void insertUserStatus(Map<String, Integer> param) throws SQLException;

    UserDto selectUser(Map<String, String> form) throws SQLException;

    int selectTreeCategory() throws SQLException;

    UserDto findUserByUserIdx(String userIdx) throws SQLException;

    int findUserByUserId(String userId) throws SQLException;

    void updateToken(UserDto user) throws SQLException;

    UserDto findUserByToken(String token) throws SQLException;

    void deleteTokenByUserIdx(String userIdx) throws SQLException;
}
