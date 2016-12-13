package sing.proyectosi;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table; 

@Entity
@Table(name="persons")
public class Persons {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPerson;
	
	private String name;
	private String lastName;

	
	@ManyToMany
	@JoinTable(name="Person_Production", joinColumns={@JoinColumn(name="idPerson")}, inverseJoinColumns={@JoinColumn(name="idProduction")})
	private Set<Production> productions = new HashSet<>();


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


	public Set<Production> getProductions() {
		return productions;
	}


	public void setProductions(Set<Production> productions) {
		this.productions = productions;
	}


	public int getIdPerson() {
		return idPerson;
	}

	
	
	
	
}
