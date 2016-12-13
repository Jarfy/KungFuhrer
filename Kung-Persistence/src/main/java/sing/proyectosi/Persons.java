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

	
}
