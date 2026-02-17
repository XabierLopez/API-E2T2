package eus.fpsanturtzilh.pag.e2t2.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import eus.fpsanturtzilh.pag.e2t2.model.User;
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
    
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));
    }
    
    public User getUserLogin(String username, String password) {
        return repository.findFirstByUsernameAndPassword(username, hashPassword(password)).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));
    }

    public User saveUser(User user) {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be null or empty");
        }
        if (repository.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already in use");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot be null or empty");
        }
        if (user.getRol() == null || user.getRol().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role cannot be null or empty");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be null or empty");
        }

        user.setPassword(hashPassword(user.getPassword()));//pasahitza hasheatuta gorde beti, beraz textu laua bezala jasotzea espero da
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id);
        }
        repository.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));


        //editatzean, editatuko ez diren propietateak zeuden bezala bidaltzea espero da, horrela formulario batean eremu guztiak aldatzen ez badira ere balidatu ahal izateko
        
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be null or blank");
        }
        if (repository.existsByUsername(user.getUsername()) && !user.getUsername().equals(existingUser.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already in use: " + user.getUsername());
        }
        existingUser.setUsername(user.getUsername());

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot be null or blank");
        }
        existingUser.setEmail(user.getEmail());

        if (user.getRol() == null || user.getRol().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role cannot be null or blank");
        }
        existingUser.setRol(user.getRol());

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be null or blank");
        }
        existingUser.setPassword(hashPassword(user.getPassword()));//pasahitza hasheatuta gorde beti, beraz textu laua bezala jasotzea espero da

        return repository.save(existingUser);
    }


    //pasahitza hasheatzeko funtzio auxiliar pribatua
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error hashing password", e);
        }
    }
}