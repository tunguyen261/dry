package com.fpt.dry.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.Payload;
import com.fpt.dry.object.model.RefreshTokenPayload;
import com.fpt.dry.object.model.TokenUserPayload;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenService {
    private final RedissonClient redissonClient;

    @Value("${token.jwt.secret-key:a3972eed-ef62-4154-ae4b-80a5fba7a073}")
    private String secretKey;

    @Value("${token.jwt.expiration-time:300000}")
    private Long expirationTime;

    private RMapCache<UUID, String> tokenMap;
    private RMapCache<String, TokenUserPayload> userMap;
    @PostConstruct
    private void init() {
        tokenMap = redissonClient.getMapCache("REFRESH_TOKEN_MAP_TO_USER_EMAIL");
        userMap = redissonClient.getMapCache("USER_EMAIL_MAP_TO_USER_INFO");
    }
    public String generateAccessToken(@NonNull TokenUserPayload user){
        Map<String, Object> payload = new HashMap<>();
        payload.put("username",user.getUsername());
        if (!ObjectUtils.isEmpty(user.getRoles())) {
            payload.put("roles", List.copyOf(user.getRoles()));
        }
        JWTCreator.Builder builder = JWT.create();
        builder.withKeyId(UUID.randomUUID().toString())
                .withPayload(payload).withIssuedAt(new Date()).withExpiresAt(Timestamp.valueOf(LocalDateTime.now()
                        .plusSeconds(expirationTime/1000)));
        return builder.sign(Algorithm.HMAC512(secretKey));
    }
    public TokenUserPayload getAuthInfoFromToken(String token){
        String json = new String(Base64.getDecoder().decode(JWT.decode(token).getPayload()));
        Payload payload =new JWTParser().parsePayload(json);
        Map<String, Claim> claims = payload.getClaims();
        return TokenUserPayload.creator()
                .username(claims.get("username").asString())
                .roles(Set.copyOf(claims.get("roles").asList(String.class)))
                .create();
    }

    public boolean validateToken(String authToken){
        try{
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(authToken);
            return true;
        } catch (JWTVerificationException | NullPointerException | IllegalArgumentException ex){
            log.error("Validate token failed: " +ex.getMessage());
            return false;
        }
    }
    public RefreshTokenPayload generateRefreshToken(TokenUserPayload user) {
        RefreshTokenPayload refreshTokenPayload =
                RefreshTokenPayload.creator().token(UUID.randomUUID()).user(user).create();

        tokenMap.fastPut(refreshTokenPayload.getToken(), user.getUsername(), 6, TimeUnit.HOURS);
        userMap.fastPut(user.getUsername(), user, 6, TimeUnit.HOURS);

        return refreshTokenPayload;
    }
    public class TokenExpiredException extends RuntimeException {
        public TokenExpiredException(String message) {
            super(message);
        }
    }
    public RefreshTokenPayload verifyExpiration(UUID token) {
        if (tokenMap.remainTimeToLive(token) > 0) {
            String userEmail = tokenMap.get(token);
            TokenUserPayload user = userMap.get(userEmail);
            // In case you want to renew refresh token on successful verify
//            return generateRefreshToken(user);

            // In case you want to renew refresh token on successful verify
            return RefreshTokenPayload.creator().token(token).user(user).create();
        } else {
            throw new TokenExpiredException("Refresh token has been expired");
        }
    }

    public TokenUserPayload getUserById(long id) {
        return userMap.get(id);
    }
}
