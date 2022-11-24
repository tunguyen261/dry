package com.fpt.dry.controller;

import com.fpt.dry.object.dto.request.AuthenticationRequest;
import com.fpt.dry.object.dto.response.TokenResponse;
import com.fpt.dry.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("login")
    public ResponseEntity<TokenResponse> getAccessToken(@RequestBody AuthenticationRequest request){
        System.out.println(true);
        TokenResponse response = authenticationService.createAccessToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("refresh-token/{token}")
    public ResponseEntity<TokenResponse> refreshAccessToken(@PathVariable UUID token){
        TokenResponse response = authenticationService.renewAccessToken(token);
        return ResponseEntity.ok(response);
    }

}
