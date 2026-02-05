package eus.fpsanturtzilh.pag.e2t2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eus.fpsanturtzilh.pag.e2t2.model.Student_Equipment;

public interface Student_EquipmentRepository  extends JpaRepository <Student_Equipment, Long> {

	
	public Student_Equipment findFirstByIzena(String izena);

}