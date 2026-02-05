package eus.fpsanturtzilh.pag.e2t2.model;

import java.time.LocalDateTime;

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
@Table(name="students_equipments")
public class Student_Equipment extends Auditable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private LocalDateTime start_datetime;
	@Column
	private LocalDateTime end_datetime;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	@JsonBackReference
	private Student student;

	@ManyToOne
	@JoinColumn(name = "equipment_id")
	@JsonBackReference
	private Equipment equipment;
}
