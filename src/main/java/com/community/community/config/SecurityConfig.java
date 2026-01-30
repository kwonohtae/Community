package com.community.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.community.community.user.mapper.UserMapper;
import com.community.community.user.service.impl.CustomUserDetailsService; // Will create this class soon

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (개발 편의를 위해, 실제 운영에서는 활성화 필요)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/**").permitAll() // 모든 요청을 허용 (개발 편의를 위해, 실제 운영에서는 역할에 따라 접근 제어 필요)
                .anyRequest().authenticated()
            );

        return http.build();
    }

    // Expose AuthenticationManager as a bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Provide a custom UserDetailsService
    @Bean
    public UserDetailsService userDetailsService(UserMapper userMapper) {
        return new CustomUserDetailsService(userMapper);
    }
}
