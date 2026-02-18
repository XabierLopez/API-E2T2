package eus.fpsanturtzilh.pag.e2t2.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="shifts")
@SQLDelete(sql = "UPDATE shifts SET deleted_at = NOW() WHERE id = ?") //soft deleterako anotazioa, ezabatu beharrean deleted_at eguneratu ezabatutako momentuaren timestamp batekin
@SQLRestriction("deleted_at IS NULL")//AND deleted_at IS NULL gehitu sql eragiketa guztiei, horrela timestamp dutenak ignoratuta
public class Shift extends Auditable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Character type;
	
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	@JsonBackReference("student-shift")
	private Student student;
}
