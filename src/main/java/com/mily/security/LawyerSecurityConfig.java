package com.mily.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(1)
public class LawyerSecurityConfig {
    @Bean
    SecurityFilterChain lawyerFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
//                .csrf((csrf) -> csrf.disable())
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .formLogin((formLogin) -> formLogin
                        .loginPage("/lawyer/login")
                        .passwordParameter("password")
                        .usernameParameter("name")
                        .defaultSuccessUrl("/")
                        .failureUrl("/lawyer/login"))
                .logout((logout) -> logout
                        .invalidateHttpSession(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/lawyer/logout"))
                        .logoutSuccessUrl("/"))
                ;
        return http.build();
    }
}
