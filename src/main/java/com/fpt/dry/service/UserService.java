package com.fpt.dry.service;


import com.fpt.dry.object.constant.SystemRole;
import com.fpt.dry.object.dto.mapper.UserMapper;
import com.fpt.dry.object.dto.request.UserRequest;
import com.fpt.dry.object.entity.Role;
import com.fpt.dry.object.entity.User;
import com.fpt.dry.repository.RoleRepository;
import com.fpt.dry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    public User findUser(String username){
//        return userRepository.findByUsername(username).orElseThrow(()->
//                new RuntimeException("Not Found User Name with username is: "+ username));
//    }

    public User createUser(UserRequest request){
        User entity= userMapper.mapCreateRequestToEntity(request);
//        entity.setRoles(Collections.singleton(new Role(SystemRole.USER)));

//        entity.setRoles();

        return userRepository.save(entity);
    }

}
