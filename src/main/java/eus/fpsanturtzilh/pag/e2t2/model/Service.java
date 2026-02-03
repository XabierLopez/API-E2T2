package eus.fpsanturtzilh.pag.e2t2.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="services")
public class Service extends Auditable{
	private Long id;
	private String name;
	private BigDecimal price;
	private BigDecimal home_price;
	private int duration;
	
}
