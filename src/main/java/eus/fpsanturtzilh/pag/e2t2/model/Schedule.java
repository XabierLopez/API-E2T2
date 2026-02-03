package eus.fpsanturtzilh.pag.e2t2.model;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Entity;
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
	private Long id;
	private int day;
	private Date start_date;
	private Date end_date;
	private Time start_time;
	private Time end_time;
}
