package eus.fpsanturtzilh.pag.e2t2.model;

import java.util.Date;

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
@Table(name="student_consumables")
public class Student_consumable extends Auditable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private int quantity;
}
