package eus.fpsanturtzilh.pag.e2t2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@Table(name="users")
public class User extends Auditable{
	private Long id;
	private String username;
	private String email;
	private String rol;
}
