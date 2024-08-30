package com.pokemon.company.pokemon_joute.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.company.pokemon_joute.model.Espece;
import com.pokemon.company.pokemon_joute.service.EspeceService;

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
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		this.especeService.deleteById(id);
	}
	
}


