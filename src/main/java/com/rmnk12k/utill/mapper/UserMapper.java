package com.rmnk12k.utill.mapper;

import com.rmnk12k.dto.user.UserResponse;
import com.rmnk12k.entity.User;

public class UserMapper {
    public static UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .created(user.getCreated())
                .updated(user.getUpdated())
                .build();
    }
}
