package com.saessak.momo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = { "com.saessak.**.mapper" })
public class WebConfiguration {
}
