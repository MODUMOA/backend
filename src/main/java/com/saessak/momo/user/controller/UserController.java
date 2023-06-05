package com.saessak.momo.user.controller;

import com.saessak.momo.global.dto.ResponseDto;
import com.saessak.momo.user.dto.SignupForm;
import com.saessak.momo.user.dto.UserDto;
import com.saessak.momo.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final UserService userService;

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

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, result));
    }

    @PostMapping("/test")
    public ResponseEntity<ResponseDto> test(@RequestBody Map<String, String> param) throws Exception {
        log.info("param={}", param);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, null));
    }
}
