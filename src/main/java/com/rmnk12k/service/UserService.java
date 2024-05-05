package com.rmnk12k.service;

import com.rmnk12k.dto.user.UserCreateRequest;
import com.rmnk12k.dto.user.UserResponse;
import com.rmnk12k.dto.user.UserUpdateRequest;
import com.rmnk12k.entity.Cart;
import com.rmnk12k.entity.User;
import com.rmnk12k.exception.NotUniqLoginOrEmailException;
import com.rmnk12k.exception.UserNotFoundException;
import com.rmnk12k.repo.CartRepo;
import com.rmnk12k.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;
    private final CartRepo cartRepo;

    public void create(UserCreateRequest userCreateRequest) {
        log.info("create user: {}", userCreateRequest);

        Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
        try {
            User user = userRepo.save(User.builder()
                    .name(userCreateRequest.name())
                    .password(userCreateRequest.password())
                    .email(userCreateRequest.email())
                    .role(userCreateRequest.role())
                    .created(currentTime)
                    .updated(currentTime)
                    .build());
            Cart cart = Cart.builder()
                    .id(user.getId())
                    .build();
            cartRepo.save(cart);
        } catch (DataIntegrityViolationException e) {
            throw new NotUniqLoginOrEmailException();
        }
    }

    public void delete(Long id) {
        log.info("delete user with id: {}", id);
        userRepo.deleteById(id);
    }

    public void update(Long id, UserUpdateRequest userUpdateRequest) {
        log.info("update user with id: {}, new user: {}", id, userUpdateRequest);

        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User oldUser = optionalUser.get();
            User updatedUser = User.builder()
                    .name(userUpdateRequest.name())
                    .password(userUpdateRequest.password())
                    .email(userUpdateRequest.email())
                    .role(userUpdateRequest.role())
                    .created(oldUser.getCreated())
                    .updated(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            userRepo.save(updatedUser);
        }
    }

    public UserResponse getUser(Long id) {
        log.info("get user with id: {}", id);

        User user = userRepo.findById(id).orElseThrow(UserNotFoundException::new);
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .created(user.getCreated())
                .updated(user.getUpdated())
                .build();
    }

    public List<UserResponse> getAllUser() {
        log.info("get all users");

        return userRepo.findAll().stream().map(
                user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .created(user.getCreated())
                        .updated(user.getUpdated())
                        .build())
                .toList();
    }
}
