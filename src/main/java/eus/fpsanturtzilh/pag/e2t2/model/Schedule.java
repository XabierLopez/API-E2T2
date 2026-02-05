package eus.fpsanturtzilh.pag.e2t2.model;

import java.sql.Time;
import java.util.Date;

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
	private Date start_date;
	@Column
	private Date end_date;
	@Column
	private Time start_time;
	@Column
	private Time end_time;
}
