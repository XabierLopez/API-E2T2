package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import eus.fpsanturtzilh.pag.e2t2.model.Consumable;
import eus.fpsanturtzilh.pag.e2t2.repository.ConsumableRepository;

public class ConsumableService {
	private final ConsumableRepository repository;
	
	public ConsumableService(ConsumableRepository repository) {
		this.repository = repository;
	}
	
	public List<Consumable> getAllConsumables(){
		return repository.findAll();
	}
	public Consumable saveConsumables(Consumable talde) {
		return repository.save(talde);
	}
	public void deleteConsumable(Long id) {
		repository.deleteById(id);
	}
	public Consumable updateConsumable(Long id, Consumable updateConsumable) {
		Consumable existingConsumable = repository.findById(id).orElseThrow(() -> new RuntimeException("Consumablea not found with id" + id));
		
		existingConsumable.setName(updateConsumable.getName());
		existingConsumable.setDescription(updateConsumable.getDescription());
		existingConsumable.setBatch(updateConsumable.getBatch());
		existingConsumable.setBrand(updateConsumable.getBrand());
		existingConsumable.setStock(updateConsumable.getStock());
		existingConsumable.setMin_stock(updateConsumable.getMin_stock());
		existingConsumable.setExpiration_date(updateConsumable.getExpiration_date());
		
		return repository.save(existingConsumable);
	}


	public static Consumable findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
