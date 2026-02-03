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
@Table(name="consumables")
public class Consumable extends Auditable{
	private Long id;
	private String name;
	private String description;
	private String batch;
	private String brand;
	private int stock;
	private int min_stock;
	private Date expiration_date;
}
