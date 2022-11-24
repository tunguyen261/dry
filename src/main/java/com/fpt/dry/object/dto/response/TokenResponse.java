package com.fpt.dry.object.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Creator", buildMethodName = "create", builderMethodName = "creator")
public class TokenResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expired_at")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime expiredAt;

    @JsonProperty("refresh_token")
    private UUID refreshToken;
}
