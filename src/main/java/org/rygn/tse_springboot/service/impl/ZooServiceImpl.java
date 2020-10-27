package org.rygn.tse_springboot.service.impl;

import java.util.List;

import org.rygn.tse_springboot.dao.AnimalRepository;
import org.rygn.tse_springboot.domain.Animal;
import org.rygn.tse_springboot.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ZooServiceImpl implements ZooService {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Animal> findAllAnimals() {
		
		return this.animalRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Animal findAnimal(Long id) {

		return this.animalRepository.findById(id).orElse(null);
	}

	@Override
	public Animal createAnimal(Animal animal) {
		
		return this.animalRepository.save(animal);
	}

	@Override
	public void deleteAnimal(Long id) {
		
		this.animalRepository.deleteById(id);
	}

	@Override
	public Animal saveAnimal(Animal foundAnimal) {
		
		return this.animalRepository.save(foundAnimal);
	}
	
	
}
