//package com.xian.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class CORSConfig {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
////				registry.addMapping("/api/**");
//                registry.addMapping("/**")
//                        .allowedOrigins("http://domain.com", "http://domain2.com")
//                        .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
//                        .allowCredentials(false).maxAge(3600);
//            }
//        };
//    }
//}
