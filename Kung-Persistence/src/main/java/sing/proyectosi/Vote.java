package sing.proyectosi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVoto;		
	private int rating;
	
	@ManyToOne
	@JoinColumn(name="email_user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="idProduction")
	private Production production;
	
	//constructor
	public Vote(){	}
	
	
	public int getIdVoto() {
		return idVoto;
	}
	
	public int getIdproduction() {
		return production.getIdProduction();
	}

	public String getEmail() {
		return user.getEmail();
	}
	public void setEmail(String email) {
		this.user.setEmail(email); 
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	


	
}
