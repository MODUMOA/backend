package com.saessak.momo.notice.controller;

import com.saessak.momo.global.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private static final String SUCCESS = "SUCCESS";
    @GetMapping
    public ResponseEntity<ResponseDto> noticeList() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), SUCCESS, null));
    }
}
