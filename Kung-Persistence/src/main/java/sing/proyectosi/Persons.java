package sing.proyectosi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(name="idperson")
	private PersonsProduction personProdcution;

	
}
