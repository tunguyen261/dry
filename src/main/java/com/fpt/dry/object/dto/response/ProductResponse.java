package com.fpt.dry.object.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Creator", builderMethodName ="creator", buildMethodName= "create")
public class ProductResponse {
    private Long id;
    private String title;
    private Double price;
    private String image;
    private String description;
}
