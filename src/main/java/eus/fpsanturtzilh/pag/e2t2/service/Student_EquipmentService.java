package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import eus.fpsanturtzilh.pag.e2t2.model.Student_Equipment;
import eus.fpsanturtzilh.pag.e2t2.repository.Student_EquipmentRepository;


public class Student_EquipmentService {
	
	private final Student_EquipmentRepository repository;
	
	public Student_EquipmentService(Student_EquipmentRepository repository) {
		this.repository = repository;
	}
	
	public List<Student_Equipment> getAllStudent_Equipments(){
		return repository.findAll();
	}
	public Student_Equipment saveStudent_Equipment(Student_Equipment student_equipment) {
		return repository.save(student_equipment);
	}
	public void deleteStudent_Equipment(Long id) {
		repository.deleteById(id);
	}
	public Student_Equipment updateStudent_Equipment(Long id, Student_Equipment updateStudent_Equipment) {
		Student_Equipment existingStudent_Equipment = repository.findById(id).orElseThrow(() -> new RuntimeException("Student_Equipmenta not found with id" + id));
		
		existingStudent_Equipment.setStart_datetime(updateStudent_Equipment.getStart_datetime());
		existingStudent_Equipment.setEnd_datetime(updateStudent_Equipment.getEnd_datetime());
		
		return repository.save(existingStudent_Equipment);
	}

	public static Student_Equipment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
