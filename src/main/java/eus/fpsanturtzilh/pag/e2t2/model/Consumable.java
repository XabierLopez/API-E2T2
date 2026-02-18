package eus.fpsanturtzilh.pag.e2t2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@SQLDelete(sql = "UPDATE consumables SET deleted_at = NOW() WHERE id = ?") //soft deleterako anotazioa, ezabatu beharrean deleted_at eguneratu ezabatutako momentuaren timestamp batekin
@SQLRestriction("deleted_at IS NULL")//AND deleted_at IS NULL gehitu sql eragiketa guztiei, horrela timestamp dutenak ignoratuta
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
	private Integer stock;
	@Column
	private Integer min_stock;
	@Column
	private LocalDate expiration_date;
	

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference("category-consumable")
    private Category category;
    
    @OneToMany(mappedBy = "consumable")
    @JsonManagedReference("consumable-student")
    private List<Student_Consumable> student_consumables = new ArrayList<>();
}
