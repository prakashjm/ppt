package com.xyz.mydiary.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "m_user")
@Getter
@Setter
 
public class UserDAO {

	@Id
	@SequenceGenerator(name = "seq_m_user", sequenceName = "seq_m_user", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_m_user")
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "Email should be valid")
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "user_type", nullable = false)
	private String userType;

	@NotBlank(message = "first name should be valid")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "middle_name", nullable = true)
	private String middleName;

	@NotBlank(message = "last name should be valid")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@NotBlank(message = "Mobile should be valid")
	@Column(name = "mobile", nullable = false)
	private String mobile;

	@NotBlank(message = "Password  should be valid")
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "status", nullable = false)
	private Boolean status;

	@Column(name = "modified_by", nullable = false)
	private String modifiedBy;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "last_logged", nullable = false)
	private LocalDate accessDate;

	@Column(name = "locked"/* nullable = false */)
	private Boolean locked;

	@ManyToMany( /* cascade = { CascadeType.REMOVE }, */fetch = FetchType.EAGER)
	@JoinTable(name = "map_user_roles", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Set<Role> roles;

	@Column(name = "enabled")
	private boolean enabled;
	
	  public UserDAO() {
	        super();
	        this.enabled=false;
	    }

}
