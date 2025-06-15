package org.ComputerShop.controller;

import jakarta.validation.Valid;
import org.ComputerShop.model.dto.LoginDTO;
import org.ComputerShop.model.dto.RegisterDTO;
import org.ComputerShop.service.UserService;
import org.ComputerShop.util.LoggedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    private final LoggedUser loggedUser;
    private final UserService userService;

    public UserController(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", result);
            return "redirect:/users/login";
        }
        boolean validCredentials = this.userService.checkCredentials(loginDTO.getUsername(), loginDTO.getPassword());
        if (!validCredentials) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("validCredentials", false);
            return "redirect:/users/login";
        }
        this.userService.login(loginDTO.getUsername());
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("loggedUser", loggedUser);
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }
        model.addAttribute("registerDTO", new RegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "registerDTO",
                            "confirmPassword",
                            "Passwords must be the same."
                    )
            );
        }
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> logger.error("Validation error: {}", error.getDefaultMessage()));
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", result);
            return "redirect:/register";
        }
        userService.register(registerDTO);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.userService.logout();
        return "redirect:/";
    }
}