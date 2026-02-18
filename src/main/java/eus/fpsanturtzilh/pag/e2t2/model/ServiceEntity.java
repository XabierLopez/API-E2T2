package eus.fpsanturtzilh.pag.e2t2.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="services")
public class ServiceEntity extends Auditable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private BigDecimal price;
	@Column
	private BigDecimal home_price;
	@Column
	private Integer duration;
	
	@OneToMany(mappedBy = "service")
	@JsonManagedReference("service-appointmentService")
	private List<Appointment_service> appointmentServices;
}
