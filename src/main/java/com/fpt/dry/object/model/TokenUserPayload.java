package com.fpt.dry.object.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Creator", buildMethodName = "create", builderMethodName = "creator")
public class TokenUserPayload  implements Serializable {
    private String username;
    private Set<String> roles;


}
