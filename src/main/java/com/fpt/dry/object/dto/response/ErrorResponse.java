package com.fpt.dry.object.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Creator", builderMethodName = "creator", buildMethodName = "create")
@JsonPropertyOrder({"status_code", "message", "errors", "timestamp"})
public class ErrorResponse {
    @JsonProperty("status_code")
    private int errorCode;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> errors;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp = LocalDateTime.now();
}
