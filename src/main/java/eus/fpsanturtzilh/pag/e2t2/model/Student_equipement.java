package eus.fpsanturtzilh.pag.e2t2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="students_equipments")
public class Student_equipement extends Auditable{
	private Long id;
	private LocalDateTime start_datetime;
	private LocalDateTime end_datetime;
	
}
