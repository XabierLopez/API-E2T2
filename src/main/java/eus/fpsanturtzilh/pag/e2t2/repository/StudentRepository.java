package eus.fpsanturtzilh.pag.e2t2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eus.fpsanturtzilh.pag.e2t2.model.Student;

public interface StudentRepository extends JpaRepository <Student, Long>{
	public Student findFirstByIzena(String izena);
}
