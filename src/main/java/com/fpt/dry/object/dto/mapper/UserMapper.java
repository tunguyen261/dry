package com.fpt.dry.object.dto.mapper;


import com.fpt.dry.object.dto.request.UserRequest;
import com.fpt.dry.object.dto.response.UserResponse;
import com.fpt.dry.object.entity.Role;
import com.fpt.dry.object.entity.User;
import com.fpt.dry.object.model.SecurityUserDetails;
import com.fpt.dry.object.model.TokenUserPayload;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface UserMapper {

    User mapCreateRequestToEntity(UserRequest request);

    @Mapping(target = "roles", expression = "java(mapEntityRolesToResponse(user.getRoles()))")
    UserResponse mapUserToResponse(User user);
    void updateEntity(@MappingTarget User entity,UserRequest request);
    default TokenUserPayload mapUserDetailsToTokenPayload(SecurityUserDetails userDetails){
        User user =  userDetails.user();
        return TokenUserPayload.creator()
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()))
                .create();
    }

    default List<String> mapEntityRolesToResponse(Set<Role> entityRoles) {
                return  entityRoles.stream().map(role -> role.getName().name()).collect(Collectors.toList());
    }
}
