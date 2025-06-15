package org.ComputerShop.service;


import org.ComputerShop.model.dto.RegisterDTO;
import org.ComputerShop.model.dto.UserDTO;
import org.ComputerShop.model.entity.UserEntity;

public interface UserService {



    boolean checkCredentials(String username, String password);

    void login(String username) ;


    UserDTO findUserByEmail(String email) ;

    UserDTO findUserByUsername(String username);

    void register(RegisterDTO registerDTO) ;

    UserEntity getLoggedUser();

    void logout() ;
}
