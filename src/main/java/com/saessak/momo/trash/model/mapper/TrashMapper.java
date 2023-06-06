package com.saessak.momo.trash.model.mapper;

import com.saessak.momo.trash.dto.PrevPercentParam;
import com.saessak.momo.trash.dto.WeeklyEmissionParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface TrashMapper {

    // 오늘 요일 구하기
    int getNowDay() throws Exception;

    // 일주일 배출량 구하기
    int getWeeklyEmission(WeeklyEmissionParam param) throws Exception;

    // 전 주(전전주) 배출량 구하기
    int getPrevEmission(PrevPercentParam param) throws Exception;

    int getTodayThrowTrash(Map<String, String> param) throws SQLException;

    void updateTodayThrowTrash(Map<String, String> param) throws SQLException;

    void insertTodayThrowTrash(Map<String, String> param) throws SQLException;


}
