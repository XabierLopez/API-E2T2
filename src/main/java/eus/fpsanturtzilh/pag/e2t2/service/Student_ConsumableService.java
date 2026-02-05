package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import eus.fpsanturtzilh.pag.e2t2.model.Student_Consumable;
import eus.fpsanturtzilh.pag.e2t2.repository.Student_ConsumableRepository;

public class Student_ConsumableService {
	private final Student_ConsumableRepository repository;
	
	public Student_ConsumableService(Student_ConsumableRepository repository) {
		this.repository = repository;
	}
	
	public List<Student_Consumable> getAllStudent_Consumable(){
		return repository.findAll();
	}
	public Student_Consumable saveStudent_Consumable(Student_Consumable Students_Consumable) {
		return repository.save(Students_Consumable);
	}
	public void deleteStudent_Consumable(Long id) {
		repository.deleteById(id);
	}
	public Student_Consumable updateStudent_Consumable(Long id, Student_Consumable updateStudent_Consumable) {
		Student_Consumable existingStudent_Consumable = repository.findById(id).orElseThrow(() -> new RuntimeException("Student_Consumablea not found with id" + id));
		
		existingStudent_Consumable.setDate(updateStudent_Consumable.getDate());
		existingStudent_Consumable.setQuantity(updateStudent_Consumable.getQuantity());
		
		return repository.save(existingStudent_Consumable);
	}

	public static Student_Consumable findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
