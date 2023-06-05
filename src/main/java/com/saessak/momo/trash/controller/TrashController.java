package com.saessak.momo.trash.controller;

import com.saessak.momo.global.dto.ResponseDto;
import com.saessak.momo.trash.dto.DashboardItem;
import com.saessak.momo.trash.dto.TrashParam;
import com.saessak.momo.trash.model.service.TrashSerivce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/trash")
@RequiredArgsConstructor
public class TrashController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private final TrashSerivce trashSerivce;

    @GetMapping()
    public ResponseEntity<ResponseDto> getDashboardInfo(@RequestBody TrashParam param) throws Exception{
        DashboardItem dashboardItem = new DashboardItem();

        try{
            // 카테고리

            // 일주일 배출량

            // 평균

            // 총

            // 전 주 대비 증감량, Status

            // 평균 배출량 대비 증감량, Status

        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, dashboardItem));
    }
}
