package sing.proyectosi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "userAccess")
public class UserAccess {

	
	@Column(name="email")
	private String email;
	private String password;
	private boolean userr;
	private boolean employee;
	private boolean admin;
	
	
	@OneToOne 
	private User user;
	
}
