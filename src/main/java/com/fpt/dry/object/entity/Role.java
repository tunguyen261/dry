package com.fpt.dry.object.entity;

import com.fpt.dry.object.constant.SystemRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private SystemRole name;

    @ManyToMany
    @JoinTable(name = "user_roles",
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<User> users;

    public Role(SystemRole roleName) {
        this.name = roleName;
    }
}
