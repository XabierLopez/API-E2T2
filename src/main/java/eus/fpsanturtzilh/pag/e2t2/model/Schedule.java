package eus.fpsanturtzilh.pag.e2t2.model;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="schedules")
public class Schedule extends Auditable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Integer day;
	@Column
	private LocalDate start_date;
	@Column
	private LocalDate end_date;
	@Column
	private LocalTime start_time;
	@Column
	private LocalTime end_time;
}
