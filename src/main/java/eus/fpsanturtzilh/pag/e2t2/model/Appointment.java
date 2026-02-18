package eus.fpsanturtzilh.pag.e2t2.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="appointments")
@SQLDelete(sql = "UPDATE appointments SET deleted_at = NOW() WHERE id = ?") //soft deleterako anotazioa, ezabatu beharrean deleted_at eguneratu ezabatutako momentuaren timestamp batekin
@SQLRestriction("deleted_at IS NULL")//AND deleted_at IS NULL gehitu sql eragiketa guztiei, horrela timestamp dutenak ignoratuta
public class Appointment extends Auditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Integer seat;
	@Column
	private LocalDate date;
	@Column
	private LocalTime start_time;
	@Column
	private LocalTime end_time;//
	@Column
	private String comment;//
	@Column
	private String name;
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	@JsonBackReference("client-appointment")
	private Client client;
	
	@OneToMany(mappedBy = "appointment")
	@JsonManagedReference("appointment-appointmentService") //jsonbackereference-ek gako bat behar du modelo berean bat baino gehiago badago
	private List<Appointment_service> appointmentServices;
}
