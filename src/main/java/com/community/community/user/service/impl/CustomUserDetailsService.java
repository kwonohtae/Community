package com.community.community.user.service.impl;

import com.community.community.user.dto.UserResponseDto;
import com.community.community.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserResponseDto user = userMapper.findById(userId);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with userId: " + userId);
        }

        // For simplicity, hardcoding ROLE_USER. In a real app, authorities would come from DB.
        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) // Default role for now
        );
    }
}
