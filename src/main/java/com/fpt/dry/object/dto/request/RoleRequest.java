package com.fpt.dry.object.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleRequest {
    @NotBlank(message = "RoleName must not be blank")
    private String name;
}
