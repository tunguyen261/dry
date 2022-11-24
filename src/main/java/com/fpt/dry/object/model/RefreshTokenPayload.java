package com.fpt.dry.object.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Creator", buildMethodName = "create", builderMethodName = "creator")
public class RefreshTokenPayload implements Serializable {
    private UUID token;
    private TokenUserPayload user;
}

