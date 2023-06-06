package com.saessak.momo.trash.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface TrashMapper {
    int getTodayThrowTrash(Map<String, String> param) throws SQLException;

    void updateTodayThrowTrash(Map<String, String> param) throws SQLException;

    void insertTodayThrowTrash(Map<String, String> param) throws SQLException;

    ;
}
