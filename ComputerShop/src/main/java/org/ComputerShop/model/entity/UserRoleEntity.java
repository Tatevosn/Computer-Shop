package org.ComputerShop.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.ComputerShop.model.enums.UserRoleEnum;

@Table(name = "roles")
@Entity
public class UserRoleEntity extends BaseEntity{

    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;



    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

}
