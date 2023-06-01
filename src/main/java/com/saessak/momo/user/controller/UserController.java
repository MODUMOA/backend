package com.saessak.momo.user.controller;

import com.saessak.momo.global.dto.ResponseDto;
import com.saessak.momo.user.dto.SignupForm;
import com.saessak.momo.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private static final String SUCCESS = "SUCCESS";
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody SignupForm form) throws Exception {

        userService.signup(form);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, null));
    }
}
