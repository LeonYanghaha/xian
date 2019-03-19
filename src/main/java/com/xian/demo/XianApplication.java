package com.xian.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600)
@SpringBootApplication
public class XianApplication {

    public static void main(String[] args) {

        SpringApplication.run(XianApplication.class, args);
    }

}
