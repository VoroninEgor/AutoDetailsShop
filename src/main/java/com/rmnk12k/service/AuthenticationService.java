package com.rmnk12k.service;

import com.rmnk12k.config.Details;
import com.rmnk12k.dto.user.LoginUserRequest;
import com.rmnk12k.dto.user.RegisterUserRequest;
import com.rmnk12k.dto.user.UserResponse;
import com.rmnk12k.entity.Cart;
import com.rmnk12k.entity.User;
import com.rmnk12k.exception.EmailNotUniqException;
import com.rmnk12k.exception.UserNotFoundException;
import com.rmnk12k.repo.CartRepo;
import com.rmnk12k.repo.UserRepo;
import com.rmnk12k.utill.UserRole;
import com.rmnk12k.utill.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepo userRepo;
    private final CartRepo cartRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserResponse signUp(RegisterUserRequest registerUserRequest) {
        log.info("register user with email: {}", registerUserRequest.email());

        Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
        try {
            User user = userRepo.save(User.builder()
                    .name(registerUserRequest.name())
                    .password(passwordEncoder.encode(registerUserRequest.password()))
                    .email(registerUserRequest.email())
                    .role(UserRole.USER)
                    .created(currentTime)
                    .updated(currentTime)
                    .build());
            Cart cart = Cart.builder()
                    .id(user.getId())
                    .build();
            cartRepo.save(cart);

            return UserMapper.mapUserToUserResponse(user);
        } catch (DataIntegrityViolationException e) {
            throw new EmailNotUniqException();
        }
    }

    public Details authenticate(LoginUserRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = userRepo.findByEmail(request.email()).orElseThrow(UserNotFoundException::new);
        return new Details(user);
    }
}
