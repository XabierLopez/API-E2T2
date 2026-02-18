package eus.fpsanturtzilh.pag.e2t2.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

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
@Table(name="equipments")
@SQLDelete(sql = "UPDATE equipments SET deleted_at = NOW() WHERE id = ?") //soft deleterako anotazioa, ezabatu beharrean deleted_at eguneratu ezabatutako momentuaren timestamp batekin
@SQLRestriction("deleted_at IS NULL")//AND deleted_at IS NULL gehitu sql eragiketa guztiei, horrela timestamp dutenak ignoratuta
public class Equipment extends Auditable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String label;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String brand;
	
	@OneToMany(mappedBy = "equipment")
    @JsonManagedReference("equipment-student")
    private List<Student_Equipment> student_equipments = new ArrayList<>();
}
