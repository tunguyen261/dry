package com.fpt.dry.object.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Creator", builderMethodName ="creator", buildMethodName= "create")
public class UserResponse {
    private Long id;

    private  String username;

    private List<String> roles;
}
