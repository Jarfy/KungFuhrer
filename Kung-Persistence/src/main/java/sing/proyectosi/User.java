package sing.proyectosi;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@OneToMany
	private Set<Vote> votes = new HashSet<>();

	@OneToOne
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

	//vote
	
	public Set<Vote> getVote() {
		return Collections.unmodifiableSet(votes);
	}

	public void addVote(Vote vote) {
		vote.setUser(this);
	}
	
	public void removeVote(Vote vote) {
		vote.setUser(null);
	}
	
	void internalRemoveVote(Vote vote) {
		this.votes.remove(vote);
	}

	void internalAddVote(Vote vote) {
		this.votes.add(vote);
	}
	
	
}
