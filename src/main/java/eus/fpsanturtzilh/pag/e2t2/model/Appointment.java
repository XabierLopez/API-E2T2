package eus.fpsanturtzilh.pag.e2t2.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment extends Auditable{
	private Long id;
	private Integer seat;
	private LocalDate date;
	private LocalTime start_time;
	private LocalTime end_time;
	private String comment;
	private String name;
	
}
