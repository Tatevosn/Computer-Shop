package org.ComputerShop.service;

import org.ComputerShop.model.dto.ComputerDTO;
import org.ComputerShop.model.entity.ComputerEntity;

import java.util.List;

public interface ComputerService {
    void addComputer(ComputerDTO computerDTO);

    void updateComputer(Long id, ComputerDTO computerDTO);

    void deleteComputer(Long id);

    void deleteAllComputers();

    void getComputer();

    List<ComputerEntity> getAllComputers();

    ComputerEntity getComputerById(Long id);

    void getComputersByUser();
}
