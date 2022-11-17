package com.fpt.dry.object.dto.mapper;


import com.fpt.dry.object.dto.request.UserRequest;
import com.fpt.dry.object.entity.User;
import org.mapstruct.*;


@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface UserMapper {

    User mapCreateRequestToEntity(UserRequest request);

    void updateEntity(@MappingTarget User entity,UserRequest request);

}
