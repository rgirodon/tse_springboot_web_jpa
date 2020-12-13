package org.rygn.tse_springboot;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rygn.tse_springboot.dao.AnimalRepository;
import org.rygn.tse_springboot.domain.Animal;
import org.rygn.tse_springboot.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ZooServiceTest {

	@Autowired
	private ZooService zooService;
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Test
	public void testFindAllAnimals()  throws Exception{
		
		Collection<Animal> allAnimals = this.zooService.findAllAnimals();
		
		Assertions.assertEquals(3, allAnimals.size());
	}
	
	@Test
	public void testFindAnimal()  throws Exception{
		
		Animal animal = this.zooService.findAnimal(1L);
		
		Assertions.assertEquals("Girafe", animal.getName());
	}
		
	@Test
	public void testCreateAnimal()  throws Exception{
		
		Animal animal = new Animal();
		animal.setName("Zèbre");
		
		animal = this.zooService.createAnimal(animal);
		
		Collection<Animal> allAnimals = this.zooService.findAllAnimals();
		
		Assertions.assertEquals(4, allAnimals.size());
		
		this.animalRepository.delete(animal);
	}
		
	@Test
	public void testDeleteAnimal() throws Exception {
		
		this.zooService.deleteAnimal(3L);
		
		Collection<Animal> allAnimals = this.zooService.findAllAnimals();
		
		Assertions.assertEquals(2, allAnimals.size());
		
		Animal animal = new Animal();
		animal.setName("Lion");
		this.animalRepository.save(animal);
	}
		
	@Test
	public void testUpdateAnimal() throws Exception {
		
		Animal animal = this.animalRepository.findById(2L).orElse(null);
		
		animal.setName("Gorille");
		
		this.zooService.saveAnimal(animal);
		
		animal = this.zooService.findAnimal(2L);
		
		Assertions.assertEquals("Gorille", animal.getName());
		
		animal.setName("Eléphant");
		
		this.animalRepository.save(animal);
	}
}
