package sing.proyectosi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="personsProduction")
public class PersonsProduction {

	@Id
	private int idPerson;	
	
	@Id
	private int idproduction;	
	
	private String type;

	
	@ManyToOne
	@JoinColumn(name="idperson")
	private Persons persons;
	
	@ManyToOne
	@JoinColumn(name="idProduction")
	private Production production;

	
}
