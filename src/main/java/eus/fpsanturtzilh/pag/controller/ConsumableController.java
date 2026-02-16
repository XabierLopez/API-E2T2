package eus.fpsanturtzilh.pag.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import eus.fpsanturtzilh.pag.e2t2.service.ConsumableService;
import eus.fpsanturtzilh.pag.e2t2.model.Consumable;

@RestController 
@RequestMapping("/api/consumables")
public class ConsumableController {
	
	private final ConsumableService service;
	
	public ConsumableController (ConsumableService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Consumable> getConsumables(){
		return service.getAllConsumables();
	}
	
	@PostMapping
	public Consumable addConsumable (@RequestBody Consumable consumable) {
		return service.saveConsumable(consumable);
	}
	
	@PutMapping("/{id}")
	public Consumable updateConsumable (@PathVariable Long id, @RequestBody Consumable updatedConsumable) {
		return service.updateConsumable (id, updatedConsumable);
	}
	
	@DeleteMapping("/{id}")
	public void deleteConsumable (@PathVariable Long id) {
		service.deleteConsumable(id);
	}
}
