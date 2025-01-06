package com.blog.react_spring_blog.security;

import com.blog.react_spring_blog.security.jwt.JwtAuthenticationEntryPoint;
import com.blog.react_spring_blog.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntiyPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain FilterChan(HttpSecurity http) throws Exception {
        return http
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf( csrf -> csrf.disalbe())
                .corf(cors -> cors.configurationSourfce(corsConfigurationSource))
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/board/list",
                                                                                "/board/{boardId}",
                                                                                "/board/search",
                                                                                "/user/checkId",
                                                                                "/user/register",
                                                                                "/user/login",
                                                                                "/board/{boardId}/comment/list/**",
                                                                                "/board/{boardId}/file/download/**").permitAll()
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/board/**").hasRole("USER")
                        .requestMatchers("/board/{boardId}/comment/**}").hasRole("USER")
                        .requestMatchers("/board/{boardId}/file/**").hasRole("USER")


                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                )


                .sessionManagement()
                .exceptionHandling()
                .addFilterBefore()
                .build();

    }





}
