package com.saessak.momo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface TestMapper {
    String test() throws SQLException;
}
