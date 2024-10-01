package org.rygn.tse_springboot.controller;

import org.rygn.tse_springboot.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
		
	@Autowired
	private ZooService zooService;
	
	@GetMapping("/web/animals")
	public String allAnimals(Model model) {
				
		model.addAttribute("animals", this.zooService.findAllAnimals());
		
		return "animals";
	}
}
