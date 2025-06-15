package org.ComputerShop.service.impl;

import org.ComputerShop.model.dto.ComputerDTO;
import org.ComputerShop.model.entity.ComputerEntity;
import org.ComputerShop.repo.ComputerRepo;
import org.ComputerShop.service.ComputerService;
import org.ComputerShop.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {

    private final UserService userService;
    private final ComputerRepo computerRepo;

    public ComputerServiceImpl(UserService userService, ComputerRepo computerRepo) {
        this.userService = userService;
        this.computerRepo = computerRepo;
    }

    @Override
    public void addComputer(ComputerDTO computerDTO) {
        ComputerEntity computer = new ComputerEntity();
        computer.setBrand(computerDTO.getBrand());
        computer.setModel(computerDTO.getModel());
        computer.setPrice(computerDTO.getPrice());
        computer.setProcessor(computerDTO.getProcessor());
        computer.setRam(computerDTO.getRam());
        computer.setStorage(computerDTO.getStorage());
        computer.setGraphicCard(computerDTO.getGraphicCard());

        this.computerRepo.save(computer);
    }

    @Override
    public void updateComputer(Long id, ComputerDTO computerDTO) {

        ComputerEntity computer = this.computerRepo.findById(id).orElse(null);
        if (computer != null) {
            computer.setBrand(computerDTO.getBrand());
            computer.setModel(computerDTO.getModel());
            computer.setPrice(computerDTO.getPrice());
            computer.setProcessor(computerDTO.getProcessor());
            computer.setRam(computerDTO.getRam());
            computer.setStorage(computerDTO.getStorage());
            computer.setGraphicCard(computerDTO.getGraphicCard());

            this.computerRepo.save(computer);
        }


    }

    @Override
    public void deleteComputer(Long id) {
        computerRepo.deleteById(id);
    }

    @Override
    public void deleteAllComputers() {
        computerRepo.deleteAll();
    }

    @Override
    public void getComputer() {
        System.out.println("Computer retrieved");
    }

    @Override
    public List<ComputerEntity> getAllComputers() {


        return this.computerRepo.findAll();
    }

    @Override
    public ComputerEntity getComputerById(Long id) {

        return this.computerRepo.findById(id).orElse(null);
    }

    @Override
    public void getComputersByUser() {
        System.out.println("Computers retrieved by user");
    }

    public void initComputers() {

        ComputerEntity computer1 = new ComputerEntity();
        computer1.setBrand("Dell");
        computer1.setModel("Inspiron");
        computer1.setPrice(999.99);
        computer1.setProcessor("Intel Core i5");
        computer1.setRam("16GB");
        computer1.setStorage("512GB SSD");
        computer1.setGraphicCard("Nvidia GeForce GTX 1650");

        ComputerEntity computer2 = new ComputerEntity();
        computer2.setBrand("HP");
        computer2.setModel("Pavilion");
        computer2.setPrice(799.99);
        computer2.setProcessor("Intel Core i3");
        computer2.setRam("8GB");
        computer2.setStorage("256GB SSD");
        computer2.setGraphicCard("Intel UHD Graphics 630");

        this.computerRepo.save(computer1);
        this.computerRepo.save(computer2);




    }
}
