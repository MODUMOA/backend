package com.saessak.momo.trash.model.mapper;

import com.saessak.momo.trash.dto.PrevPercentParam;
import com.saessak.momo.trash.dto.WeeklyEmissionParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrashMapper {
    // 오늘 요일 구하기
    int getNowDay() throws Exception;

    // 일주일 배출량 구하기
    int getWeeklyEmission(WeeklyEmissionParam param) throws Exception;

    // 전 주(전전주) 배출량 구하기
    int getPrevEmission(PrevPercentParam param) throws Exception;
}
