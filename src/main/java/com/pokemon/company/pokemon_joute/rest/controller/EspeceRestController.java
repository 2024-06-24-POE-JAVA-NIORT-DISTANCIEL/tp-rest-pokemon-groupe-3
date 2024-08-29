package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.model.Espece;
import com.pokemon.company.pokemon_joute.service.EspeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/especes")
public class EspeceRestController {
	
	@Autowired
	EspeceService especeService;
	
	@GetMapping("/{id}")
	public Optional<Espece> getById(@PathVariable("id") Long especeId) {
		return this.especeService.findById(especeId);
	}
	
	@PostMapping
	public Espece save(@RequestBody Espece espece) {
		return this.especeService.save(espece);
	}
//	
//	@DeleteMapping("espece/{especeId}")
//	public void delete(@PathVariable("especeId") Long especeId) {
//		this.especeService.removeById(especeId);
//	}
	
	
	
	
}


