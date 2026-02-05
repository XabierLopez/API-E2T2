package eus.fpsanturtzilh.pag.e2t2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eus.fpsanturtzilh.pag.e2t2.model.Category;

public interface CategoryRepository extends JpaRepository <Category, Long> {

	
	public Category findFirstByIzena(String izena);

}