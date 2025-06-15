package org.ComputerShop.controller;

import org.ComputerShop.model.entity.UserEntity;
import org.ComputerShop.service.UserService;
import org.ComputerShop.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final UserService userService;

    public HomeController(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("loggedUser", loggedUser);
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }



    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("username", loggedUser.isLogged() ? loggedUser.getUsername() : "Guest");
        return "home";
    }



}
