package com.fpt.dry.service;


import com.fpt.dry.object.constant.SystemRole;
import com.fpt.dry.object.dto.mapper.UserMapper;
import com.fpt.dry.object.dto.request.UserRequest;
import com.fpt.dry.object.entity.Role;
import com.fpt.dry.object.entity.User;
import com.fpt.dry.object.model.SecurityUserDetails;
import com.fpt.dry.repository.RoleRepository;
import com.fpt.dry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public User findUserByUsername (String username){
        return userRepository.findByUsername(username);
    }
    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->
                new RuntimeException("Not found User with id: " +id));
    }
    public User createUser(UserRequest request){
        User entity= userMapper.mapCreateRequestToEntity(request);
        Role userRole = roleRepository.findRoleByName(SystemRole.USER);
        entity.setRoles(Collections.singleton(userRole));
        return userRepository.save(entity);
    }

    public void deleteUser(Long id) {
        User entity = findUserById(id);
        userRepository.delete(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new SecurityUserDetails(user);
    }
}
