package eus.fpsanturtzilh.pag.e2t2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eus.fpsanturtzilh.pag.e2t2.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}