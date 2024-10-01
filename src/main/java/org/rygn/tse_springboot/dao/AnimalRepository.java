package org.rygn.tse_springboot.dao;

import java.util.List;

import org.rygn.tse_springboot.domain.Animal;

public interface AnimalRepository {

	Animal save(Animal animal);

	Animal findById(Long id);

	List<Animal> findAll();

	void deleteById(Long id);

	Integer count();

}
