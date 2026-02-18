package eus.fpsanturtzilh.pag.e2t2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import eus.fpsanturtzilh.pag.e2t2.model.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

}