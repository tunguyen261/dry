package com.fpt.dry.object.dto.mapper;


import com.fpt.dry.object.dto.request.RoleRequest;
import com.fpt.dry.object.dto.request.UserRequest;
import com.fpt.dry.object.entity.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface RoleMapper {

    Role mapCreateRequestToEntity(UserRequest request);

    void updateEntity(@MappingTarget Role entity, RoleRequest request);
}
