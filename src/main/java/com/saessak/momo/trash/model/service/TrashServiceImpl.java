package com.saessak.momo.trash.model.service;

import com.saessak.momo.trash.dto.PrevPercentParam;
import com.saessak.momo.trash.dto.WeeklyEmissionParam;
import com.saessak.momo.trash.model.mapper.TrashMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrashServiceImpl implements TrashSerivce {
    private final TrashMapper trashMapper;
    @Override
    public int getNowDay() throws Exception {
        return trashMapper.getNowDay();
    }

    @Override
    public int getWeeklyEmission(WeeklyEmissionParam param) throws Exception {
        return trashMapper.getWeeklyEmission(param);
    }

    @Override
    public int getPrevEmission(PrevPercentParam param) throws Exception {
        return trashMapper.getPrevEmission(param);
    }
}
