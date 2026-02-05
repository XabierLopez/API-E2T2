package eus.fpsanturtzilh.pag.e2t2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
@Table(name="consumables")
public class Consumable extends Auditable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String batch;
	@Column
	private String brand;
	@Column
	private int stock;
	@Column
	private int min_stock;
	@Column
	private Date expiration_date;
	
	@OneToMany(mappedBy = "Consumable", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Category> categories = new ArrayList<>();
}
