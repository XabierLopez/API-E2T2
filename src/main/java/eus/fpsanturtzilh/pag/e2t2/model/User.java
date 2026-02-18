package eus.fpsanturtzilh.pag.e2t2.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="users")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?") //soft deleterako anotazioa, ezabatu beharrean deleted_at eguneratu ezabatutako momentuaren timestamp batekin
@SQLRestriction("deleted_at IS NULL")//AND deleted_at IS NULL gehitu sql eragiketa guztiei, horrela timestamp dutenak ignoratuta
public class User extends Auditable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String username;
	@Column
	private String email;
	@Column
	private String rol;
	@Column
	private String password;//SHA-256 bidez hasheatutako pasahitza
	
	@OneToOne
	@JoinColumn(name="userClient_id")
    @JsonManagedReference("user-client")
    private Client client;
	
	@OneToOne
	@JoinColumn(name="userStudent_id")
    @JsonManagedReference("user-student")
    private Student student;
	
	// @OneToOne
    // @JoinColumn(name = "user_id", nullable = false)
    // @JsonManagedReference("user-student")
    // private User user;
	
	// @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // @JsonBackReference("user-student")
    // private Student student;
}
