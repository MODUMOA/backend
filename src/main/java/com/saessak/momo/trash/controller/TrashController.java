package com.saessak.momo.trash.controller;

import com.saessak.momo.global.dto.ResponseDto;
import com.saessak.momo.trash.dto.DashboardItem;
import com.saessak.momo.trash.dto.PrevPercentParam;
import com.saessak.momo.trash.dto.TrashParam;
import com.saessak.momo.trash.dto.WeeklyEmissionParam;
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

            // 1. 카테고리
            int category = param.getTrashIdx();
            String stringCategory = "";

            if(category == 1){
                stringCategory = "일반쓰레기";
            }
            else if(category == 2){
                stringCategory = "음식물쓰레기";
            }
            else if(category == 3){
                stringCategory = "플라스틱";
            }

            dashboardItem.setCategory(stringCategory);


            // 2. 일주일 배출량
            int[] weeks = new int[7];
            WeeklyEmissionParam weeklyEmissionParam = new WeeklyEmissionParam();
            weeklyEmissionParam.setUserIdx(param.getUserIdx());
            weeklyEmissionParam.setTrashIdx(param.getTrashIdx());

            // 2 - 1) 오늘 요일 구하기
            int today = trashSerivce.getNowDay();

            // 2 - 2) today + 1 만큼 반복문 돌리기
            for(int i = today; i >= 0; i--){
                weeklyEmissionParam.setDateSub(today - i);
                weeks[i] = trashSerivce.getWeeklyEmission(weeklyEmissionParam);

            }
            dashboardItem.setWeeks(weeks);


            // 3. 총 합
            int sum = 0;
            for(int i = 0; i < 7; i++){
                sum += weeks[i];
            }
            dashboardItem.setTotal(sum);


            // 4. 평균
            dashboardItem.setAvg( sum / (today + 1) );


            // 5. 전 주 대비 증감량, Status
            PrevPercentParam prevPercentParam = new PrevPercentParam();
            boolean prevPercentStatus = false;

            prevPercentParam.setUserIdx(param.getUserIdx());
            prevPercentParam.setTrashIdx(param.getTrashIdx());

            // 5 - 1) 전 주 배출량 구하기
            prevPercentParam.setStartDay(today + 7);
            prevPercentParam.setEndDay(today + 1);

            int prevEmission = trashSerivce.getPrevEmission(prevPercentParam);

            // 5 - 2) 전전주 배출량 구하기
            prevPercentParam.setStartDay(today + 14);
            prevPercentParam.setEndDay(today + 8);

            int prevPrevEmission = trashSerivce.getPrevEmission(prevPercentParam);

            // 5 - 3) prevPercentStatus 구하기
            if(prevPrevEmission > prevEmission){
                // 배출량 줄었으면 true
                prevPercentStatus = true;
            }

            // 5 - 4) prevPercent 구하기
            int prevPercent = (int)((double)prevEmission / (double)prevPrevEmission * 100) - 100;

            dashboardItem.setPrevPercent(prevPercent);
            dashboardItem.setPrevPercentStatus(prevPercentStatus);


            // 6. 평균 배출량 대비 증감량, Status
            boolean avgPercentStatus = false;
            int seoulAvg = 0;

            // 6 - 1) 서울 평균 쓰레기양 설정
            if(category == 1){
                seoulAvg = 2226;
            }
            else if(category == 2){
                seoulAvg = 1743;
            }
            else if(category == 3){
                seoulAvg = 1981;
            }

            if(seoulAvg > prevEmission){
                avgPercentStatus = true;
            }

            int avgPercent = (int)((double)prevEmission / (double)seoulAvg * 100) - 100;

            dashboardItem.setAvgPercent(avgPercent);
            dashboardItem.setAvgPercentStatus(avgPercentStatus);


        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT.value(), FAIL, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, dashboardItem));
    }
}
