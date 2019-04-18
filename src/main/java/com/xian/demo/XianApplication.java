package com.xian.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@EnableSwagger2
@SpringBootApplication
public class XianApplication {

    // 我也不知道这个配置是干啥的，反正集成ES之后报错   availableProcessors is already set to [4], rejecting [4]
    // 加上这个配置之后一切ok了
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    public static void main(String[] args) {

        SpringApplication.run(XianApplication.class, args);
    }

}
