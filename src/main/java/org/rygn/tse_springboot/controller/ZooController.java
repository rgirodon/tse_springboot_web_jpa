package org.rygn.tse_springboot.controller;

import java.util.List;

import org.rygn.tse_springboot.domain.Animal;
import org.rygn.tse_springboot.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZooController {
	
	
	@Autowired
	private ZooService zooService;
	
	@GetMapping("/animals")
	public List<Animal> allAnimals() {
				
		return this.zooService.findAllAnimals();
	}
	
	@GetMapping("/animals/{id}")
	public Animal oneAnimal(@PathVariable Long id) {
				
		return this.zooService.findAnimal(id);
	}
	
	@PostMapping("/animals")
	public Animal createAnimal(@RequestBody Animal animal) {
		
		return this.zooService.createAnimal(animal);
	}
	
	@DeleteMapping("/animals/{id}")
	public void deleteAnimal(@PathVariable Long id) {
		
		this.zooService.deleteAnimal(id);
	}
	
	@PutMapping("/animals/{id}")
	Animal replaceAnimal(@RequestBody Animal animal, @PathVariable Long id) {

		Animal foundAnimal = this.zooService.findAnimal(id);
		
		if (animal != null) {
			
			foundAnimal.setName(animal.getName());
			
			foundAnimal = this.zooService.saveAnimal(foundAnimal);
		}
		
		return foundAnimal;
	}
}
