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
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isDefUser() {
		return defUser;
	}
	public void setDefUser(boolean defUser) {
		this.defUser = defUser;
	}
	public boolean isEmployee() {
		return employee;
	}
	public void setEmployee(boolean employee) {
		this.employee = employee;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getEmail() {
		return email;
	}
	
	
	
	
}
