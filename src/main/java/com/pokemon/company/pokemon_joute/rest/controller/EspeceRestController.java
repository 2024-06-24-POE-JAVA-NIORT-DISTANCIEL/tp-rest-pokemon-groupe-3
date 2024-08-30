package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.model.Espece;
import com.pokemon.company.pokemon_joute.service.EspeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/especes")
public class EspeceRestController {
	
	@Autowired
	EspeceService especeService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Espece> getById(@PathVariable("id") Long especeId) {
		return especeService.findById(especeId)
				.map(espece -> ResponseEntity.ok(espece))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Espece save(@RequestBody Espece espece) {
		return this.especeService.save(espece);
	}
//	
//	@DeleteMapping("/{especeId}")
//	public void delete(@PathVariable("especeId") Long especeId) {
//		this.especeService.removeById(especeId);
//	}
	
	
	
	
}


