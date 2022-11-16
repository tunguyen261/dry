package com.fpt.dry.object.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    @NotBlank(message = "title must not be blank")
    private String title;

    @NotNull(message = "title must not be null")
    private Double price;

    private String image;

    private String description;
}
