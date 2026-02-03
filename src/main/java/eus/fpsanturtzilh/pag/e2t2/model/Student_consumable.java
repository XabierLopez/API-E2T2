package eus.fpsanturtzilh.pag.e2t2.model;

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
@Table(name="student_consumables")
public class Student_consumable extends Auditable{
	private Long id;
	private Date date;
	private int quantity;
}
