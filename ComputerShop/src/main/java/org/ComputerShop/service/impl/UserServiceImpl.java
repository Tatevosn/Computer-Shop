package org.ComputerShop.service.impl;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.ComputerShop.model.dto.RegisterDTO;
import org.ComputerShop.model.dto.UserDTO;
import org.ComputerShop.model.entity.UserEntity;
import org.ComputerShop.model.entity.UserRoleEntity;
import org.ComputerShop.model.enums.UserRoleEnum;
import org.ComputerShop.repo.UserRepo;
import org.ComputerShop.repo.UserRoleRepo;
import org.ComputerShop.service.UserService;
import org.ComputerShop.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final PasswordEncoder encoder;
    private final LoggedUser loggedUser;
    private final HttpSession session;

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    public UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo, PasswordEncoder encoder, LoggedUser loggedUser, HttpSession session) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.encoder = encoder;
        this.loggedUser = loggedUser;
        this.session = session;
    }


    @Override
    public boolean checkCredentials(String username, String password) {
        UserEntity user = this.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        return encoder.matches(password, user.getPassword());
    }

    @Override
    public void login(String username) {
        UserEntity user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        UserEntity user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        UserEntity user = this.getUserByUsername(username);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    @Override
    @Transactional
    public void register(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        UserEntity userEntity = this.mapUser(registerDTO);


        if (userRepo.count() == 0) {
           // role.setRole(UserRoleEnum.ADMIN);
            userEntity.setRole(userRoleRepo.getRoleByRole(UserRoleEnum.ADMIN));
        } else {
            userEntity.setRole(userRoleRepo.getRoleByRole(UserRoleEnum.USER));
        }

        this.userRepo.save(userEntity);
        this.login(registerDTO.getUsername());
    }

    @Override
    public UserEntity getLoggedUser() {
        if (this.loggedUser.isLogged()) {
            return this.userRepo.findById(this.loggedUser.getId()).orElse(null);
        }
        return null;
    }

    @Override
    public void logout() {
        this.session.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }


    private UserEntity getUserByUsername(String username) {
        return this.userRepo.findByUsername(username).orElse(null);
    }

    private UserEntity mapUser(RegisterDTO registerDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(registerDTO.getFirstName());
        userEntity.setLastName(registerDTO.getLastName());
        userEntity.setUsername(registerDTO.getUsername());
        userEntity.setEmail(registerDTO.getEmail());
        // Encode the password before setting it
        userEntity.setPassword(encoder.encode(registerDTO.getPassword()));
        return userEntity;
    }


    private UserDTO mapUserDTO(UserEntity user) {
        return new UserDTO()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername());
    }





}
