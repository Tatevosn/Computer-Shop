package org.ComputerShop.repo;

import org.ComputerShop.model.entity.UserRoleEntity;
import org.ComputerShop.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepo extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity getRoleByRole(UserRoleEnum userRoleEnum);
    //  Optional<UserRoleEntity> findByName(String name);
}
