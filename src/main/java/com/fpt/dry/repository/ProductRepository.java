package com.fpt.dry.repository;

import com.fpt.dry.object.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByTitle(String title);

    List<Product> findAllByTitle(String title);
}
