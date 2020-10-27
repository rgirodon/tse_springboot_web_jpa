package org.rygn.tse_springboot;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.rygn.tse_springboot.dao.AnimalRepository;
import org.rygn.tse_springboot.domain.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ZooControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Test
	public void testAllAnimals()  throws Exception{
		
		this.mockMvc
			.perform(get("/animals"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.length()", is(3)));
	}
	
	@Test
	public void testOneAnimal()  throws Exception{
		
		this.mockMvc
			.perform(get("/animals/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.name", is("Girafe")));
	}
	
	@Test
	public void testCreateAnimal()  throws Exception{
		
		Animal animal = new Animal();
		animal.setName("Hippopotame");
		
		ObjectMapper mapper = new ObjectMapper();
        byte[] animalAsBytes = mapper.writeValueAsBytes(animal);
		
		
		this.mockMvc
			.perform(
					post("/animals")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON).content(animalAsBytes))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.name", is("Hippopotame")));
		
		assertEquals(4, this.animalRepository.count());
		
		Collection<Animal> animals = animalRepository.findAll();
		
		boolean found = false;
		
		for (Animal currentAnimal : animals) {
			
			if (currentAnimal.getName().equals("Hippopotame")) {
				
				found = true;
				
				animalRepository.delete(currentAnimal);
			}
		}
		
		assertTrue(found);
	}
	
	@Test
	public void testDeleteAnimal() throws Exception {
		
		Animal animal = new Animal();
		animal.setName("Zèbre");
		
		this.animalRepository.save(animal);
		
		Collection<Animal> animals = this.animalRepository.findAll();
		
		Long id = 0L;
		
		for (Animal currentAnimal : animals) {
			
			if (currentAnimal.getName().equals("Zèbre")) {
				
				id = currentAnimal.getId();
			}
		}
		
		this.mockMvc.perform(
					delete("/animals/" + id)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		
		assertEquals(3, this.animalRepository.count());
	}
	
	@Test
	public void testReplaceAnimal() throws Exception {
		
		Animal animal = new Animal();
		animal.setName("Gorille");
		
		ObjectMapper mapper = new ObjectMapper();
        byte[] animalAsBytes = mapper.writeValueAsBytes(animal);
        
        this.mockMvc.perform(
						put("/animals/1")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(animalAsBytes))
						.andExpect(status().isOk());
        
        animal = this.animalRepository.findById(1L).orElse(null);
        
        if (animal == null) {
        	
        	fail("Animal not found");
        }
        
        assertEquals("Gorille", animal.getName());
        
        animal.setName("Girafe");
        
        this.animalRepository.save(animal);
	}
}
