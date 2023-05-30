package com.saessak.momo;

import com.saessak.momo.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestMapper mapper;

    @GetMapping("/test")
    public String hello() {
        return "hello";
    }

    @GetMapping("/test2")
    public String hello2() {
        return "SUCCESS";
    }

    @GetMapping("/test3")
    public String hello3() throws Exception {
        return mapper.test();
    }
}
