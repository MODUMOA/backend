package com.saessak.momo.user.model.mapper;

import com.saessak.momo.user.dto.SignupForm;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface UserMapper {
    void insertUser(SignupForm form) throws SQLException;
}
