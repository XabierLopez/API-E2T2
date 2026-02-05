package eus.fpsanturtzilh.pag.e2t2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eus.fpsanturtzilh.pag.e2t2.model.Equipment;

public interface EquipmentRepository extends JpaRepository <Equipment, Long> {

	
	public Equipment findFirstByIzena(String izena);

}