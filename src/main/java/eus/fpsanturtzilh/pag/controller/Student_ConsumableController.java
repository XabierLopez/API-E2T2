package eus.fpsanturtzilh.pag.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import eus.fpsanturtzilh.pag.e2t2.service.Student_ConsumableService;
import eus.fpsanturtzilh.pag.e2t2.model.Student_Consumable;

@RestController 
@RequestMapping("/api/student_consumables")
public class Student_ConsumableController {
	
	private final Student_ConsumableService service;
	
	public Student_ConsumableController (Student_ConsumableService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Student_Consumable> getStudent_Consumables(){
		return service.getAllStudent_Consumables();
	}
	
	@PostMapping
	public Student_Consumable addStudent_Consumable (@RequestBody Student_Consumable student_consumable) {
		return service.saveStudent_Consumable(student_consumable);
	}
	
	@PutMapping("/{id}")
	public Student_Consumable updateStudent_Consumable (@PathVariable Long id, @RequestBody Student_Consumable updatedStudent_Consumable) {
		return service.updateStudent_Consumable (id, updatedStudent_Consumable);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent_Consumable (@PathVariable Long id) {
		service.deleteStudent_Consumable(id);
	}
}
