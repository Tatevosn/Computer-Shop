package org.ComputerShop.config;

import org.ComputerShop.model.entity.UserRoleEntity;
import org.ComputerShop.model.enums.UserRoleEnum;
import org.ComputerShop.repo.UserRoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner initRoles(UserRoleRepo userRoleRepo) {
        return args -> {
            if (userRoleRepo.count() == 0) {
                UserRoleEntity adminRole = new UserRoleEntity();
                adminRole.setRole(UserRoleEnum.ADMIN);
                userRoleRepo.save(adminRole);

                UserRoleEntity userRole = new UserRoleEntity();
                userRole.setRole(UserRoleEnum.USER);
                userRoleRepo.save(userRole);
            }
        };
    }

}
