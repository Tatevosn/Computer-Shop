package org.ComputerShop.controller;

import jakarta.validation.Valid;
import org.ComputerShop.model.dto.ComputerDTO;
import org.ComputerShop.model.entity.ComputerEntity;
import org.ComputerShop.service.ComputerService;
import org.ComputerShop.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final LoggedUser loggedUser;
    private final ComputerService computerService;

    public ProductController(LoggedUser loggedUser, ComputerService computerService) {
        this.loggedUser = loggedUser;
        this.computerService = computerService;
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("loggedUser", loggedUser);
        List<ComputerEntity> computers = computerService.getAllComputers();
        model.addAttribute("computers", computers);
        return "products";
    }

    @GetMapping("/offers/add")
    public String addProduct(Model model) {
        model.addAttribute("loggedUser", loggedUser);
        return "product-add";
    }

    @PostMapping("/add-product")
    public String addProductPost(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loggedUser", loggedUser);
            return "product-add";
        }

        computerService.addComputer(computerDTO);
        return "redirect:/products";
    }

    @GetMapping("/offers/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("loggedUser", loggedUser);
        ComputerEntity computer = computerService.getComputerById(id);
        model.addAttribute("computer", computer);
        return "product-edit";
    }

    @PostMapping("/offers/edit/{id}")
    public String editProductPost(@PathVariable Long id,
                                   @Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loggedUser", loggedUser);
            return "product-edit";
        }

        computerService.updateComputer(id, computerDTO);
        return "redirect:/products";
    }

    @PostMapping("/offers/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        computerService.deleteComputer(id);
        return "redirect:/products";
    }

    @PostMapping("/offers/delete-all")
    public String deleteAllProducts() {
        computerService.deleteAllComputers();
        return "redirect:/products";
    }


}