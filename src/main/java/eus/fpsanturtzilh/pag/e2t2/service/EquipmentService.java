package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import eus.fpsanturtzilh.pag.e2t2.model.Equipment;
import eus.fpsanturtzilh.pag.e2t2.repository.EquipmentRepository;

public class EquipmentService {
private final EquipmentRepository repository;
	
	public EquipmentService(EquipmentRepository repository) {
		this.repository = repository;
	}
	
	public List<Equipment> getAllEquipments(){
		return repository.findAll();
	}
	public Equipment saveEquipments(Equipment talde) {
		return repository.save(talde);
	}
	public void deleteEquipment(Long id) {
		repository.deleteById(id);
	}
	public Equipment updateEquipment(Long id, Equipment updateEquipment) {
		Equipment existingEquipment = repository.findById(id).orElseThrow(() -> new RuntimeException("Equipmenta not found with id" + id));
		
		existingEquipment.setLabel(updateEquipment.getLabel());
		existingEquipment.setName(updateEquipment.getName());
		existingEquipment.setDescription(updateEquipment.getDescription());
		existingEquipment.setBrand(updateEquipment.getBrand());
		
		return repository.save(existingEquipment);
	}

	public static Equipment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
