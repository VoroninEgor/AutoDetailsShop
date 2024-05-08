package com.rmnk12k.controller;

import com.rmnk12k.config.Details;
import com.rmnk12k.config.JwtService;
import com.rmnk12k.dto.auth.LoginResponse;
import com.rmnk12k.dto.user.LoginUserRequest;
import com.rmnk12k.dto.user.RegisterUserRequest;
import com.rmnk12k.dto.user.UserResponse;
import com.rmnk12k.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "auth", description = "the auth API")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public UserResponse register(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        return authenticationService.signUp(registerUserRequest);
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody LoginUserRequest loginUserRequest) {
        Details authenticatedUser = authenticationService.authenticate(loginUserRequest);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        return new LoginResponse(jwtToken, jwtService.getExpirationTime());
    }
}
