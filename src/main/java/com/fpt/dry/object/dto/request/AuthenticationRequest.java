package com.fpt.dry.object.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Creator", builderMethodName = "creator", buildMethodName = "create")
public class AuthenticationRequest {
    @NotBlank(message = "blank")
    private String username;
    @NotBlank(message = "blank")
    private String password;
}
