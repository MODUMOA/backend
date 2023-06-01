package com.saessak.momo.user.model.mapper;

import com.saessak.momo.user.dto.SignupForm;
import com.saessak.momo.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface UserMapper {
    void insertUser(SignupForm form) throws SQLException;

    UserDto selectUser(Map<String, String> form) throws SQLException;
}
