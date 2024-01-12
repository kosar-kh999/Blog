package com.example.blog.controller;

import com.example.blog.model.dto.LoginUserDto;
import com.example.blog.model.dto.UserDto;
import com.example.blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity<Void> signUp(@RequestBody UserDto userDto) {
        userService.signUp(userDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/logIn")
    public ResponseEntity<UserDto> logIn(@RequestBody LoginUserDto loginUserDto) {
        UserDto userDto = userService.login(loginUserDto);
        return ResponseEntity.ok().body(userDto);
    }
}
