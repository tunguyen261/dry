package com.fpt.dry.repository;

import com.fpt.dry.object.constant.SystemRole;
import com.fpt.dry.object.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    Role findRoleByName(SystemRole name);

    List<Role> findAllByName(String name);

    @Query(value = "SELECT * FROM roles where name = :roleName", nativeQuery = true)
    Role findRoleByName(@Param("roleName") String roleName);

}
