package org.rygn.tse_springboot.dao;

import org.rygn.tse_springboot.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
