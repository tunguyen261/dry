package com.fpt.dry.object.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "NVARCHAR(20)", nullable = false)
    private String title;

    @Column(columnDefinition = "DECIMAL(12, 4)", nullable = false)
    private Double price;

    private String image;

    @Column(columnDefinition = "text")
    private String description;
}
