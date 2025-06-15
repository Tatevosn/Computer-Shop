package org.ComputerShop.init;

import org.ComputerShop.service.impl.ComputerServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
@Component
public class FirstInit implements CommandLineRunner {

    private final ComputerServiceImpl computerService;

    public FirstInit(ComputerServiceImpl computerService) {
        this.computerService = computerService;
    }


    @Override
    public void run(String... args) throws Exception {

        this.computerService.initComputers();


    }


}
