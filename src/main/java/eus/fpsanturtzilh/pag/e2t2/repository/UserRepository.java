package eus.fpsanturtzilh.pag.e2t2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import eus.fpsanturtzilh.pag.e2t2.model.User;

public interface UserRepository extends JpaRepository <User, Long>{
	public Optional<User> findFirstByUsername(String username);
	public boolean existsByUsername(String username);
	public Optional<User> findFirstByUsernameAndPassword(String username, String password);
}
