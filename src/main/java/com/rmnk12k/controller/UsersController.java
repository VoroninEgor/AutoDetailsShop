package com.rmnk12k.controller;

import com.rmnk12k.dto.user.UserCreateRequest;
import com.rmnk12k.dto.user.UserResponse;
import com.rmnk12k.dto.user.UserUpdateRequest;
import com.rmnk12k.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "users", description = "the users API")
public class UsersController {
    private final UserService userService;

    @GetMapping()
    public List<UserResponse> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        userService.create(userCreateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        userService.update(id, userUpdateRequest);
    }
}
