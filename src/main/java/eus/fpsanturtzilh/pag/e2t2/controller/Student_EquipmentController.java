package eus.fpsanturtzilh.pag.e2t2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import eus.fpsanturtzilh.pag.e2t2.service.Student_EquipmentService;
import eus.fpsanturtzilh.pag.e2t2.model.Student_Equipment;

@RestController 
@RequestMapping("/api/student_equipments")
public class Student_EquipmentController {
	
	private final Student_EquipmentService service;
	
	public Student_EquipmentController (Student_EquipmentService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Student_Equipment> getStudent_Equipments(){
		return service.getAllStudent_Equipments();
	}
	
	@PostMapping
	public Student_Equipment addStudent_Equipment (@RequestBody Student_Equipment equipment) {
		return service.saveStudent_Equipment(equipment);
	}
	
	@PutMapping("/{id}")
	public Student_Equipment updateStudent_Equipment (@PathVariable Long id, @RequestBody Student_Equipment updatedStudent_Equipment) {
		return service.updateStudent_Equipment (id, updatedStudent_Equipment);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent_Equipment (@PathVariable Long id) {
		service.deleteStudent_Equipment(id);
	}
}
