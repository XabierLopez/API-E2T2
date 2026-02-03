package eus.fpsanturtzilh.pag.e2t2.model;

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
@Table(name="equipments")
public class Equipment extends Auditable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String label;
	private String name;
	private String description;
	private String brand;
}
