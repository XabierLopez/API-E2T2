package eus.fpsanturtzilh.pag.e2t2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eus.fpsanturtzilh.pag.e2t2.model.Consumable;

public interface ConsumableRepository extends JpaRepository <Consumable, Long> {

	
	public Consumable findFirstByIzena(String izena);

}