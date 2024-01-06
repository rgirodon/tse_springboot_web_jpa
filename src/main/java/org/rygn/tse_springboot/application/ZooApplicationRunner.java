package org.rygn.tse_springboot.application;

import org.rygn.tse_springboot.dao.AnimalRepository;
import org.rygn.tse_springboot.domain.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class ZooApplicationRunner implements ApplicationRunner {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Animal girafe = new Animal();
		girafe.setName("Girafe");		
		this.animalRepository.save(girafe);
		
		Animal elephant = new Animal();
		elephant.setName("Eléphant");		
		this.animalRepository.save(elephant);
		
		Animal lion = new Animal();
		lion.setName("Lion");		
		this.animalRepository.save(lion);
	}

}
