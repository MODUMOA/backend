package com.saessak.momo.user.controller;

import com.saessak.momo.global.dto.ResponseDto;
import com.saessak.momo.trash.dto.WeeklyEmissionParam;
import com.saessak.momo.trash.model.service.TrashSerivce;
import com.saessak.momo.user.dto.SignupForm;
import com.saessak.momo.user.dto.UserDto;
import com.saessak.momo.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private final UserService userService;
    private final TrashSerivce trashSerivce;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody UserDto form) throws Exception {

        userService.signup(form);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(), SUCCESS, null));
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody Map<String, String> form) throws Exception {
        UserDto loginUser = userService.login(form);

        if (loginUser == null) {
            throw new Exception("잘못된 아이디 또는 비밀번호");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("userIdx", loginUser.getUserIdx());
        result.put("userId", loginUser.getUserId());
        result.put("nickname", loginUser.getNickName());
        result.put("userName", loginUser.getUserName());
        result.put("token", loginUser.getToken());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, result));
    }

    @PostMapping("/authLogin")
    public ResponseEntity<ResponseDto> authLogin(@RequestBody String token) throws Exception {
        UserDto loginUser = userService.authLogin(token);

        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(HttpStatus.OK.value(), FAIL, null));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("userIdx", loginUser.getUserIdx());
        result.put("userId", loginUser.getUserId());
        result.put("nickname", loginUser.getNickName());
        result.put("userName", loginUser.getUserName());
        result.put("token", loginUser.getToken());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, result));
    }

    @GetMapping("/logout/{userIdx}")
    public ResponseEntity<ResponseDto> logout(@PathVariable String userIdx) throws Exception {
        userService.logout(userIdx);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, null));
    }

    @GetMapping("/checkDuplicateId/{userId}")
    public ResponseEntity<ResponseDto> checkId(@PathVariable String userId) throws Exception {

        boolean result = userService.findUserByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, result));
    }

    /**
     * 사용자 레벨, 경험치, 탄소배출량
     */
    @GetMapping("/info/{user_idx}")
    public ResponseEntity<ResponseDto> getUserStatus(@PathVariable("user_idx") int userIdx) throws Exception {
        UserDto findUser = userService.findUserByUserIdx(userIdx);
        int sum = 0;

        // 1. 일주일 배출량
        int[] weeks = new int[7];
        WeeklyEmissionParam weeklyEmissionParam = new WeeklyEmissionParam();
        weeklyEmissionParam.setUserIdx(userIdx);

        // 1 - 1) 오늘 요일 구하기
        int today = trashSerivce.getNowDay();

        // 1 - 2) today + 1 만큼 반복문 돌리기
        for (int idx = 1; idx <= 3; idx++) {
            weeklyEmissionParam.setTrashIdx(idx);
            double value = idx == 3 ? 2.75 : 1.08;

            for(int i = today; i >= 0; i--){
                weeklyEmissionParam.setDateSub(today - i);
                weeks[i] = trashSerivce.getWeeklyEmission(weeklyEmissionParam);
            }

            // 3. 총 합
            for(int i = 0; i < 7; i++) {
                sum += weeks[i] * value;
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("level", findUser.getLevel());
        result.put("exp", (int) Math.ceil(findUser.getExp() / 100));
        result.put("weeklyEmission", sum);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, result));
    }

    /**
     * 비밀번호 검증
     * userIdx, password
     */
    @PostMapping("/check")
    public ResponseEntity<ResponseDto> checkPwd(@RequestBody Map<String, String> param) throws Exception {
        boolean checkValue = userService.checkUserByPwd(param);

        if (checkValue) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, null));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDto(HttpStatus.UNAUTHORIZED.value(), FAIL, null));
        }

    }

    /**
     * 쓰레기 분리수거 완료
     * userIdx, type, weight
     */
    @PostMapping("/trash")
    public ResponseEntity<ResponseDto> test(@RequestBody Map<String, String> param) throws Exception {
        userService.todayThrowTrash(param);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, null));
    }


}
