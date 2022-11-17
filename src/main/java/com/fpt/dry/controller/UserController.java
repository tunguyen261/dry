package com.fpt.dry.controller;


import com.fpt.dry.object.dto.request.UserRequest;
import com.fpt.dry.object.entity.User;
import com.fpt.dry.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest request){
        User response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }

}
