package com.fpt.dry.repository;

import com.fpt.dry.object.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);
    List<User> findAllByUsername(String username);

}
