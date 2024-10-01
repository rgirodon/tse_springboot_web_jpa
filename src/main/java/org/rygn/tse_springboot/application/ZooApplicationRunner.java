package org.rygn.tse_springboot.application;

import org.rygn.tse_springboot.dao.AnimalRepository;
import org.rygn.tse_springboot.domain.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ZooApplicationRunner implements ApplicationRunner {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Animal girafe = new Animal();
		girafe.setId(1L);
		girafe.setName("Girafe");		
		this.animalRepository.save(girafe);
		
		Animal elephant = new Animal();
		elephant.setId(2L);
		elephant.setName("Eléphant");		
		this.animalRepository.save(elephant);
		
		Animal lion = new Animal();
		lion.setId(3L);
		lion.setName("Lion");		
		this.animalRepository.save(lion);
	}

}
