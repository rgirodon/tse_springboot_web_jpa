package org.rygn.tse_springboot.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rygn.tse_springboot.dao.AnimalRepository;
import org.rygn.tse_springboot.domain.Animal;
import org.springframework.stereotype.Repository;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {

	private Map<Long, Animal> repository = new HashMap<>();
	
	@Override
	public Animal save(Animal animal) {

		this.repository.put(animal.getId(), animal);
		
		return this.repository.get(animal.getId());
	}

	@Override
	public Animal findById(Long id) {

		return this.repository.get(id);
	}

	@Override
	public List<Animal> findAll() {

		List<Animal> result = new ArrayList<>();
		
		result.addAll(repository.values());
		
		return result;
	}

	@Override
	public void deleteById(Long id) {
		
		this.repository.remove(id);
	}

	@Override
	public Integer count() {
		
		return this.findAll().size();
	}

}
