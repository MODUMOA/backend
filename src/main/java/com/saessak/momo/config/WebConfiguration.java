package com.saessak.momo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = { "com.saessak.**.mapper" })
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080", "http://localhost:8081", "http://43.202.34.154:80", "http://10.0.2.2:8080",
//                                "http://192.168.159.116:8080", "http://192.168.159.209:8080", "http://192.168.76.178:8080", "http://192.168.76.209:8080", "http://192.168.154.178:8080", "http://192.168.0.102:8080"
//                                ,"http://192.168.0.178:8080","http://192.168.103.64:8080",  "http://192.168.0.116:8080", "http://192.168.0.100:8080", "http://192.168.154.209:8080",
//                                "https://43.202.34.154:80", "http://modumoa.shop", "https://modumoa.shop") // 나중에 AWS 주소 추가해야
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
