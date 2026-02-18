package eus.fpsanturtzilh.pag.e2t2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import eus.fpsanturtzilh.pag.e2t2.model.Appointment_service;

import java.util.List;
import java.util.Optional;

public interface AppointmentServiceRepository extends JpaRepository<Appointment_service, Long> {
	Optional<Appointment_service> findFirstByAppointmentIdAndServiceId(Long appointmentId, Long serviceId);
	List<Appointment_service> findAllByAppointmentId(Long appointmentId);
	List<Appointment_service> findAllByServiceId(Long serviceId);
}