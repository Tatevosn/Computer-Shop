package org.ComputerShop.repo;

import org.ComputerShop.model.entity.ComputerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepo extends JpaRepository<ComputerEntity, Long> {
}
