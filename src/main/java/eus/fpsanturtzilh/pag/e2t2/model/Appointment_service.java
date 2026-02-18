package eus.fpsanturtzilh.pag.e2t2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="appointments_service")
public class Appointment_service extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String comment;//
	
	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	@JsonBackReference("service-appointmentService")//jsonmanagedreference-ek gako bat behar du modelo berean bat baino gehiago badago
	private ServiceEntity service;

	@ManyToOne
	@JoinColumn(name = "appointment_id", nullable = false)
	@JsonBackReference("appointment-appointmentService")//jsonmanagedreference-ek gako bat behar du modelo berean bat baino gehiago badago
	private Appointment appointment;
}
