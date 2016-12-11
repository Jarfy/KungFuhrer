package sing.proyectosi;

import java.util.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	private String email; 
	private String name;
	private String lastName;
	private String password;
	
	@OneToMany
	private Set<Vote> votes = new HashSet<>();

	
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
