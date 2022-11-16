package com.fpt.dry.object.dto.mapper;

import com.fpt.dry.object.dto.request.ProductRequest;
import com.fpt.dry.object.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product mapCreateRequestToEntity(ProductRequest request);

    void updateEntity(@MappingTarget Product entity, ProductRequest request);
}
