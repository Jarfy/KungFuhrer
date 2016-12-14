package sing.proyectosi;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name = "email")
	private String email; 
	
	private String name;
	private String lastName;
	private String password;
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Vote> votes = new HashSet<>();

	@OneToOne
	@PrimaryKeyJoinColumn
	private UserAccess userAccess;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	//metodos relaciones
	public Set<Vote> getVotes() {
		return votes;
	}
	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}
	public UserAccess getUserAccess() {
		return userAccess;
	}
	public void setUserAccess(UserAccess userAccess) {
		this.userAccess = userAccess;
	}
	
}
