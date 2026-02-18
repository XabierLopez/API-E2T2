package eus.fpsanturtzilh.pag.e2t2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import eus.fpsanturtzilh.pag.e2t2.service.UserService;
import eus.fpsanturtzilh.pag.e2t2.model.User;

@RestController 
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService service;
	
	public UserController (UserService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<User> getUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id){
		return service.getUserById(id);
	}
	
	@PostMapping("/login")
	public User getUserLogin(@RequestBody User user){
		return service.getUserLogin(user.getUsername(), user.getPassword());
	}
	
	@PostMapping
	public User addUser (@RequestBody User user) {
		return service.saveUser(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser (@PathVariable Long id, @RequestBody User updatedUser) {
		return service.updateUser (id, updatedUser);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser (@PathVariable Long id) {
		service.deleteUser(id);
	}
}
