package com.blog.react_spring_blog.security;

import org.springframework.context.annotation.Bean;

public class PwdEncoderConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
