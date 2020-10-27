package org.rygn.tse_springboot.service;

import java.util.List;

import org.rygn.tse_springboot.domain.Animal;

public interface ZooService {

	public List<Animal> findAllAnimals();

	public Animal findAnimal(Long id);

	public Animal createAnimal(Animal animal);

	public void deleteAnimal(Long id);

	public Animal saveAnimal(Animal foundAnimal);
}
