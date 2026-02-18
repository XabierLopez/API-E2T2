package eus.fpsanturtzilh.pag.e2t2.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="students")
public class Student extends Auditable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String surname;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("student-equipment")
	private List<Student_Equipment> student_equipments = new ArrayList<>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("student-consumable")
	private List<Student_Consumable> student_consumables = new ArrayList<>();
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("student-shift")
    private List<Shift> shifts = new ArrayList<>();
	
	// @OneToOne
    // @JoinColumn(name = "user_id", nullable = false)
    // @JsonManagedReference("user-student")
    // private User user;
	
	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonBackReference("user-student")
    private User user;
}
