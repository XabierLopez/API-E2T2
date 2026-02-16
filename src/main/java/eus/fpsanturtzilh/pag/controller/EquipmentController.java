package eus.fpsanturtzilh.pag.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import eus.fpsanturtzilh.pag.e2t2.service.EquipmentService;
import eus.fpsanturtzilh.pag.e2t2.model.Equipment;

@RestController 
@RequestMapping("/api/equipments")
public class EquipmentController {
	
	private final EquipmentService service;
	
	public EquipmentController (EquipmentService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Equipment> getEquipments(){
		return service.getAllEquipments();
	}
	
	@PostMapping
	public Equipment addEquipment (@RequestBody Equipment equipment) {
		return service.saveEquipment(equipment);
	}
	
	@PutMapping("/{id}")
	public Equipment updateEquipment (@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
		return service.updateEquipment (id, updatedEquipment);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEquipment (@PathVariable Long id) {
		service.deleteEquipment(id);
	}
}
