package sing.proyectosi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;

@Entity
@Table(name = "userAccess")
public class UserAccess {

	
	@Id
	private String email;
	
	private String password;
	private boolean defUser;
	private boolean employee;
	private boolean admin;
	
	
	
	
}
