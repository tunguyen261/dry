package com.fpt.dry.service;

import com.fpt.dry.object.dto.mapper.UserMapper;
import com.fpt.dry.object.dto.request.AuthenticationRequest;
import com.fpt.dry.object.dto.response.TokenResponse;
import com.fpt.dry.object.model.RefreshTokenPayload;
import com.fpt.dry.object.model.SecurityUserDetails;
import com.fpt.dry.object.model.TokenUserPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private final UserService userService;
    private final UserMapper userMapper;

    @Value("${token.jwt.expiration-time:300000}")
    private Long expirationTime;

    public TokenResponse createAccessToken(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUserDetails principal=(SecurityUserDetails) authentication.getPrincipal();
        System.out.println(principal);
        TokenUserPayload user = userMapper.mapUserDetailsToTokenPayload(principal);
        String accessToken = tokenService.generateAccessToken(user);
        UUID refreshToken = tokenService.generateRefreshToken(user).getToken();

        return TokenResponse.creator()
                .accessToken(accessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(expirationTime/1000))
                .refreshToken(refreshToken)
                .create();
    }

    public TokenResponse renewAccessToken(UUID token) {
        RefreshTokenPayload refreshTokenPayload = tokenService.verifyExpiration(token);

        TokenUserPayload user = refreshTokenPayload.getUser();
        String newAccessToken = tokenService.generateAccessToken(user);

        return TokenResponse.creator()
                .accessToken(newAccessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(expirationTime / 1000))
                .refreshToken(token)
                .create();
    }

//    public UserResponse registerUser(CreateUserRequest request) {
//        return userMapper.mapUserToResponse(userService.createUser(request));
//    }
}
