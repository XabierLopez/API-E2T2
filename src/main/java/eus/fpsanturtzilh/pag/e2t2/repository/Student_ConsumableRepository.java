package eus.fpsanturtzilh.pag.e2t2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eus.fpsanturtzilh.pag.e2t2.model.Student_Consumable;

public interface Student_ConsumableRepository extends JpaRepository <Student_Consumable, Long> {

	
	public Student_Consumable findFirstByIzena(String izena);

}