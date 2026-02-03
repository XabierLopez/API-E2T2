package eus.fpsanturtzilh.pag.e2t2.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="appointments")
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
	private LocalTime end_time;
	@Column
	private String comment;
	@Column
	private String name;
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;
}
