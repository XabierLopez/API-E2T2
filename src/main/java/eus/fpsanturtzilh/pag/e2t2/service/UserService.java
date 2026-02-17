package eus.fpsanturtzilh.pag.e2t2.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.e2t2.model.Category;
import eus.fpsanturtzilh.pag.e2t2.model.User;
import eus.fpsanturtzilh.pag.e2t2.repository.CategoryRepository;
import eus.fpsanturtzilh.pag.e2t2.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User saveUser(User user) {
    	if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }else if(repository.existsByUsername(user.getUsername())){
        	throw new RuntimeException("Username already in use");       	
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (user.getRol() == null || user.getRol().isBlank()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        user.setPassword(hashPassword(user.getPassword()));//pasahitza hasheatuta gorde beti, beraz textu laua bezala jasotzea espero da
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public User updateUser(Long id, User user) {
    	User existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

    	if (user.getUsername() != null && !user.getUsername().isBlank() && !repository.existsByUsername(user.getUsername())) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getRol() != null && !user.getRol().isBlank()) {
            existingUser.setRol(user.getRol());
        }
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existingUser.setPassword(hashPassword(user.getPassword()));//pasahitza hasheatuta gorde beti, beraz textu laua bezala jasotzea espero da
        }

        return repository.save(existingUser);
    }

    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }
    
    //pasahitza hasheatzeko funtzio auxiliar pribatua
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
