
package eus.fpsanturtzilh.pag.e2t2.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET deleted_at = NOW() WHERE id = ?") //soft deleterako anotazioa, ezabatu beharrean deleted_at eguneratu ezabatutako momentuaren timestamp batekin
@SQLRestriction("deleted_at IS NULL")//AND deleted_at IS NULL gehitu sql eragiketa guztiei, horrela timestamp dutenak ignoratuta
public class Category extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "category")
	@JsonManagedReference("category-consumable")
	private List<Consumable> consumables = new ArrayList<>();
}
